package com.github.mxsaad.command;

import com.github.mxsaad.service.MessageService;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Scanner;

public class PingCommand extends Command {

    private static final String NAME = "ping";
    private static final String DESCRIPTION = "Measures the latency between a command and its response.";

    public PingCommand() {
        super(NAME, DESCRIPTION, false);
    }

    @Override
    public void execute(MessageCreateEvent event, Scanner in) {
        String title = "Pong! \uD83C\uDFD3";
        long ping = System.currentTimeMillis() - event.getMessage().getCreationTimestamp().toEpochMilli();
        String body = ping + " ms";

        new MessageService()
                .makeEmbedMessage(event, title, body)
                .send(event.getChannel());
    }
}
