import java.util.*;


public class WildcardTest {

    public static void main(String[] args){
        Banana banana = new Banana();
        Banana banana2 = new Banana();
        Banana banana3 = new Banana();
        Orange orange = new Orange();
        List<Fruit> fruitList = new ArrayList<>();
        List<Banana> bananaList = new ArrayList<>();
        List<Banana> bananaList2 = new ArrayList<>();

        //fruitList.add(banana);
        //fruitList.add(orange);
        
        bananaList.add(banana);
        bananaList.add(banana3);
        bananaList.add(banana2);

        //System.out.println(fruitList.size());
        //copy(bananaList, bananaList2);
        //System.out.println(bananaList2.size());
        copy(bananaList, fruitList);
        System.out.println(fruitList.size());


    }

    // super or extends? when to use <? extends T> and when to use <? super T>
    //
    // the principle is: PECS: producer extends, consumer super
    //
    // the meaning is that if to read from a list, for example, then List<? extends T>
    // if to write to a list, then List<? super T>
    //
    // the reason is that any new ArrayList<subtype> can be assigned to List<? extends T> 
    // so, element read from List<? extends T> guaranteed to be a T, hence, can be used as a T
    // but a T (or an instance of T's subtype) could not be added to List<? extends T>,
    // because List<? extends T> could now points to new ArrayList<T1> while tring to add an instance
    // of T2 to it. 
    //
    // on the other hand, List<? super T> can point to new ArrayList<supertype>, 
    // so, any instance of type T or its subtype can be added to List<? super T>, 
    // as the instance of type T or its subtype is indeed an instance of T's supertype. 
    // but an element read from List<? super T> can only guaranteed to be an instance of Object, 
    // and it could be an instance of one of T's supertype, hence, the element could not be used as a T. 
    //
    private static <T> boolean copy(List<T> src, List <? super T> dst){

        for(T e : src){
            dst.add(e);
        }
        return true;
    }

}

class Fruit {

}

class Banana  extends Fruit {

}

class Orange extends Fruit {

}

