package com.github.mxsaad.listener;

import com.github.mxsaad.service.MessageService;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeActivityEvent;
import org.javacord.api.listener.user.UserChangeActivityListener;

import java.util.Set;

public class GameActivityListener implements UserChangeActivityListener {

    @Override
    public void onUserChangeActivity(UserChangeActivityEvent event) {
        Set<Activity> activities = event.getNewActivities();
        for (Activity a : activities) {
            System.out.println(a.getName());
            if (a.getName().equalsIgnoreCase("Valorant")) {
                if (event.getUser().isPresent()) {
                    User user = event.getUser().get();
                    String name = user.getName();
                    String body = "Everyone laugh at " + name + " for playing MIDorant! 🤣";

                    MessageBuilder messenger = new MessageService()
                            .setEmbed(new EmbedBuilder()
                                    .setDescription(body)
                                    .setColor(MessageService.IRIS));

                    Set<Server> servers = user.getMutualServers();
                    for (Server s : servers) {
                        if (s.getSystemChannel().isPresent()) {
                            messenger.send(s.getSystemChannel().get());
                        }
                    }
                }
                break;
            }
        }
    }
}
