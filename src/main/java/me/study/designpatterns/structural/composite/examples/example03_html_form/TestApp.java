package me.study.designpatterns.structural.composite.examples.example03_html_form;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TestApp {

    public static void main(String[] args) {
        FormElement form = getProductForm();
        loadProductData(form);
        renderProduct(form);
    }

    public static FormElement getProductForm() {
        Form form = new Form("product", "Add product", "/product/add");
        form.add(new Input("name", "Name", "text"));
        form.add(new Input("description", "Description", "text"));

        Fieldset picture = new Fieldset("photo", "Product photo");
        picture.add(new Input("caption", "Caption", "text"));
        picture.add(new Input("image", "Image", "file"));
        form.add(picture);

        return form;
    }

    public static void loadProductData(FormElement form) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Apple MacBook");
        data.put("description", "A decent laptop.");
        data.put("photo", new String[]{
            "caption", "Front photo.",
            "image", "photo1.png"
        });
       
        form.setData(data);
    }

    public static void renderProduct(FormElement form) {
        System.out.println(form.render());
    }
}

abstract class FormElement {
    protected String name;
    protected String title;
    protected Object data;

    public FormElement(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return this.name;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public abstract String render();
}

class Input extends FormElement {
    private String type;

    public Input(String name, String title, String type) {
        super(name, title);
        this.type = type;
    }

    public String render() {
        return "<label for=\"" + this.name + "\">" + this.title + "</label>\n" +
            "<input name=\"" + this.name + "\" type=\"" + this.type + "\" value=\"" + this.data + "\">\n";
    }
}

abstract class FieldComposite extends FormElement {
    public FieldComposite(String name, String title) {
        super(name, title);
        //TODO Auto-generated constructor stub
    }

    protected Map<String, FormElement> fields = new HashMap<>();

    public void add(FormElement field) {
        String name = field.getName();
        this.fields.put(name, field);
    }

    public void remove(FormElement component) {
        this.fields = this.fields.entrySet().stream()
            .filter(entry -> !entry.getValue().equals(component))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setData(Object data) {
        for (Map.Entry<String, FormElement> entry : this.fields.entrySet()) {
            String name = entry.getKey();
            FormElement field = entry.getValue();
            if (data instanceof Map && ((Map) data).containsKey(name)) {
                field.setData(((Map) data).get(name));
            }
        }
    }

    public Object getData() {
        Map<String, Object> data = new HashMap<>();
        for (Map.Entry<String, FormElement> entry : this.fields.entrySet()) {
            String name = entry.getKey();
            FormElement field = entry.getValue();
            data.put(name, field.getData());
        }
        return data;
    }

    public String render() {
        StringBuilder output = new StringBuilder();
        for (FormElement field : this.fields.values()) {
            output.append(field.render());
        }
        return output.toString();
    }
}

class Fieldset extends FieldComposite {
    public Fieldset(String name, String title) {
        super(name, title);
        //TODO Auto-generated constructor stub
    }

    public String render() {
        String output = super.render();
        return "<fieldset><legend>" + this.title + "</legend>\n" + output + "</fieldset>\n";
    }
}

class Form extends FieldComposite {
    protected String url;

    public Form(String name, String title, String url) {
        super(name, title);
        this.url = url;
    }

    public String render() {
        String output = super.render();
        return "<form action=\"" + this.url + "\">\n<h3>" + this.title + "</h3>\n" + output + "</form>\n";
    }
}