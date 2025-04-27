package org.ashirov.nicolai.service;

import org.ashirov.nicolai.repository.InMemoryTaskRepo;
import org.ashirov.nicolai.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final InMemoryTaskRepo repo;

    @Autowired
    public TaskService(InMemoryTaskRepo repo) {
        this.repo = repo;
    }

    public void addTask(Long chatID, String taskText) {
        repo.addTask(chatID, taskText);
    }

    public List<Task> getTasks() {
        return repo.getTasks();
    }

    public void deleteTask(Integer taskId) {
        repo.deleteTask(taskId);
    }
}
