package com.github.mxsaad.command;

import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Scanner;

public class Command {

    private String name;
    private String description;
    private boolean hasParameters;

    public Command(String name, String description, boolean hasParameters) {
        this.name = name;
        this.description = description;
        this.hasParameters = hasParameters;

    }

    public void execute(MessageCreateEvent event, Scanner in) {
        return;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean hasParameters() {
        return this.hasParameters;
    }
}
