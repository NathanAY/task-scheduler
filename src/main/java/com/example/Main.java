package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * The task class represents a certain activities that must be done as the part of the project planning
 */
class Task {

    /**
     * Unique name of the activity
     */
    private String name;

    /**
     * A list of names of the activitiest that must be compelte in order to be able to start the current activity
     */
    private List<String> predecessors;

    public Task(String name, List<String> predecessors) {
        this.name = name;
        this.predecessors = predecessors;
    }

    public String getName() {
        return name;
    }

    public List<String> getPredecessors() {
        return predecessors;
    }
}

/**
 * A scheduler interface is intended to process a random list of tasks with the information of their predecessors
 * and return a list of the same tasks but in order they may be executed according to their dependencies
 */
interface IScheduler {
    public List<Task> schedule(List<Task> tasks);
}

public class Main {

    public static void main(String[] args) {
        /**
         * The following is the example of how the scheduler may be used
         */
        List<Task> tasks = Arrays.asList(
                new Task("E", Arrays.asList("B")),
                new Task("D", Arrays.asList("A", "B")),
                new Task("A", Arrays.asList()),
                new Task("B", Arrays.asList("A")),
                new Task("C", Arrays.asList("D", "B")),
                new Task("F", Arrays.asList("E"))
        );

        IScheduler scheduler = new DefaultScheduler();
        List<Task> sortedTasks = scheduler.schedule(tasks);
        for (Task t : sortedTasks) {
            System.out.println(t.getName() + " " + t.getPredecessors());
        }
    }
}
