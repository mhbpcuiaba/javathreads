package br.com.mhbp.threads.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileSearcher extends RecursiveTask<List<String>> {

    private final String path;
    private final String extension;

    public FileSearcher(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String> listOfFileNames = new ArrayList<>();
        File[] files = new File(path).listFiles();

        if (files == null) return listOfFileNames;

        List<FileSearcher> tasks = new ArrayList<>();

        for (File file : files) {

            String absolutePath = file.getAbsolutePath();

            if (file.isDirectory()) {
                FileSearcher task = new FileSearcher(absolutePath, extension);
                task.fork();
                tasks.add(task);
            } else if (file.getName().endsWith(extension)) {
                listOfFileNames.add(absolutePath);
            }
        }
        assembleResults(listOfFileNames, tasks);
        return listOfFileNames;
    }

    private void assembleResults(List<String> listOfFileNames, List<FileSearcher> tasks) {
        for (FileSearcher task : tasks) {
            Collection<String> results = task.join();
            listOfFileNames.addAll(results);
        }
    }
}

class Main {
    public static void main(String[] args) {
        List<String> list = new FileSearcher("/", ".txt").compute();
        System.out.println("Files with extension .txt");
        for (String fileWithAbsolutePath : list) {
            System.out.println(fileWithAbsolutePath);
        }
    }
}