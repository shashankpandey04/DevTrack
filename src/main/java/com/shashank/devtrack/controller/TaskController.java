package com.shashank.devtrack.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import java.util.Iterator;

import com.shashank.devtrack.model.Task;

import java.util.List;
import java.util.ArrayList;

@RestController 
@RequestMapping("/api/v1/tasks")
public class TaskController {
    
    private List<Task> tasks = new ArrayList<>();

    private Long nextId = 1L;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        for(Task task : tasks) {
            if(Objects.equals(task.getId(), id)){
                return task;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Iterator<Task> iterator = tasks.iterator();
        while(iterator.hasNext()) {
            Task task = iterator.next();
            if(Objects.equals(task.getId(), id)) {
                iterator.remove();
                return ResponseEntity.noContent().build();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PatchMapping ("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
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
}
