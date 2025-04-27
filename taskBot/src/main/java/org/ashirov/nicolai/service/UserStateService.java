package org.ashirov.nicolai.service;

import org.ashirov.nicolai.state.BotState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserStateService {
    private final Map<Long, BotState> userStates = new HashMap<>();

    public void setState(Long chatId, BotState state) {
        userStates.put(chatId, state);
    }

    public BotState getState(Long chatId) {
        return userStates.getOrDefault(chatId, BotState.NONE);
    }

    public void clearState(Long chatId) {
        userStates.remove(chatId);
    }
}
