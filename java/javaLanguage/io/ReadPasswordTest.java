import java.io.*;  
class ReadPasswordTest{  
public static void main(String args[]){  
Console c=System.console();  
System.out.println("Enter name: ");
String name = c.readLine();
System.out.println("Enter password: ");  
char[] ch=c.readPassword();  
String pass=String.valueOf(ch);//converting char array into string  
System.out.println("Password is: "+pass);  
}  
} 
