package components;
import java.util.ArrayList;
import java.util.List;

public class Clock implements Runnable {
    private int cycle;
    private boolean isRunning = true;
    private List<Object> notifiers = new ArrayList<>();

    @Override
    public void run() {
        cycle = 0;
        while (isRunning) {
            try {
                Thread.sleep(1000);
                cycle++;
                for (Object object : notifiers) {
                    synchronized (object) {
                        object.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addNotifier(Object object) {
        notifiers.add(object);
    }

    public synchronized int getCycle() {
        return cycle;
    }

    public void stop() {
        isRunning = false;
    }
}
