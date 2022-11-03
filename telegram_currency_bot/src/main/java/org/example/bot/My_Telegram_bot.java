package org.example.bot;

import lombok.SneakyThrows;
import org.example.constants.Constants;
import org.example.model.Currency;
import org.example.url.Url_con;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class My_Telegram_bot extends TelegramLongPollingBot implements Constants {

    static ArrayList<Currency> currencyList = Url_con.currencyList();

    int gen = 1;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            if (text.equals("/start")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("Assalomu aleykum ");
                sendMessage.enableMarkdownV2(true);
                sendMessage.setChatId(chatId);
                execute(sendMessage);
                ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
                markup.setResizeKeyboard(true);
                markup.setSelective(true);
                KeyboardButton button1 = new KeyboardButton();
                KeyboardButton button2 = new KeyboardButton();
                button1.setText("All Valuates");
                button2.setText("Convert");
                List<KeyboardRow> keyboardRow = new ArrayList<>();
                keyboardRow.add(new KeyboardRow(List.of(button1, button2)));
                markup.setKeyboard(keyboardRow);
                sendMessage.setReplyMarkup(markup);
                execute(sendMessage);
            } else if (update.getMessage().getText().equals("All Valuates")) {
                for (Currency item : currencyList) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(gen++ + ".  " + item.getValuate() + '\n' +
                            "Valuate :  " + item.getUz() + '\n' +
                            "Rate    :  " + item.getRate() + " sum" + '\n' +
                            "Date    :  " + item.getSana());
                    sendMessage.setChatId(chatId);
                    execute(sendMessage);
                }
            } else if (update.getMessage().getText().equals("Convert")) {
                List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

                for (Currency item : currencyList) {
                    buttons.add(
                            Arrays.asList(
                                   InlineKeyboardButton.builder().text(item.getValuate()).
                                           callbackData("Original Currency").build(),
                                    InlineKeyboardButton.builder().text(item.getValuate()).
                                            callbackData("Target Currency").build())
                    );
                }
                execute(SendMessage.builder().text("Please choose ").chatId(chatId).
                        replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build()).build());

            }

        }


    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
