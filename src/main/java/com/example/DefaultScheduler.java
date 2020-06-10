package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DefaultScheduler implements IScheduler {

    @Override
    public List<Task> schedule(final List<Task> list) {
        List<Task> tasks = new ArrayList<>(list);
        List<Task> orderedTasks = new ArrayList<>();
        while (orderedTasks.size() < list.size()) {
            order(tasks, orderedTasks);
        }
        return orderedTasks;
    }

    private void order(List<Task> tasks, List<Task> orderedTasks) {
        int currentSize = tasks.size();
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (canBeAddedToOrderedList(task, orderedTasks)) {
                orderedTasks.add(task);
                it.remove();
            }
        }
        if (currentSize == tasks.size()) {
            throw new IllegalArgumentException("Task cannot be scheduled");
        }
    }

    private boolean canBeAddedToOrderedList(Task task, List<Task> list) {
        if (task.getPredecessors().size() == 0) {
            return true;
        }
        List<String> predecessors = task.getPredecessors();
        return predecessors.stream().allMatch(p -> list.stream().anyMatch(t -> t.getName().equals(p)));
    }

}
