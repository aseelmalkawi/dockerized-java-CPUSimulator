package components;

import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//class that demonstrates the hard drive to save the tasks before they are loaded into the RAM
public class HardDrive {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void readTasks(String tasksFilePath) throws FileNotFoundException {
        File taskFile = new File(tasksFilePath);
        Scanner taskReader = new Scanner(taskFile);
        int tasksNumber = Integer.parseInt(taskReader.nextLine());
        for (int i = 0; i < tasksNumber; i++) {

            Task task = new Task();
            task.setCreationTime(taskReader.nextInt());
            task.setExeTime(taskReader.nextInt());
            task.setPriority(taskReader.nextInt());
            tasks.add(task);
        }
    }
}
