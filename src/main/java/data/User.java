package main.java.data;

public class User {

    protected String name;
    private boolean isAuthenticated;

    //Constructor
    public User(String userName){
        this.name = userName;
        if(userName == null)
            System.out.println("Anonymous");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean autenthicate(String password) {
        this.isAuthenticated = false;
        return false;
    }

    public boolean isNamed (String name){
        if(name.equals(this.name)){
            return true;
        }else{
            return false;
        }
    }

    public void greet(){
        System.out.println("Hello " + this.name + "! \n Welcome to our Warehouse Database. \n If you don't find what you are looking for, |n please ask one of our staff members to assist you");
    }

    public void bye() {
        System.out.println("Thank you!!");
    }
}
