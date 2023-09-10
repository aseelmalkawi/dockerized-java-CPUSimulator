import simulator.Simulator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String tasksFilePath;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path of the tasks file:");
        tasksFilePath = input.nextLine();
        System.out.println("Enter the number of processors:");
        int processorsNumber = input.nextInt();
        System.out.println("Enter the number of cycles:");
        int cycles = input.nextInt();
        try {
            Simulator simulator = new Simulator(processorsNumber, cycles, tasksFilePath);
            simulator.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}