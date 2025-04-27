package org.ashirov.nicolai.menu;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainMenu {

    public SendMessage sendMainMenu(long chatId, String text) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("\uD83D\uDCDD Create Task");
        row1.add("❌ Delete Task");
        row1.add("\uD83D\uDCCB My Tasks");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("\uD83D\uDEDF Support");

        keyboard.add(row1);
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);

        SendMessage response = new SendMessage();
        response.setChatId(String.valueOf(chatId));response.setText(text);
        response.setReplyMarkup(keyboardMarkup);

        return response;
    }

}
