package com.github.mxsaad;

import com.github.mxsaad.command.*;
import com.github.mxsaad.listener.CommandListener;
import com.github.mxsaad.listener.GameActivityListener;
import com.github.mxsaad.listener.PrefixButtonListener;
import com.github.mxsaad.listener.WelcomeListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import java.io.File;
import java.util.TreeMap;

public class Main {

    private static final String token = System.getenv("TOKEN"); // environment variable for private token
    private static final DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
    private static final TreeMap<String, Command> allCommands = new TreeMap<>();
    private static char prefix = '!'; // default prefix for commands

    public static void main(String[] args) {

        // initial setup
        api.updateActivity(ActivityType.LISTENING, prefix + "help");
        api.updateAvatar(new File("com/github/mxsaad/resource/FrankyIcon.jpg"));
        System.out.println("You can invite the bot to your server with the following link: " + api.createBotInvite());

        // attach available listeners
        api.addMessageCreateListener(new CommandListener());
        api.addMessageComponentCreateListener(new PrefixButtonListener());
        api.addServerMemberJoinListener(new WelcomeListener());
        api.addUserChangeActivityListener(new GameActivityListener());

        // activate available commands
        createCommand(new HelpCommand());
        createCommand(new PingCommand());
        createCommand(new RandomCommand());
        createCommand(new PrefixCommand());
    }

    public static DiscordApi getApi() {
        return api;
    }

    public static TreeMap<String, Command> getAllCommands() {
        return allCommands;
    }

    public static char getPrefix() {
        return prefix;
    }

    public static void setPrefix(char prefix) {
        Main.prefix = prefix;
    }

    private static void createCommand(Command command) {
        allCommands.put(command.getName(), command);
    }
}
