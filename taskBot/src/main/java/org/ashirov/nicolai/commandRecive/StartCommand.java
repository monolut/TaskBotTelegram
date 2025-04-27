package org.ashirov.nicolai.commandRecive;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class StartCommand {

    public SendMessage startCommandReceiver(long chatId, String name) {
        SendMessage message = new SendMessage();
        String command = "Hello " + name + " !";
        message.setText(command);
        message.setChatId(String.valueOf(chatId));

        return message;
    }

}
