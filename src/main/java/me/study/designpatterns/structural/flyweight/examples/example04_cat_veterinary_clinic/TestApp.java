package me.study.designpatterns.structural.flyweight.examples.example04_cat_veterinary_clinic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;

public class TestApp {
    public static void main(String[] args) {
        CatDataBase db = new CatDataBase();

        System.out.println("Client: Let's see what we have in \"cats.csv\".");

        // Code for reading from cats.csv and adding cats to the database
        try {
            // open input stream test.txt for reading purpose.
            URL path = TestApp.class.getResource("cats.csv");
            File f = new File(path.getFile());
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                var columns = line.split(",");
                db.addCat(
                        columns[0],
                        columns[1],
                        columns[2],
                        columns[3],
                        columns[4],
                        columns[5],
                        columns[6],
                        columns[7],
                        columns[8]);
            }

            reader.close();
            // currentElement = this.reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\nClient: Let's look for a cat named \"Siri\".");
        Cat cat = db.findCat(Map.of("name", "Siri"));
        if (cat != null) {
            cat.render();
        }

        System.out.println("\nClient: Let's look for a cat named \"Bob\".");
        cat = db.findCat(Map.of("name", "Bob"));
        if (cat != null) {
            cat.render();
        }
    }
}

class CatVariation {
    public String breed;
    public String image;
    public String color;
    public String texture;
    public String fur;
    public String size;

    public CatVariation(String breed, String image, String color, String texture, String fur, String size) {
        this.breed = breed;
        this.image = image;
        this.color = color;
        this.texture = texture;
        this.fur = fur;
        this.size = size;
    }

    public void renderProfile(String name, String age, String owner) {
        System.out.println("= " + name + " =");
        System.out.println("Age: " + age);
        System.out.println("Owner: " + owner);
        System.out.println("Breed: " + this.breed);
        System.out.println("Image: " + this.image);
        System.out.println("Color: " + this.color);
        System.out.println("Texture: " + this.texture);
    }
}

class Cat {
    public String name;
    public String age;
    public String owner;
    private CatVariation variation;

    public Cat(String name, String age, String owner, CatVariation variation) {
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.variation = variation;
    }

    public boolean matches(Map<String, String> query) {
        for (Map.Entry<String, String> entry : query.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals("name") && !this.name.equals(value)) {
                return false;
            } else if (key.equals("age") && !this.age.equals(value)) {
                return false;
            } else if (key.equals("owner") && !this.owner.equals(value)) {
                return false;
            } else if (key.equals("breed") && !this.variation.breed.equals(value)) {
                return false;
            } else if (key.equals("image") && !this.variation.image.equals(value)) {
                return false;
            } else if (key.equals("color") && !this.variation.color.equals(value)) {
                return false;
            } else if (key.equals("texture") && !this.variation.texture.equals(value)) {
                return false;
            }
        }
        return true;
    }

    public void render() {
        this.variation.renderProfile(this.name, this.age, this.owner);
    }
}

class CatDataBase {
    private List<Cat> cats = new ArrayList<>();
    private Map<String, CatVariation> variations = new HashMap<>();

    public void addCat(String name, String age, String owner, String breed, String image, String color, String texture,
            String fur, String size) {
        CatVariation variation = getVariation(breed, image, color, texture, fur, size);
        cats.add(new Cat(name, age, owner, variation));
        System.out.println("CatDataBase: Added a cat (" + name + ", " + breed + ").");
    }

    public CatVariation getVariation(String breed, String image, String color, String texture, String fur,
            String size) {
        String key = getKey(breed, image, color, texture, fur, size);
        if (!variations.containsKey(key)) {
            variations.put(key, new CatVariation(breed, image, color, texture, fur, size));
        }
        return variations.get(key);
    }

    private String getKey(String breed, String image, String color, String texture, String fur, String size) {
        return breed + "_" + image + "_" + color + "_" + texture + "_" + fur + "_" + size;
    }

    public Cat findCat(Map<String, String> query) {
        for (Cat cat : cats) {
            if (cat.matches(query)) {
                return cat;
            }
        }
        System.out.println("CatDataBase: Sorry, your query does not yield any results.");
        return null;
    }
}
