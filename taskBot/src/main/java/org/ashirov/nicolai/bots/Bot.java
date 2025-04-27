package org.ashirov.nicolai.bots;

import org.ashirov.nicolai.commandRecive.StartCommand;
import org.ashirov.nicolai.config.BotConfig;
import org.ashirov.nicolai.menu.MainMenu;
import org.ashirov.nicolai.menu.SupportMenu;
import org.ashirov.nicolai.service.TaskService;
import org.ashirov.nicolai.service.UserStateService;
import org.ashirov.nicolai.state.BotState;
import org.ashirov.nicolai.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    private final UserStateService userStateService;
    private final TaskService taskService;
    private final MainMenu mainMenu;
    private final SupportMenu supportMenu;
    private final StartCommand startCommand;

    Long userID = 6292471707L;
    Long adminID = 5132832880L;
    Long leraID = 1546674077L;
    Long misaChatID = 917243913L;

    private final BotConfig config;

    @Autowired
    public Bot(BotConfig config,
               MainMenu mainMenu,
               SupportMenu supportMenu,
               StartCommand startCommand,
               TaskService taskService,
               UserStateService userStateService) {
        this.config = config;
        this.mainMenu = mainMenu;
        this.supportMenu = supportMenu;
        this.startCommand = startCommand;
        this.taskService = taskService;
        this.userStateService = userStateService;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(!update.hasMessage() && !update.getMessage().hasText()) { return; }

        var message = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        BotState state = userStateService.getState(chatId);

        switch (state)
        {
            case AWAITING_TEXT : {
                String taskText = update.getMessage().getText();
                taskService.addTask(chatId, taskText);

                SendMessage response = new SendMessage();
                response.setChatId(String.valueOf(chatId));
                response.setText("Task: " + taskText);
                this.executeMethod(response);

                userStateService.clearState(chatId);
                return;
                }
            case AWAITING_ID: {
                String taskText = update.getMessage().getText();

                taskService.deleteTask(Integer.parseInt(taskText));

                SendMessage response = new SendMessage();
                response.setChatId(String.valueOf(chatId));
                response.setText("Task with ID " + update.getMessage().getText() + " has deleted");

                this.executeMethod(response);
                userStateService.clearState(chatId);
                return;
            }
        }

            System.out.println(update.getMessage().getChat().getFirstName() +
                    " with id " +
                    update.getMessage().getChat().getId() +
                    " with chat id " +
                    update.getMessage().getChatId() +
                    " wrote " +
                    message);

            switch (message) {
                case "/start":{
                    SendMessage response = startCommand.startCommandReceiver(chatId, update.getMessage().getChat().getFirstName());
                    this.executeMethod(response);
                    response = mainMenu.sendMainMenu(chatId, "\uD83C\uDFE0 Welcome");
                    this.executeMethod(response);
                    }
                    break;
                case "\uD83D\uDEDF Support": {
                    SendMessage response = supportMenu.sendSupportMenu(chatId);
                    this.executeMethod(response);
                    }
                    break;
                case "⬅\uFE0F Back!":
                    backMethod(chatId);
                    break;
                case "\uD83D\uDCCB My Tasks": {
                    List<Task> taskList = taskService.getTasks();
                    taskList.forEach(task -> {
                        SendMessage response = new SendMessage();
                        response.setChatId(String.valueOf(chatId));
                        response.setText(task.getId() + ": " + task.getText());
                        this.executeMethod(response);
                    });
                }
                break;
                case "❌ Delete Task": {
                    userStateService.setState(chatId, BotState.AWAITING_ID);

                    SendMessage response = new SendMessage();
                    response.setChatId(String.valueOf(chatId));
                    response.setChatId("Type ID which you want to delete:");

                    this.executeMethod(response);
                    }
                    break;
                case "\uD83D\uDCDD Create Task": {
                    userStateService.setState(chatId, BotState.AWAITING_TEXT);

                    SendMessage response = new SendMessage();
                    response.setChatId(String.valueOf(chatId));
                    response.setText("Type what would you like to do?");

                    this.executeMethod(response);
                }
                break;
                default:
                    sendErrorMessage(chatId);
                    break;
            }

    }

    private void backMethod(long chatId) {
        SendMessage message = new SendMessage();
        message = mainMenu.sendMainMenu(chatId, "\uD83C\uDFE0 Menu");
        executeMethod(message);
    }

    private void sendErrorMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Error, try another one");
        executeMethod(sendMessage);
    }

    private void executeMethod(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Telegram API Exception");
        }
    }
}
