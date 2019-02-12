package io.finder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FinderApp {

    public Finder getFinder(String s) {
        Map<String, Finder> finds = null;
        try {
            finds = Map.of(
                    "-f", new FullCompareFinder(),
                    "-m", new MaskFinder(),
                    "-r", new RegexFinder());
        } catch (NullPointerException e) {
            System.out.println("keys incorrect.");
        }
        return finds.get(s);
    }

    private class FullCompareFinder implements Finder {
        @Override
        public void find(String startDirectory, String searchString, String logNameFile) throws IOException {
            PrintStream ps = new PrintStream(new FileOutputStream(new File(logNameFile)));
            Files.find(Paths.get(startDirectory), Integer.MAX_VALUE, ((path, bfa) -> bfa.isRegularFile()
                    && path.getFileName().toString().equalsIgnoreCase(searchString)
            )).forEach(path -> ps.print(path.toString()));
            ps.close();
        }
    }

    private class MaskFinder implements Finder {
        @Override
        public void find(String startDirectory, String searchString, String logNameFile) throws IOException {
            PrintStream ps = new PrintStream(new FileOutputStream(new File(logNameFile)));
            Files.find(Paths.get(startDirectory), Integer.MAX_VALUE, ((path, bfa) -> bfa.isRegularFile()
                    && path.getFileName().toString().toLowerCase().contains(searchString.toLowerCase())
            )).forEach(path -> ps.print(path.toString()));
            ps.close();
        }
    }

    private class RegexFinder implements Finder {
        @Override
        public void find(String startDirectory, String searchString, String logNameFile) throws IOException {
            PrintStream ps = new PrintStream(new FileOutputStream(new File(logNameFile)));
            Files.find(Paths.get(startDirectory), Integer.MAX_VALUE, ((path, bfa) -> bfa.isRegularFile()
                    && path.getFileName().toString().matches(searchString)
            )).forEach(path -> ps.print(String.format("%s%s", path.toString(), System.lineSeparator())));
            ps.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String info = "To search you need to use the keys, example string: -d {main search directory}, -n {search string}"
                + "(further search method) - m {mask search}, -f {full compare}, -r {regular expression}"
                + "-o {result file name}"
                + System.lineSeparator()
                + "your result will be like: -d c:/ -n *.txt -m -o log.txt";
        System.out.println(info);
        boolean search = true;
        while (search) {
            if (args.length == 7) {
                if (args[0].equals("-d") && args[2].equals("-n") && args[5].equals("-o")
                        && args[4].equals("-m") || args[4].equals("-f") || args[4].equals("-r")) {
                    FinderApp app = new FinderApp();
                    app.getFinder(args[4]).find(args[1], args[3], args[6]);
                    search = false;
                }
            }
        }
    }
}
