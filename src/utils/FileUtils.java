package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    public static List<String> fileToList(String path){
        List<String> lines = new ArrayList<>();
        try{
            File file = new File(path);
            System.out.println(file.getAbsolutePath());
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
            scanner.close();
//            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        }catch (IOException e){
            System.out.println("Invalid path.");
            e.printStackTrace();
            lines = new ArrayList<>();
        }
        return lines;
    }
}
