import java.util.Scanner;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Facade {
    private Database database;
    private Engine engine;
    private LinkedList<User> users;
    
    Facade()
    {
        database= new Database();
        engine = new Engine();
        users = new LinkedList<User>();
    }
    
    public LinkedList<Integer> getRegisteredNumbers()
    {
        return database.getRegisteredNumbers();
    }
    
    public int isValid(String ID, String PW)
    {
        int x;
        x=database.isValid(ID, PW);
        return x;
    }
    
    public void start()
    {
        engine.start();
    }
    
   public void updateUsers()
   {
       engine.updateUsers(users);
   }
   
   public void updateEngine()
   {
        engine = null;
        engine = new Engine();
	engine.updateUsers(users);
	engine.start();
   }
   public void Add(User x)
   {
       users.add(x);
   }
   
   public int Size()
   {
       int x = users.size();
       return x;
   }
   
   public LinkedList<User> ListReturn()
   {
       return users;
   }
   
   public int getFirstNumber()
   {
       return users.getFirst().getNumber();
   }
   
   public void removeFirst()
   {
       users.removeFirst();
   }
   
}
