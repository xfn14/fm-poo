import objects.Team;
import objects.exceptions.FileIOException;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try{
            Map<String, Team> teams = Parser.loadLogFile("resources/logs.txt");
            System.out.println(teams.get("Bartok F. C."));
//            for(Map.Entry<String, Team> entry : teams.entrySet()){
//                System.out.println(entry.getKey() + " : " + entry.getValue());
//            }
        }catch (FileIOException e){
            System.out.println(e.getMessage());
        }
    }
}
