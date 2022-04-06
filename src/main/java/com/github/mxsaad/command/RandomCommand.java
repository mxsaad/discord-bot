package com.github.mxsaad.command;


import com.github.mxsaad.Main;
import com.github.mxsaad.service.MessageService;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Scanner;

public class RandomCommand extends Command {

    private static final String NAME = "random";
    private static final String DESCRIPTION = "Generates a random number from x to y.\nSyntax: " + Main.getPrefix() + "random x y";

    public RandomCommand() {
        super(NAME, DESCRIPTION, true);
    }

    @Override
    public void execute(MessageCreateEvent event, Scanner in) {
        String title = "RNG \uD83C\uDFB2";
        String body = "Please use the proper syntax for the command. See " + Main.getPrefix() + "help for more information.";

        if (in.hasNext() == this.hasParameters()) {
            int min = in.hasNextInt() ? in.nextInt() : 0;
            int max = in.hasNextInt() ? in.nextInt() : 0;

            if (max > min) {
                int range = max - min + 1;
                int random = (int)(Math.random() * range + min);
                body = "Hmm... I choose " + random;
            }
        }

        new MessageService()
                .makeEmbedMessage(event, title, body)
                .send(event.getChannel());
    }
}
