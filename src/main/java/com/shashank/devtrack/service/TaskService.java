package com.shashank.devtrack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.shashank.devtrack.model.Task;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Iterator;

@Service 
public class TaskService {
    
        
    private List<Task> tasks = new ArrayList<>();

    private Long nextId = 1L;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(Long id) {
        for (Task task : tasks) {
            if (Objects.equals(task.getId(), id)) {
                return task;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Task createTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    public Task updateTask(Long id, Task updatedTask) {
        Iterator<Task> iterator = tasks.iterator();
        while(iterator.hasNext()){
            Task task = iterator.next();
            if(Objects.equals(task.getId(), id)) {
                if (updatedTask.getTitle() != null) {
                    task.setTitle(updatedTask.getTitle());
                }

                if (updatedTask.getDescription() != null) {
                    task.setDescription(updatedTask.getDescription());
                }

                if (updatedTask.getPriority() != null) {
                    task.setPriority(updatedTask.getPriority());
                }

                if (updatedTask.getStatus() != null) {
                    task.setStatus(updatedTask.getStatus());
                }

                if (updatedTask.getDueDate() != null) {
                    task.setDueDate(updatedTask.getDueDate());
                }
                return task;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void deleteTask(Long id) {
        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            Task task = iterator.next();

            if (Objects.equals(task.getId(), id)) {
                iterator.remove();
                return;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


}
