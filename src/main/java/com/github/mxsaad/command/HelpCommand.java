package com.github.mxsaad.command;

import com.github.mxsaad.Main;
import com.github.mxsaad.service.MessageService;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Scanner;

public class HelpCommand extends Command {

    private static final String NAME = "help";
    private static final String DESCRIPTION = "Lists all of Seedling's commands with their descriptions.";

    public HelpCommand() {
        super(NAME, DESCRIPTION, false);
    }

    @Override
    public void execute(MessageCreateEvent event, Scanner in) {
        String title = "Seedling's Commands \uD83E\uDD16";
        String body = "";

        for (Command cmd : Main.getAllCommands().values()) {
            if (!cmd.getName().equals(NAME)) {
                body += "➼ " + Main.getPrefix() + cmd.getName() + ": " + cmd.getDescription() + "\n";
            }
        }

        new MessageService()
                .makeEmbedMessage(event, title, body)
                .send(event.getChannel());
    }
}
