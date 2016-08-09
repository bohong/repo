import java.util.*;  
public class ScannerTest2{  
public static void main(String args[]){  
     String input = "10 tea 20 coffee 30 tea buiscuits";  
     Scanner s = new Scanner(input).useDelimiter("\\s");  
     System.out.println(s.next());  
     System.out.println(s.next());  
     System.out.println(s.nextInt());  
     System.out.println(s.next());  
     s.close();   
}}  
