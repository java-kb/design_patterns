package me.study.designpatterns.creational.builder.gui.example05_display_table;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TabDelimitedDataFile {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String[]> data = new ArrayList<>();
        data.add(new String[] { "Afghanistan", "AF", "AFG", "004", "Asia" });
        data.add(new String[] { "Åland Islands", "AX", "ALA", "248", "Europe" });
        data.add(new String[] { "Albania", "AL", "ALB", "008", "Europe" });
        data.add(new String[] { "Algeria", "DZ", "DZA", "012", "Africa" });
        data.add(new String[] { "American Samoa", "AS", "ASM", "016", "Polynesia" });
        data.add(new String[] { "Andorra", "AD", "AND", "020", "South Europe" });
        data.add(new String[] { "Angola", "AO", "AGO", "024", "Africa" });
        data.add(new String[] { "Anguilla", "AI", "AIA", "660", "Americas" });
        data.add(new String[] { "Antarctica", "AQ", "ATA", "010", "" });
        data.add(new String[] { "Argentina", "AR", "ARG", "032", "Americas" });

        Path p = Paths.get(TabDelimitedDataFile.class.getResource("BuilderDemo.dat").toURI());
        URL path = TabDelimitedDataFile.class.getResource("BuilderDemo.dat");
        File f = new File(path.getFile());
        try (PrintWriter writer = new PrintWriter(

                Files.newBufferedWriter(Paths.get("BuilderDemo.dat")))) {
            writer.printf("%s\t%s",
                    5, 10);
            writer.println();
            for (String[] row : data) {
                writer.printf("%s\t%s\t%s\t%s\t%s",
                        row[0], row[1], row[2], row[3], row[4]);
                writer.println();
            }
        }
    }
}