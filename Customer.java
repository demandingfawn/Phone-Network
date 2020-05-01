import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Customer implements observer{
	User user;
	System system;
	Customer(System sys, int num){
		system = sys;
		user = new User(num);
	}
	void makeCall() {

    }
    
    void answerCall() {

    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		try {
    		PhoneSystemLog templ = new PhoneSystemLog();
    		String line = templ.nextL();
    		while(line != null) {
    			
    			//tokenize each word (separate by space)
    			//and check if the token is same to given phone number
    			char[] temp = line.toCharArray();
    			String token = "";
    			for(char a:temp) {
    				if(a != ' ') {
    					token = token + a;
    				}
    				else {
    					if(token.equals(String.valueOf(user.getNumber()))) {
        					java.lang.System.out.println(line);
        					line = templ.nextL();
        					continue;
    					}
    					token = "";
    				}
    			}
    			line = templ.nextL();
        	}
    	}
    	catch(Exception e){
            java.lang.System.out.println("Error creating base log file.");
        }
	}
}