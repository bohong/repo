import java.util.*;  
class TestCollection13{  
 public static void main(String args[]){  
   
  HashMap<Integer,String> hm=new HashMap<Integer,String>();  
  
  hm.put(100,"Amit");  
  hm.put(101,"Vijay");  
  hm.put(102,"Rahul");  
  hm.put(103,"Rahul");
  hm.put(100,"Rahul");  
  
  for(Map.Entry m:hm.entrySet()){  
   System.out.println(m.getKey()+" "+m.getValue());  
  }  
 }  
} 
