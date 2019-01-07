package io.zout;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipPacker {

    private final static Logger L = LogManager.getLogger(ZipPacker.class.getName());
    private String path;
    private List<String> format = new ArrayList<>();
    private String zipTargetName;

    public ZipPacker(String path, String format, String zipTargetName) {
        this.path = correctPath(path);
        setFormat(format);
        this.zipTargetName = this.path + zipTargetName;
    }

    public static void main(String... args) {
        String path = null;
        String format = "";
        String name = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                path = args[i + 1];
            } else if (args[i].equals("-e")) {
                while (!args[i + 1].equals("-o")) {
                    format = String.format("%s%s", format, args[i + 1]);
                    i++;
                }
            } else if (args[i].equals("-o")) {
                name = args[i + 1];
            }
        }
        ZipPacker formater = new ZipPacker(path, format, name);
        formater.puckUp();
    }

    public void puckUp() {
        try (var fout = new FileOutputStream(new File(this.zipTargetName));
             ZipOutputStream zout = new ZipOutputStream(fout)) {
            L.info(String.format("...СОЗДАНИЕ.АРХИВА.(name: %s)...", this.zipTargetName.replace(this.path, "")));
            this.addStructure(zout, new File(this.path));
        } catch (FileNotFoundException e) {
            L.error(e.getMessage(), e);
        } catch (IOException e) {
            L.error(e.getMessage(), e);
        }
        L.info(String.format("...АРХИВ.СОЗДАН!...%s                 "
                + "%s", System.lineSeparator(), this.zipTargetName));
    }

    private void addStructure(ZipOutputStream zout, File fileSource) throws IOException {
        Files.walk(Paths.get(fileSource.getPath()))
                .filter(Files::isRegularFile)
                .filter(path -> isAllowedFormat(path, this.format))
                .forEach(path -> {
                    L.info(String.format("...%s...добавление.в.структуру.архива...", path.getFileName()));
                    try (var reader = new FileInputStream(path.toFile())) {
                        zout.putNextEntry(new ZipEntry(
                                path.toString().replace(fileSource.getPath(), "")));
                        L.info(String.format("...%s...добавлен!...", path.getFileName()));
                        var buffer = new byte[reader.available()];
                        long langth;
                        L.info(String.format("...%s.размер(%s Б)...начало.компрессии...", path.getFileName(), path.toFile().length()));
                        while ((langth = reader.read(buffer)) > 0) {
                            zout.write(buffer);
                        }
                        L.info("...Компресия.завершена...");
                        zout.closeEntry();
                    } catch (FileNotFoundException e) {
                        L.error(e.getMessage(), e);
                    } catch (IOException e) {
                        L.error(e.getMessage(), e);
                    }
                });
    }

    private boolean isAllowedFormat(Path path, List<String> format) {
        boolean result = false;
        for (String s : format) {
            if (path.getFileName().toString().endsWith(s)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private String correctPath(String path) {
        if (path.charAt(0) != File.separator.charAt(0)) {
            path = path.replaceAll("\\\\", File.separator);
        }
        path = path.charAt(0) != File.separator.charAt(0) ? File.separator + path : path;
        return path.charAt(path.length() - 1) == File.separator.charAt(0) ? path : path + File.separator;
    }

    private void setFormat(String format) {
        this.format = Arrays.stream(format.split(","))
                .map(s -> String.format(".%s", s)
                        .replaceAll(" ", "")
                        .strip())
                .collect(Collectors.toList());
    }
}

