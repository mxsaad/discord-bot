package com.github.mxsaad.listener;

import com.github.mxsaad.Main;
import com.github.mxsaad.service.MessageService;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

public class PrefixButtonListener implements MessageComponentCreateListener {

    @Override
    public void onComponentCreate(MessageComponentCreateEvent event) {
        if (event.getMessageComponentInteraction().getServer().isPresent()) {
            Server server = event.getMessageComponentInteraction().getServer().get();
            User user = event.getMessageComponentInteraction().getUser();
            String title = "Prefix Control ⚙️";
            String body = "";

            char prefix = event.getMessageComponentInteraction().getCustomId().charAt(0);
            if (prefix != 'x') {
                if (server.isAdmin(user)) {
                    Main.setPrefix(prefix);
                    body = "From now on, I will respond to commands that begin with \"" + prefix +"\"";
                    Main.getApi().updateActivity(ActivityType.LISTENING, prefix + "help");
                }
                else body = "You must have admin privileges to change the command prefix.";
            }

            event.getMessageComponentInteraction().getMessage().delete();
            new MessageService().sendComponentResponse(event, title, body);
        }
    }
}
