import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;
import static java.lang.System.out;
import static java.lang.System.err;

public class Tester {

    public static void main(String[] args){
        try{
            Class<?> c = Class.forName("ClassToReflect");
            System.out.println(c.getName());
            Method[] methods = c.getDeclaredMethods();
            for(Method method : methods){
                System.out.println(method.getName());
            }
            Class[] staticLockBandArgTypes = new Class[]{Integer.class};
            Method staticLockBand = c.getDeclaredMethod("staticLockBand", staticLockBandArgTypes);
            System.out.println(staticLockBand.getName());
            staticLockBand.setAccessible(true);
            staticLockBand.invoke(null, 100);
            Method staticUnlockBand = c.getDeclaredMethod("staticUnlockBand");
            System.out.println(staticUnlockBand.getName());
            staticUnlockBand.setAccessible(true);
            staticUnlockBand.invoke(null);

            
            Object o = c.newInstance();
            Class[] lockBandArgTypes = new Class[]{Integer.class};
            Method lockBand = c.getDeclaredMethod("lockBand", lockBandArgTypes);
            System.out.println(lockBand.getName());
            lockBand.setAccessible(true);
            lockBand.invoke(o, 100);
            Method unlockBand = c.getDeclaredMethod("unlockBand");
            System.out.println(unlockBand.getName());
            unlockBand.setAccessible(true);
            unlockBand.invoke(o);

        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x){
            x.printStackTrace();
        }
    }
}
