package chapter04;

/**
 * 6-5
 */
public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                chapter04.SleepUtils.second(100);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
