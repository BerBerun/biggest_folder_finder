package org.example;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {
    private Node node;

    public FolderSizeCalculator( Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        File folder = node.getFolder();
        if (folder.isFile()) {
            long lenght = folder.length();
            node.setSize(lenght);
            return folder.length();
        }

        long sum = 0;
        List<FolderSizeCalculator> subTasks = new LinkedList<>();

        File[] files = folder.listFiles();
        for (File file : files) {
            Node child = new Node(file);
            FolderSizeCalculator tast = new FolderSizeCalculator(child);
            tast.fork();
            subTasks.add(tast);
            node.addChildren(child);
        }
        for(FolderSizeCalculator task : subTasks) {
            sum += task.join();
        }

        node.setSize(sum);
        return sum;
    }
}
