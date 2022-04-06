package com.github.mxsaad.listener;

import com.github.mxsaad.Main;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Scanner;

public class CommandListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().startsWith(String.valueOf(Main.getPrefix())) && event.getMessageContent().length() > 1) {
            Scanner in = new Scanner(event.getMessageContent());
            String name = in.next().substring(1);
            if (Main.getAllCommands().containsKey(name))
                Main.getAllCommands().get(name).execute(event, in);
        }
    }
}
