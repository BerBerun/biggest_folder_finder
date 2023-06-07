package org.example;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String[] units = new String[] {"b", "kb", "mb", "gb", "tb"};
    public static void main(String[] args) {
       String folderPath = "C:/steam/appcache";
       File file = new File(folderPath);
       Node root = new Node(file);
       FolderSizeCalculator folderSizeCalculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(folderSizeCalculator);
        System.out.println(root);

    }
}