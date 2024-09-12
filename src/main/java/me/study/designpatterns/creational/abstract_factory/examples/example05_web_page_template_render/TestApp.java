package me.study.designpatterns.creational.abstract_factory.examples.example05_web_page_template_render;

import java.util.Map;

public class TestApp {
    public static void main(String[] args) {
        Page page = new Page("Sample page", "This is the body.");
        System.out.println("Testing actual rendering with the PHPTemplate factory:");
        System.out.println(page.render(new PHPTemplateFactory()));
        System.out.println("Testing actual rendering with the TwigTemplate factory:");
        System.out.println(page.render(new TwigTemplateFactory()));
    }
}

interface TemplateFactory {
    TitleTemplate createTitleTemplate();

    PageTemplate createPageTemplate();

    TemplateRenderer getRenderer();
}

class TwigTemplateFactory implements TemplateFactory {
    public TitleTemplate createTitleTemplate() {
        return new TwigTitleTemplate();
    }

    public PageTemplate createPageTemplate() {
        return new TwigPageTemplate(createTitleTemplate());
    }

    public TemplateRenderer getRenderer() {
        return new TwigRenderer();
    }
}

class PHPTemplateFactory implements TemplateFactory {
    public TitleTemplate createTitleTemplate() {
        return new PHPTemplateTitleTemplate();
    }

    public PageTemplate createPageTemplate() {
        return new PHPTemplatePageTemplate(createTitleTemplate());
    }

    public TemplateRenderer getRenderer() {
        return new PHPTemplateRenderer();
    }
}

interface TitleTemplate {
    String getTemplateString();
}

class TwigTitleTemplate implements TitleTemplate {
    public String getTemplateString() {
        return "<h1>{{ title }}</h1>";
    }
}

class PHPTemplateTitleTemplate implements TitleTemplate {
    public String getTemplateString() {
        return "<h1><?= $title; ?></h1>";
    }
}

interface PageTemplate {
    String getTemplateString();
}

abstract class BasePageTemplate implements PageTemplate {
    protected TitleTemplate titleTemplate;

    public BasePageTemplate(TitleTemplate titleTemplate) {
        this.titleTemplate = titleTemplate;
    }
}

class TwigPageTemplate extends BasePageTemplate {
    public TwigPageTemplate(TitleTemplate titleTemplate) {
        super(titleTemplate);
        // TODO Auto-generated constructor stub
    }

    public String getTemplateString() {
        String renderedTitle = titleTemplate.getTemplateString();
        return "<div class=\"page\">\n" +
                renderedTitle +
                "\n<article class=\"content\">{{ content }}</article>\n</div>";
    }
}

class PHPTemplatePageTemplate extends BasePageTemplate {
    public PHPTemplatePageTemplate(TitleTemplate titleTemplate) {
        super(titleTemplate);
        // TODO Auto-generated constructor stub
    }

    public String getTemplateString() {
        String renderedTitle = titleTemplate.getTemplateString();
        return "<div class=\"page\">" +
                renderedTitle +
                "<article class=\"content\">$content</article></div>";
    }
}

interface TemplateRenderer {
    String render(String templateString, Map<String, Object> arguments);
}

class TwigRenderer implements TemplateRenderer {
    public String render(String templateString, Map<String, Object> arguments) {
        //String render;
        for (Map.Entry<String, Object> entry : arguments.entrySet()) {
            String name = entry.getKey();
            String field = (String) entry.getValue();
            templateString=templateString.replace("{{ "+name+" }}",field);
        }
        return templateString;
    }
}

class PHPTemplateRenderer implements TemplateRenderer {
    public String render(String templateString, Map<String, Object> arguments) {
        for (Map.Entry<String, Object> entry : arguments.entrySet()) {
            String name = entry.getKey();
            String field = (String) entry.getValue();
            templateString=templateString.replace("<?= $"+name+"; ?>",field);
            templateString=templateString.replace("$"+name,field);
        }
        return templateString;
    }
}

class Page {
    public String title;
    public String content;

    public Page(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String render(TemplateFactory factory) {
        PageTemplate pageTemplate = factory.createPageTemplate();
        TemplateRenderer renderer = factory.getRenderer();

        return renderer.render(pageTemplate.getTemplateString(), Map.of("title", title, "content", content));
    }
}
