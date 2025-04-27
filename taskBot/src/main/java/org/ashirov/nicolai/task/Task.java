package org.ashirov.nicolai.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {

    private Long ChatID;
    private Integer id;
    private String text;

    public Task(Long ChatID, Integer id, String text) {

        this.ChatID = ChatID;
        this.id = id;
        this.text = text;

    }
}

