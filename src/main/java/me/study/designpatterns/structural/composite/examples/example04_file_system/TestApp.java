package me.study.designpatterns.structural.composite.examples.example04_file_system;

import java.util.ArrayList;

import me.study.designpatterns.utils.ConsoleUtil;

public class TestApp {

    public static void main(String[] args) {
        ConsoleUtil.Title("Before");
        new Before().run();
        ConsoleUtil.Title("After");
        new After().run();
    }
}

class Before {
    public static StringBuilder compositeBuilder = new StringBuilder();

    public void run() {
        Directory music = new Directory("MUSIC");
        Directory scorpions = new Directory("SCORPIONS");
        Directory dio = new Directory("DIO");
        File track1 = new File("Don't wary, be happy.mp3");
        File track2 = new File("track2.m3u");
        File track3 = new File("Wind of change.mp3");
        File track4 = new File("Big city night.mp3");
        File track5 = new File("Rainbow in the dark.mp3");
        music.add(track1);
        music.add(scorpions);
        music.add(track2);
        scorpions.add(track3);
        scorpions.add(track4);
        scorpions.add(dio);
        dio.add(track5);
        music.ls();
    }

    class File {
        private String name;

        public File(String name) {
            this.name = name;
        }

        public void ls() {
            System.out.println(Before.compositeBuilder + name);
        }
    }

    class Directory {
        private String name;
        private ArrayList includedFiles = new ArrayList();

        public Directory(String name) {
            this.name = name;
        }

        public void add(Object obj) {
            includedFiles.add(obj);
        }

        public void ls() {
            System.out.println(Before.compositeBuilder + name);
            Before.compositeBuilder.append("   ");
            for (Object obj : includedFiles) {
                // Recover the type of this object
                String name = obj.getClass().getSimpleName();
                if (name.equals("Directory")) {
                    ((Directory) obj).ls();
                } else {
                    ((File) obj).ls();
                }
            }
            Before.compositeBuilder.setLength(Before.compositeBuilder.length() - 3);
        }
    }
}

class After {
    public static StringBuilder compositeBuilder = new StringBuilder();

    public void run() {
        Directory music = new Directory("MUSIC");
        Directory scorpions = new Directory("SCORPIONS");
        Directory dio = new Directory("DIO");
        File track1 = new File("Don't wary, be happy.mp3");
        File track2 = new File("track2.m3u");
        File track3 = new File("Wind of change.mp3");
        File track4 = new File("Big city night.mp3");
        File track5 = new File("Rainbow in the dark.mp3");
        music.add(track1);
        music.add(scorpions);
        music.add(track2);
        scorpions.add(track3);
        scorpions.add(track4);
        scorpions.add(dio);
        dio.add(track5);
        music.ls();
    }

    // Define a "lowest common denominator"
    interface AbstractFile {
        void ls();
    }

    // File implements the "lowest common denominator"
    class File implements AbstractFile {
        private String name;

        public File(String name) {
            this.name = name;
        }

        public void ls() {
            System.out.println(Before.compositeBuilder + name);
        }
    }

    // Directory implements the "lowest common denominator"
    class Directory implements AbstractFile {
        private String name;
        private ArrayList includedFiles = new ArrayList();

        public Directory(String name) {
            this.name = name;
        }

        public void add(Object obj) {
            includedFiles.add(obj);
        }

        public void ls() {
            System.out.println(Before.compositeBuilder + name);
            Before.compositeBuilder.append("   ");
            for (Object includedFile : includedFiles) {
                // Leverage the "lowest common denominator"
                AbstractFile obj = (AbstractFile) includedFile;
                obj.ls();
            }
            Before.compositeBuilder.setLength(Before.compositeBuilder.length() - 3);
        }
    }
}