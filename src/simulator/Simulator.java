package simulator;
import components.Clock;
import components.HardDrive;
import components.RAM;
import components.Processor;
import schedulers.LongScheduler;
import schedulers.ShortScheduler;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class Simulator {
    private int cycles;
    private List<Processor> processors = new ArrayList<>();
    private RAM ram = new RAM();
    private LongScheduler longScheduler;
    private ShortScheduler shortScheduler;
    private Clock clock = new Clock();
    private HardDrive hardDrive;

    public Simulator(int processorsNumber, int cycles, String taskFilePath) throws FileNotFoundException {
        for (int i = 0; i < processorsNumber; i++) {
            processors.add(new Processor());
        }
        hardDrive = new HardDrive();
        hardDrive.readTasks(taskFilePath);
        this.cycles = cycles;
        clock.addNotifier(this);
        shortScheduler = new ShortScheduler(ram, processors);
        longScheduler = new LongScheduler(hardDrive, ram, clock, shortScheduler);
    }

    public synchronized void start() {
        initiateThreads();
        while (clock.getCycle() != cycles) {
            try {
                wait();
                System.out.println();
                System.out.println("            CYCLE " + clock.getCycle());
                synchronized (longScheduler) {
                    longScheduler.notify();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        killThreads();
    }

    private void initiateThreads() {
        new Thread(clock).start();
        for (Processor processor : processors) {
            processor.start();
        }
        new Thread(longScheduler).start();
        new Thread(shortScheduler).start();
    }

    private void killThreads() {
        for (Processor processor : processors) {
            processor.terminate();
        }
        clock.stop();
        longScheduler.terminate();
        shortScheduler.terminate();
    }
}
