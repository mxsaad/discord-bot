package com.github.mxsaad.command;

import com.github.mxsaad.Main;
import com.github.mxsaad.service.MessageService;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.LowLevelComponent;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrefixCommand extends Command {

    private static final String NAME = "prefix";
    private static final String DESCRIPTION = "Choose a different prefix for commands from a set of options. *Requires administrator privileges.";
    private static final char[] OPTIONS = {'!', '.', '>', '?', '-'};

    public PrefixCommand() {
        super(NAME, DESCRIPTION, false);
    }

    @Override
    public void execute(MessageCreateEvent event, Scanner in) {
        String title = "Prefix Control ⚙️";
        String body = "Choose a new command prefix from the options below.";


        List<LowLevelComponent> buttons = new ArrayList<>();
        for (char prefix : OPTIONS) {
            if (prefix != Main.getPrefix()) {
                buttons.add(Button.secondary(String.valueOf(prefix), String.valueOf(prefix)));
            }
        }
        buttons.add(Button.danger("x", "Cancel"));
        ActionRow actionRow = ActionRow.of(buttons);

        new MessageService()
                .makeEmbedMessage(event, title, body)
                .addComponents(actionRow)
                .send(event.getChannel());
    }
}
