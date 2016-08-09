public class SimpleThreads {

    // Display a message, preceded by
    // the name of the current thread

    static void threadMessage(String message) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                          threadName,
                          message);
    }

    private static class MessageLoop
        implements Runnable {
        public void run() {
            String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
            };
            try {
                for (int i = 0;
                     i < importantInfo.length;
                     i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }
    private static class MessageLoop2
        implements Runnable {
        public void run() {
            threadMessage("in message loop 2");
            simpleThreads.inci();
        }
        MessageLoop2(SimpleThreads simpleThreads){
            this.simpleThreads = simpleThreads;
        }

        private SimpleThreads simpleThreads;

    }
    private static class MessageLoop3
        implements Runnable {
        public void run() {
            threadMessage("in message loop 3");
            try{
            Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            threadMessage("" + simpleThreads.geti());
        }
        MessageLoop3(SimpleThreads simpleThreads){
            this.simpleThreads = simpleThreads;
        }

        private SimpleThreads simpleThreads;
    }

    public static void main(String args[])
        throws InterruptedException {
    
        SimpleThreads simpleThreads = new SimpleThreads();

        Thread t2 = new Thread(new MessageLoop3(simpleThreads));
        t2.start();
        Thread t = new Thread(new MessageLoop2(simpleThreads));
        t.start();
    }

    public synchronized int geti(){
        return i;
    }

    public synchronized void inci(){
        for(int t = 0; t < 100; t++){
            i++;
            try{
            Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            threadMessage("i += 1");
        }
    }

    private int i = 0;


}
