package me.study.designpatterns.structural.composite.examples.example02_file_system;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class TestApp {

    public static void main(String[] args) {
        File file1 = new File("File1", 10);
        File file2 = new File("File2", 10);
        File file3 = new File("File3", 10);

        Folder folder1 = new Folder("Folder1");
        folder1.add(file1);

        Folder folder2 = new Folder("Folder2");
        folder2.add(file2);
        folder2.add(file3);
        folder2.add(folder1);

        print(folder2, 0);
        folder2.search("rose");

    }

    private static void print(Component component, int indentation) {
        for (var i = 0; i < indentation; i++)
            System.out.print("\t");
        System.out.println(component);

        for (var j = 0; j < component.getNumberChildNodes(); j++) {
            var child = component.getChild(j);
            print(child, indentation + 1);
        }
    }

    static abstract class Component {
        protected final String name;

        public String getName() {
            return name;
        }

        public Component(String name) {
            this.name = name;
        }

        abstract void search(String keyword);

        /**
         * Get the size of the node
         *
         * @return the value
         */
        abstract double getSize();

         public Component getChild(int index) {
            throw new RuntimeException("A leaf doesn't have any children!");
        }
        /**
         * Get the number of children
         *
         * Standard implementation (for leaves): 0
         *
         * Composite implements its own version
         *
         * @return number of children
         */
        public int getNumberChildNodes() {
            return 0;
        }
    }

    static class File extends Component {
        private double size = 0.0;

        public File(String name) {
            super(name);
        }

        public File(String name, double size) {
            super(name);
            this.size = size;
        }

        @Override
        public void search(String keyword) {
            System.out.printf("Searching for keyword %s in file %s\n", keyword, name);
        }

        public String getName() {
            return name;
        }

        @Override
        public double getSize() {
            return size;
        }
        @Override
        public String toString() {
            return  name + ": " + size;
        }
    }

    static class Folder extends Component {
        private List<Component> components = new ArrayList<>();
        private double size = 0.0;

        public Folder(String name) {
            super(name);
        }

        @Override
        public void search(String keyword) {
            System.out.printf("Searching recursively for keyword %s in folder %s\n", keyword, name);
            for (Component composite : components) {
                composite.search(keyword);
            }
        }

        public void add(Component c) {
            components.add(c);
        }

        @Override
        public double getSize() {
            size = 0.0;
            for (Component component : components) {
                size += component.getSize();
            }
            return size;
        }
        @Override
        public Component getChild(int index) {
            return components.get(index);
        }
        @Override
        public int getNumberChildNodes() {
            return components.size();
        }
        @Override
        public String toString() {
            return  name + ": " + getSize();
        }
    }
}
