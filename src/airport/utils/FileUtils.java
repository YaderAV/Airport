package airport.utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author saraibanez
 */

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static  String readFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            return "[]";
        }
    }

    public static void writeFile(String path, String content) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
