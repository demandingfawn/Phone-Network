
import java.io.IOException;
import java.util.Random;

public class Call extends Thread {
    Random random = new Random();
    User u1;
    User u2;

    Call(User a, User b){
        this.u1 = a;
        this.u2 = b;
    }

    public void run(){
        u1.setIsAvailable(false);
        u2.setIsAvailable(false);
        Integer time = 0;
        try {
            time = random.nextInt(18000) + 2000;
            sleep(time);
        }
        catch(Exception ignored){}
        time = time/1000;
        String str = "";
        str += u1.getNumber().toString();
        str += " was on a call with ";
        str += u2.getNumber().toString();
        str += " for ";
        str += time.toString();
        str += " minutes.\n";
        try {
            System.Write(str);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        u1.setIsAvailable(true);
        u2.setIsAvailable(true);
    }
}
