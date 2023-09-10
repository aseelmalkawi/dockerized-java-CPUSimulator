package schedulers;

import components.RAM;
import components.Processor;
import java.util.List;

public class ShortScheduler implements Runnable {
    private boolean isRunning = true;
    private RAM ram;
    private List<Processor> processors;

    public ShortScheduler(RAM ram, List<Processor> processors) {
        this.ram = ram;
        this.processors = processors;
    }

    @Override
    public synchronized void run() {
        while (isRunning) {
            try {
                wait();
                assignTasks();
                for (Processor processor : processors) {
                    processor.notifyProcessor();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void assignTasks() {
        for (Processor processor : processors) {
            if (!processor.isBusy()) {
                System.out.println("Processor " + processor.getProcessorID() + " is vacant");
                if (!ram.getReadyQueue().isEmpty()) {
                    processor.setAssignedTask(ram.getReadyQueue().remove());
                }
            }
        }
    }

    public void terminate() {
        isRunning = false;
    }
}
