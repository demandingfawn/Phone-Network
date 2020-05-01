import java.util.Random;
import java.util.LinkedList;

public class Engine extends Thread {
    private LinkedList<User> users;
    private LinkedList<Call> ongoingCalls = new LinkedList<Call>();
    Random random = new Random();

    public void updateUsers(LinkedList<User> users){
        this.users = users;
    }

    public void run(){
        int user1;
        int user2;
        try{
            while(true){
                sleep(random.nextInt(5000) + 5000);
                user1 = random.nextInt(users.size());
                user2 = random.nextInt(users.size());

                //if the users generated are different and they are both available then they call each other
                if ((user1 != user2) && (users.get(user1).getAvailability()) && (users.get(user2).getAvailability())){
                    ongoingCalls.add(new Call(users.get(user1), users.get(user2)));
                    ongoingCalls.getLast().start();
                    //TODO: remove the ongoing call from the list once it is done
                }
                //TODO: every now and then display that a user called another user who was busy
            }
        }
        catch(Exception ignored){ }
    }
}