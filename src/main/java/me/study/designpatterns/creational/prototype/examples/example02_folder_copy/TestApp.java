package me.study.designpatterns.creational.prototype.examples.example02_folder_copy;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        File file1 = new File("File1");
        File file2 = new File("File2");
        File file3 = new File("File3");

        Folder folder1 = new Folder("Folder1");
        folder1.addChild(file1);

        Folder folder2 = new Folder("Folder2");
        folder2.addChild(folder1);
        folder2.addChild(file2);
        folder2.addChild(file3);

        System.out.println("\nPrinting hierarchy for Folder2");
        folder2.print("  ");

        Inode cloneFolder = folder2.clone();
        System.out.println("\nPrinting hierarchy for clone Folder");
        cloneFolder.print("  ");
    }
}

interface Inode {
    void print(String indentation);
    Inode clone();
}

class File implements Inode {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void print(String indentation) {
        System.out.println(indentation + name);
    }

    @Override
    public Inode clone() {
        return new File(name + "_clone");
    }
}

class Folder implements Inode {
    private List<Inode> children;
    private String name;

    public Folder(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(Inode child) {
        children.add(child);
    }

    @Override
    public void print(String indentation) {
        System.out.println(indentation + name);
        for (Inode child : children) {
            child.print(indentation + "  ");
        }
    }

    @Override
    public Inode clone() {
        Folder cloneFolder = new Folder(name + "_clone");
        for (Inode child : children) {
            cloneFolder.addChild(child.clone());
        }
        return cloneFolder;
    }
}

