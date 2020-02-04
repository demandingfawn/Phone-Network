
import java.io.IOException;
import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class System {

    public static void Write(String fileContent) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("PhoneSystemLog.txt", true));
        writer.append(fileContent);
        writer.close();
    }


    public static void main(String args[]) {
        //TODO: put a base log into the log file
        //TODO: numbers beginning with a 0
        System system = new System();
        Database database = new Database();
        LinkedList<User> users = new LinkedList<User>();
        Engine engine = new Engine();

        for (Object number : database.getRegisteredNumbers()) {
            users.add(new User((int)number));
        }

        //Create a base log file on the computer
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("PhoneSystemLog.txt"));
            writer.write("570 was on a call with 200 for 17 minutes.\n" +
                            "414 was on a call with 570 for 15 minutes.\n" +
                            "414 was on a call with 570 for 19 minutes.\n" +
                            "726 was on a call with 294 for 10 minutes.\n" +
                            "200 was on a call with 534 for 4 minutes.\n" +
                            "294 was on a call with 534 for 11 minutes.\n" +
                            "726 was on a call with 570 for 8 minutes.\n" +
                            "294 was on a call with 200 for 17 minutes.\n" +
                            "570 was on a call with 414 for 17 minutes.\n" +
                            "294 was on a call with 200 for 16 minutes.\n" +
                            "726 was on a call with 534 for 14 minutes.\n" +
                            "570 was on a call with 414 for 7 minutes.\n" +
                            "414 was on a call with 570 for 19 minutes.\n" +
                            "726 was on a call with 570 for 2 minutes.\n" +
                            "570 was on a call with 726 for 6 minutes.");
            writer.close();
        }
        catch(Exception e){
            java.lang.System.out.println("Error creating base log file.");
        }

        engine.updateUsers(users);
        engine.start();
    }
}
