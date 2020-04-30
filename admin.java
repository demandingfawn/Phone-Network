import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class admin implements observer{
	static System system;
	admin(System sys){
		system = sys;
	}
	//add one number to user list and restart engine
	public static void addNumber() {
		Scanner input = new Scanner(java.lang.System.in);
    	java.lang.System.out.println("Type a number you want to add");
        int number =  Integer.parseInt(input.nextLine());
		boolean check = system.checkExist(number);
		if(check) {
    		java.lang.System.out.println("number already exists on the system");
    		return;
		}
		else {
			system.addUser(number);
			system.updateEngine();
			
		}
	}
	
	//remove one number in the first of the list, and restart engine
	public static void deleteNumber() {
		system.removeUser();
		system.updateEngine();
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
		try {
			PhoneSystemLog templ = new PhoneSystemLog();
        	String line = templ.nextL();
    		while(line != null) {
        		java.lang.System.out.println(line);
        		line = templ.nextL();
        	}
    	}
    	catch(FileNotFoundException e){
            java.lang.System.out.println("Error creating base log file. file not found");
        }
		catch(IOException e){
            java.lang.System.out.println("Error creating base log file. IO");
        }

	}

}
