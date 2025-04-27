package org.ashirov.nicolai.repository;

import lombok.Getter;
import org.ashirov.nicolai.task.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryTaskRepo {

    @Getter
    private List<Task> tasks = new ArrayList<>();

    private Integer taskIdCounter = 0;

    public void addTask(Long chatID, String text) {
        tasks.stream()
                .filter(taskStream -> taskStream.getText().equals(text))
                .findFirst()
                .ifPresentOrElse(
                        taskStream -> {},
                        () -> {taskIdCounter++;
                            Task task = new Task(chatID, taskIdCounter, text);
                            tasks.add(task);
                        }
                );
    }

    public void deleteTask(Integer taskId) {

        taskIdCounter = taskIdCounter > 0 ? taskIdCounter - 1 : taskIdCounter;

                tasks.stream()
                        .filter(t -> t.getId().equals(taskId))
                        .findFirst()
                        .ifPresent(tasks::remove);

        tasks.forEach(task -> {
            if(task.getId() >= taskId) {
                task.setId(task.getId() - 1);
            }
        });

    }
}
