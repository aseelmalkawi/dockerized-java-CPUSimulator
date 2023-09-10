package components;

import task.Task;

import java.util.PriorityQueue;
import java.util.Queue;
//RAM that has the ready queue
public class RAM {
    Queue<Task> readyQueue= new PriorityQueue<>();

    public Queue<Task> getReadyQueue() {
        return readyQueue;
    }
}
