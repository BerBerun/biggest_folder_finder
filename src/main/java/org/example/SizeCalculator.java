package org.example;

import java.io.File;

public class SizeCalculator {
    private static String[] units = new String[] {"b", "kb", "mb", "gb", "tb"};
    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }

    public static String getHumanReadableSize(double size) {
        int index = 0;
        while (size > 1024 && index < units.length - 1) {
            size /= 1024.0;
            index++;
        }
        return String.format(Math.round(size * 100)/100. + units[index]);
    }

    public static long getSizeFromHumanReadable(String size) {
        String sizeFactor = String.valueOf(size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0));
        long lenght = Long.parseLong(size
                .replaceAll("[^0-9]", ""));
        long sum = 0;
        switch (sizeFactor) {
            case "b" ->
                    sum += lenght;
            case "k" ->
                    sum +=  lenght * 1024;
            case "m" ->
                    sum +=  lenght * 1024 * 1024;
            case "g" ->
                    sum +=  lenght * 1024 * 1024 * 1024;
        }
        return sum;
    }
}
