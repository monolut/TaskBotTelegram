package org.ashirov.nicolai;

import org.ashirov.nicolai.bots.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@Component("org.ashirov.nicolai")
public class TaskBotApplication {

	public static void main(String[] args) throws TelegramApiException {
		var context = SpringApplication.run(TaskBotApplication.class);

		Bot bot = context.getBean("bot", Bot.class);

		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		botsApi.registerBot(bot);
	}
}