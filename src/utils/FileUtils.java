package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    public static List<String> fileToList(String path){
        List<String> lines = new ArrayList<>();
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
                lines.add(scanner.nextLine());
            scanner.close();
        }catch (NullPointerException | FileNotFoundException e){
            System.out.println("Invalid path.");
            e.printStackTrace();
            lines = new ArrayList<>();
        }
        return lines;
    }


    public static void writeObjectToFile(Object o, String objName){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("resources/" + objName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
            System.out.println(o.toString() + "\n\nObject written to file.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
