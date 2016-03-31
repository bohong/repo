
public class ClassToReflect {
    private boolean lockBand(Integer bandNumber){
        System.out.println("lockBand gets called");
        return true;
    }
    private boolean unlockBand(){
        System.out.println("unlockBand gets called");
        return true;
    }
    private static boolean staticLockBand(Integer bandNumber){
        System.out.println("staticLockBand gets called");
        return true;
    }
    private static boolean staticUnlockBand(){
        System.out.println("staticUnlockBand gets called");
        return true;
    }
}
