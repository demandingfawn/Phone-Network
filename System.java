
import java.io.IOException;

import java.util.Scanner;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class System {

    private Database database = new Database();
    private LinkedList<User> users = new LinkedList<User>();
    private Engine engine = new Engine();
    private int identity = 0; // 1 = administrator , 2 = customer;
    
	public System() {
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
                            "570 was on a call with 726 for 6 minutes.1111\n");	// '\n' was added at the end of the sentence.
            writer.close();
        }
        catch(Exception e){
            java.lang.System.out.println("Error creating base log file.");
        }

        engine.updateUsers(users);
        engine.start();
	}
    public static void Write(String fileContent) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("PhoneSystemLog.txt", true));
        writer.append(fileContent);
        writer.close();
    }
    
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    
    //return Identity
    public int getIdentity() {
    	return this.identity;
    }
    
    //login
    public void login() {
    	Scanner input = new Scanner(java.lang.System.in);
        while(true) {
        	String ID;
        	String PW;
        	int temp;
        	//check if given combination is valid
        	java.lang.System.out.println("Type your ID:");
        	ID = input.nextLine();
        	java.lang.System.out.println("Type your password");
        	PW = input.nextLine();
        	temp = database.isValid(ID, PW);
        	if(temp != 0) {
        		identity = temp;
        		break;
        	}
        	else {	//temp = 0 when it's not valid
        		java.lang.System.out.println("Invalid ID or Password");
        	}
        }
        java.lang.System.out.println("loged in as: " + String.valueOf(identity));
    }
	public void makeCall() {

    }
    
    public void answerCall() {

    }
    
    //show logs that have phone number of customer
    public void viewActivity(int phoneNum) {
    	try {
    		File file = new File("PhoneSystemLog.txt");
    		BufferedReader reader = new BufferedReader(new FileReader(file));
        	String line = reader.readLine();
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
    					if(token.equals(String.valueOf(phoneNum))) {
        					java.lang.System.out.println(line);
        					line = reader.readLine();
        					continue;
    					}
    					token = "";
    				}
    			}
        		line = reader.readLine();
        	}
    	}
    	catch(Exception e){
            java.lang.System.out.println("Error creating base log file.");
        }
    }
    
    //add one number to user list and restart engine
    public void addNumber() {
    	Scanner input = new Scanner(java.lang.System.in);
    	java.lang.System.out.println("Type a number you want to add");
    	int number =  Integer.parseInt(input.nextLine());
    	boolean check = false;
    	for(User x:users) {
    		if(x.getNumber().equals(number)) {
    			check = true;
    		}
    	}
    	if(check == true) {
    		java.lang.System.out.println("number already exists on the system");
    		return;
    	}
    	User temp = new User(number);
    	users.add(temp);
    	
    	engine = null;
		engine = new Engine();
		engine.updateUsers(users);
		engine.start();
    	java.lang.System.out.println("now there is(are) " + String.valueOf(users.size()) + " users");
    }
    
    //remove one number in the first of the list, and restart engine
    public void deleteNumbre() {
    	if(users.size()>0) {
        	users.removeFirst();
    	}
    	else {
    		java.lang.System.out.println("There is no more number to delete");
    		return;
    	}
		engine = null;
		engine = new Engine();
		engine.updateUsers(users);
		engine.start();
		
		java.lang.System.out.println("now there is(are) " + String.valueOf(users.size()) + " users");
    }
    
    public void logOut()  {
        
    }
    
    //show all logs
    public void viewLog() {
    	try {
    		File file = new File("PhoneSystemLog.txt");
    		BufferedReader reader = new BufferedReader(new FileReader(file));
        	String line = reader.readLine();
    		while(line != null) {
        		java.lang.System.out.println(line);
        		line = reader.readLine();
        	}
    	}
    	catch(Exception e){
            java.lang.System.out.println("Error creating base log file.");
        }
    }

    

    public static void main(String args[]) {
    	/*
        //TODO: put a base log into the log file
        //TODO: numbers beginning with a 0
        System system = new System();
        Database database = new Database();
        LinkedList<User> users = new LinkedList<User>();
        Engine engine = new Engine();
        int identity; // 1 = administrator , 2 = customer;

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
                            "570 was on a call with 726 for 6 minutes.\n");	// '\n' was added at the end of the sentence.
            writer.close();
        }
        catch(Exception e){
            java.lang.System.out.println("Error creating base log file.");
        }

        engine.updateUsers(users);
        engine.start();
        */
		System system = new System();
        system.login();
        
        Scanner input = new Scanner(java.lang.System.in);
        switch(system.identity) {
        	case 1:	//when login as administrator
        		while(true) {
        			java.lang.System.out.println("\n\nchoose what do to: \n1) view log \n2) add number \n3) delete number \n4) log out \n5)exit");
        			String choice = input.nextLine();
        			int temp = Integer.parseInt(choice);

        			if(temp == 1) {	//view log
        				system.viewLog();
        			}else if(temp ==2) {	//add number
        				system.addNumber();
        			}else if(temp ==3) {	//delete number
        				system.deleteNumbre();
                                }else if(temp ==4) {    //log out of menu
                                        system.login();
        			}else if(temp ==5) {	//exit
        				break;
        			}else {
        				java.lang.System.out.println("input is not valid");
        			}
        		}
        		break;
			case 2:	//when login as customer
				int customerNum = 570;
				java.lang.System.out.println("input is not valid");
        		while(true) {
        			java.lang.System.out.println("\n\nchoose what do to: \n1) view activity \n2) make call \n3) accept call \n4) log out \n5)exit");
        			String choice = input.nextLine();
        			int temp = Integer.parseInt(choice);
        			if(temp == 1) {
        				system.viewActivity(customerNum);
        			}else if(temp ==2) {
        				system.makeCall();
        			}else if(temp ==3) {
        				system.answerCall();
                                }else if(temp ==4) {    //log out of menu
                                        system.login();
        			}else if(temp ==5) {
        				break;
        			}else {
        				java.lang.System.out.println("input is not valid");
        			}
        		}
        		break;
        }
        	
    }
}
