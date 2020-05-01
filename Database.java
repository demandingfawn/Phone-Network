import java.util.Arrays;
import java.util.LinkedList;

public class Database {
    private LinkedList<Integer> RegisteredNumbers = new LinkedList<Integer>(Arrays.asList(414, 570, 200, 294, 726, 534));
    private LinkedList<String> Usernames = new LinkedList<String>(Arrays.asList("admin", "user"));
    private LinkedList<String> Passwords = new LinkedList<String>(Arrays.asList("admin", "user"));

    public LinkedList<Integer> getRegisteredNumbers(){
        return this.RegisteredNumbers;
    }
    public void addNumber(Integer number){
        this.RegisteredNumbers.add(number);
    }
    public int getTotalUsers(){
        return this.RegisteredNumbers.size();
    }

    //return true if Username and password combination is valid
    public int isValid(String ID, String PW) {
        for(int i = 0; i<this.Usernames.size(); i++) {
            if(ID.equals(Usernames.get(i))) {
                if(PW.equals(Passwords.get(i))) {
                    return i+1;
                }
            }
        }
        return 0;

    }


}
