import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class PhoneSystemLog extends subject{
    private File file;
    private BufferedReader reader;
    private LinkedList<String> log = new LinkedList();
    PhoneSystemLog() throws IOException{
        File file = new File("PhoneSystemLog.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String temp = reader.readLine();
        while(temp != null) {
            log.add(temp);
            temp = reader.readLine();
        }
    }

    public String nextL() {
        if(log.isEmpty()) {
            return null;
        }
        else {
            String temp = log.getFirst();
            log.removeFirst();
            return temp;
        }

    }



}
