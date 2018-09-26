package ua.tools.questions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.meta.generics.WebhookBot;


import java.util.List;

//switch to webhook
@Component
@Slf4j
public class QuestionBot extends TelegramLongPollingBot /*extends WebhookBot */{


    @Override
    public String getBotUsername() {
        return "vikingtebot";
    }

    @Override
    public String getBotToken() {
        return "625995806:AAGMhoaORBdjEhWfMQy72u_qoExH1pgOioM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("UPDATE!!!");
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        System.out.println("UPDATES!!!");
        // We check if the update has a message and the message has text
        for(Update u: updates) {
            onUpdateReceived(u);
        }
    }
}
