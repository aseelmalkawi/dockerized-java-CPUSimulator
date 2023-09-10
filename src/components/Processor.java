package components;

import task.Task;

public class
Processor {
    private RunnableProcessor runnableProcessor;
    private int assignedTaskExeTime;
    private Task assignedTask;
    static int processorsNo = 0;
    private boolean isRunning = true;
    private boolean isBusy = false;
    private final int processorID;

    public Processor() {
        processorID = processorsNo + 1;
        processorsNo++;
    }

    public void terminate() {
        isRunning = false;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public int getProcessorID() {
        return processorID;
    }

    public synchronized void start() {
        runnableProcessor = new RunnableProcessor();
        new Thread(runnableProcessor).start();
    }

    public void notifyProcessor() {
        if (isBusy) {
            if (--assignedTaskExeTime == 0) {
                System.out.println("Processor " + processorID + " finished executing task " + assignedTask.getTaskID());
                isBusy = false;
            }
        }
        synchronized (runnableProcessor) {
            runnableProcessor.notify();
        }
    }

    public synchronized void setAssignedTask(Task assignedTask) {
        isBusy = true;
        this.assignedTask = assignedTask;
        assignedTaskExeTime = assignedTask.getExeTime();
        System.out.println("Task " + assignedTask.getTaskID() + " assigned to processor " + processorID + ". Will take " + assignedTaskExeTime + " seconds.");
    }

    private class RunnableProcessor implements Runnable {
        @Override
        public synchronized void run() {
            while (isRunning) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
