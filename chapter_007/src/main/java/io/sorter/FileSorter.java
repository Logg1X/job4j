package io.sorter;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileSorter implements Sorter {

    private final int clusterSize;

    public FileSorter(int clusterSize) {
        this.clusterSize = clusterSize;
    }

    @Override
    public void sort(File source, File distance) throws IOException {
        List<File> resultList = this.externalSorting(source);
        if (!resultList.isEmpty()) {
            try (var resultWriter = new BufferedWriter(new FileWriter(distance));
                 var resultReader = new BufferedReader(new FileReader(resultList.get(0)))) {
                String resultLines;
                while ((resultLines = resultReader.readLine()) != null) {
                    resultWriter.write(resultLines);
                    resultWriter.write(System.lineSeparator());
                }
            }
        }
    }

    private List<File> splitFile(File file) throws IOException {
        List<File> tempFiles = new ArrayList<>();
        try (var currentFile = new RandomAccessFile(file, "r")) {
            var currentFilePointer = currentFile.getFilePointer();
            var counter = 1;
            while (currentFilePointer != currentFile.length()) {
                tempFiles.add(File.createTempFile("file" + counter, ".txt"));
                var tempFile = tempFiles.get(counter - 1);
                try (var writer = new BufferedWriter(new FileWriter(tempFile))) {
                    while ((currentFilePointer = currentFile.getFilePointer()) < this.clusterSize * counter) {
                        if (currentFilePointer == currentFile.length()) {
                            break;
                        }
                        var temp = correctEncoding(currentFile.readLine());
                        writer.write(temp);
                        writer.write(System.lineSeparator());
                    }
                    counter++;
                }
            }
        }
        return tempFiles;
    }

    private List<String> getListFromFile(File file) throws IOException {
        List<String> result = new ArrayList<>();
        try (var input = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = input.readLine()) != null) {
                result.add(str);
            }
        }
        return result;
    }

    private String correctEncoding(String line) {
        return new String(line.getBytes(ISO_8859_1), UTF_8);
    }

    private List<File> sortInMemoryToList(List<File> files) throws IOException {
        List<File> utilityList = new ArrayList<>(files);
        var i = 0;
        for (File file : files) {
            var temp = this.getListFromFile(file);
            temp.sort(Comparator.comparingInt(String::length));
            while (i < utilityList.size()) {
                try (var currentFile = new RandomAccessFile(utilityList.get(i), "rw")) {
                    currentFile.seek(0);
                    for (String s : temp) {
                        currentFile.write(s.getBytes());
                        currentFile.writeBytes(System.lineSeparator());
                    }
                }
                i++;
                break;
            }
        }
        return utilityList;
    }

    private List<File> externalSorting(File sors) throws IOException {
        var temp = new LinkedList<>(sortInMemoryToList(splitFile(sors)));
        if (!temp.isEmpty()) {
            while (temp.size() != 1) {
                var current = File.createTempFile("TTT", ".txt");
                var checker = false;
                try (var currentfile = new BufferedWriter(new FileWriter(current));
                     var reader1 = new BufferedReader(new FileReader(temp.get(0)));
                     var reader2 = new BufferedReader(new FileReader(temp.get(1)))) {
                    String firstFileLine = reader1.readLine();
                    String secondFileLine = reader2.readLine();
                    while (!checker) {
                        if (firstFileLine != null & secondFileLine != null) {
                            if (firstFileLine.length() < secondFileLine.length()) {
                                currentfile.write(firstFileLine);
                                currentfile.write(System.lineSeparator());
                                firstFileLine = reader1.readLine();
                            } else if (firstFileLine.length() > secondFileLine.length()) {
                                currentfile.write(secondFileLine);
                                currentfile.write(System.lineSeparator());
                                secondFileLine = reader2.readLine();
                            } else {
                                currentfile.write(firstFileLine);
                                currentfile.write(System.lineSeparator());
                                currentfile.write(secondFileLine);
                                currentfile.write(System.lineSeparator());
                                firstFileLine = reader1.readLine();
                                secondFileLine = reader2.readLine();
                            }
                        }
                        if (firstFileLine == null && secondFileLine != null) {
                            currentfile.write(secondFileLine);
                            currentfile.write(System.lineSeparator());
                            secondFileLine = reader2.readLine();
                        }
                        if (secondFileLine == null && firstFileLine != null) {
                            currentfile.write(firstFileLine);
                            currentfile.write(System.lineSeparator());
                            firstFileLine = reader1.readLine();
                        }
                        if (firstFileLine == null && secondFileLine == null) {
                            checker = true;
                            currentfile.close();
                            temp.poll();
                            temp.poll();
                            temp.addFirst(current);
                        }
                    }
                }
            }
        }
        return temp;
    }
}
