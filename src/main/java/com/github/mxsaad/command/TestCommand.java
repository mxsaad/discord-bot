package com.github.mxsaad.command;

import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Scanner;

public class TestCommand extends Command {

    private static final String NAME = "test";
    private static final String DESCRIPTION = "This command is only used for testing purposes.";

    private static TestCommand testCommand;

    public TestCommand () {
        super(NAME, DESCRIPTION, false);
    }

    @Override
    public void execute(MessageCreateEvent event, Scanner in) {
        event.getChannel().sendMessage("Test completed successfully.");
    }
 }
