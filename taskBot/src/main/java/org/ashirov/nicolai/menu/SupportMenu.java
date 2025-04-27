package org.ashirov.nicolai.menu;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupportMenu {

    public SendMessage sendSupportMenu(long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboardList = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅\uFE0F Back!");
        keyboardList.add(row1);
        keyboardMarkup.setKeyboard(keyboardList);
        SendMessage response = new SendMessage();
        response.setChatId(String.valueOf(chatId));
        response.setText("\uD83D\uDEDF Admin: @TaskBotAdmin");
        response.setReplyMarkup(keyboardMarkup);

        return response;
    }

}
