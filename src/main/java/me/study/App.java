package me.study;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;

public class App {
    public static void main(String[] args) {
        // Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();

        try {
            // Instantiating the URL class
            URL url = new URL("https://www.imdb.com/feature/genre/");
            // Retrieving the contents of the specified page
            URLConnection urlcon = url.openConnection();
            urlcon.addRequestProperty("User-Agent", "A");
            /*
             * Scanner sc = new Scanner(urlcon.getInputStream());
             * while (sc.hasNext()) {
             * sb.append(sc.next());
             * // System.out.println(sc.next());
             * }
             */
            // BufferedReader br = new BufferedReader(new InputStreamReader
            // (urlcon.getInputStream()));
            // String i;
            // while ((i = br.readLine()) != null)
            // {
            // sb.append(i);
            // }
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int length; (length = urlcon.getInputStream().read(buffer)) != -1;) {
                result.write(buffer, 0, length);
            }
            // StandardCharsets.UTF_8.name() > JDK 7
            String r=result.toString("UTF-8");
            System.out.println(result.toString("UTF-8").indexOf("/search/title?genres="));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Retrieving the String from the String Buffer object

        System.out.println(sb.toString().indexOf("/search/title?genres="));
        String s = "\"hello\"";
        Pattern pattern = Pattern.compile("href=\\\"(/search/title\\?genres=.*?)\\\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sb.toString());
        // using Matcher find(), group(), start() and end() methods
        while (matcher.find()) {
            System.out.println("Found the text \"" + matcher.group(1));
        }
    }
}
