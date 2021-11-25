package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User{
    //Properties
    private final String password;
    private List<Employee> headOf;

    public Employee(String userName, String password) {
        super(userName);
        this.password = password;
        this.headOf = new ArrayList<Employee>();
    }

    @Override
    public boolean autenthicate(String password) {
        return password.equals(this.password);
    }

    public void order(String item, int amount) {
        System.out.println("You order of the item " +item + " an amount of " + amount);
    }

    public void greet(){
        System.out.println("Hello, " +  + "");
    }
}
