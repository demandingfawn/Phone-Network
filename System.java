
import java.io.IOException;

import java.util.Scanner;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class System {

    private LinkedList<User> users = new LinkedList<User>();

    private Facade facade = new Facade();
    private int identity = 0; // 1 = administrator , 2 = customer;

    public System() {
        for (Object number : facade.getRegisteredNumbers()) {
            facade.Add(new User((int)number));
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

        facade.updateUsers();
        facade.start();
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
            temp = facade.isValid(ID, PW);
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
        java.lang.System.out.println("Please put in the number you want to call");
        Scanner numberin = new Scanner(java.lang.System.in);
        int caller = numberin.nextInt();
        boolean check = true;
        for(User x:facade.ListReturn()) {
            if(x.getNumber().equals(caller))
            {
                check = false;
            }
        }
        boolean freecall=false;
        for(User x:facade.ListReturn())
        {
            if (x.getNumber().equals(caller) && x.getAvailability())
            {
                freecall=true;
            }
        }
        if(caller>999 || caller<0 || check)
        {
            java.lang.System.out.println("That is not a valid number");
        }
        else if(!freecall)
        {
            java.lang.System.out.println("That number is not available");
        }
        else
        {
            for(User x:facade.ListReturn())
            {
                if (x.getNumber().equals(caller))
                {
                    x.setIsAvailable(false);
                    java.lang.System.out.println("Call started: Please hit a number to end");
                    int hangup = numberin.nextInt();
                    x.setIsAvailable(true);
                }
            }
        }
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
    //check if numbre exists in user list
    public boolean checkExist(int num) {
        boolean check = false;
        for(User x:facade.ListReturn()) {
            if(x.getNumber().equals(num)) {
                return true;
            }
        }
        return false;
    }
    //add given number to user list
    public void addUser(int number) {
        User temp = new User(number);
        facade.Add(temp);
    }
    //remove first user in the user list
    public int removeUser() {
        if(facade.Size()>0) {
            int temp = facade.getFirstNumber();
            facade.removeFirst();
            return temp;
        }
        else {
            java.lang.System.out.println("There is no more number to delete");
            return 0;
        }
    }
    //restart engine and print number of users
    public void updateEngine() {
        facade.updateEngine();
        java.lang.System.out.println("now there is(are) " + String.valueOf(users.size()) + " users");
    }

    public void logOut()  {

    }
    public static void main(String args[]) {
        System system = new System();
        Scanner input = new Scanner(java.lang.System.in);

        system.login();
        while(system.identity != 3) {
            switch (system.identity) {
                case 1:    //when login as administrator
                    admin currentAdmin = new admin(system);
                    while (true) {
                        java.lang.System.out.println("\n\nSelect an option: \n1) View log \n2) Add number \n3) Delete number \n4) Log out \n5) Exit");
                        String choice = input.nextLine();
                        int temp = Integer.parseInt(choice);

                        if (temp == 1) {    //view log
                            currentAdmin.update();
                        } else if (temp == 2) {    //add number
                            currentAdmin.addNumber();
                        } else if (temp == 3) {    //delete number
                            currentAdmin.deleteNumber();
                        } else if (temp == 4) {//log out of menu
                            system.login();
                            break;
                        } else if (temp == 5) {    //exit
                            system.identity = 3;
                            break;
                        } else {
                            java.lang.System.out.println("input is not valid");
                        }
                    }
                    break;
                case 2:    //when login as customer
                    int customerNum = 570;
                    Customer currentCustomer = new Customer(system, customerNum);
                    outer:
                    while (true) {
                        java.lang.System.out.println("\n\nSelect an option: \n1) View activity \n2) Make call \n3) Log out \n4) Exit");
                        String choice = input.nextLine();
                        int temp = Integer.parseInt(choice);
                        if (temp == 1) {
                            currentCustomer.update();
                        } else if (temp == 2) {
                            system.makeCall();
                        } else if (temp == 3) {    //log out of menu
                            system.login();
                            break outer;
                        } else if (temp == 4) {
                            system.identity= 3;
                            break;
                        } else {
                            java.lang.System.out.println("input is not valid");
                        }
                    }
                case 3: {
                    input.close();
                    java.lang.System.out.println("Thank You");
                    break;
                }
            }
        }
    }
}