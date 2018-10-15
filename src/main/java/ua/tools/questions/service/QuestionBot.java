package ua.tools.questions.service;

import com.google.api.services.customsearch.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.tools.questions.external.GoogleSearchGateway;
//import org.telegram.telegrambots.meta.generics.WebhookBot;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

//switch to webhook
@Component
@Slf4j
public class QuestionBot extends TelegramLongPollingBot /*extends WebhookBot */ {


    @Autowired
    private GoogleSearchGateway googleSearchGateway;

    private String search(String q) {
        Optional<Result> res = googleSearchGateway.performSearch(q);
        if (res.isPresent()) {

            try {
                return res.get().toPrettyString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";

    }

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
        for (Update u : updates) {
            onUpdateReceived(u);
        }
    }
}
