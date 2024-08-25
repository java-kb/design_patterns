package me.study.designpatterns.behavioral.iterator.examples.example4_csv_iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;

public class TestApp {
    public static void main(String[] args) {
        var csv = new CsvIterator("cats.csv");
        while (csv.hasNext()) {
            for (String item : csv.next()) {
                System.out.print(item +" ");
            }
            System.out.println();
        }
    }
}

class CsvIterator implements Iterator {
    String delimiter = ",";
    BufferedReader reader;
    String currentElement = null;

    public CsvIterator(String file) {
        try {
            // open input stream test.txt for reading purpose.
            URL path = CsvIterator.class.getResource(file);
            File f = new File(path.getFile());
            this.reader = new BufferedReader(new FileReader(f));
            // currentElement = this.reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public CsvIterator(String file, String delimiter) {
        this(file);
        this.delimiter = delimiter;
    }

    @Override
    public boolean hasNext() {
        try {
            return ((currentElement = reader.readLine()) != null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String[] next() {
        return currentElement.split(delimiter);
    }

}