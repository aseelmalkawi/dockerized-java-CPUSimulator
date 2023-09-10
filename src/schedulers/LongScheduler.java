package schedulers;

import components.Clock;
import components.HardDrive;
import components.RAM;
import task.Task;

public class LongScheduler implements Runnable {
    private HardDrive hardDrive;
    private RAM ram;
    private Clock clock;
    private ShortScheduler shortScheduler;
    private boolean isRunning = true;

    public LongScheduler(HardDrive hardDrive, RAM ram, Clock clock, ShortScheduler shortScheduler) {
        this.hardDrive = hardDrive;
        this.ram = ram;
        this.shortScheduler = shortScheduler;
        this.clock = clock;
    }

    public void getTasks() {
        for (Task task : hardDrive.getTasks()) {
            if (task.getCreationTime() == clock.getCycle()) {
                ram.getReadyQueue().add(task);
            }
        }
    }

    @Override
    public synchronized void run() {
        while (isRunning) {
            try {
                wait();
                getTasks();
                synchronized (shortScheduler) {
                    shortScheduler.notify();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void terminate() {
        isRunning = false;
    }
}
