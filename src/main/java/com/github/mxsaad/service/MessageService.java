package com.github.mxsaad.service;

import org.javacord.api.entity.Icon;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class MessageService extends MessageBuilder {

    public static final Color IRIS = new Color(93, 63, 211);

    public MessageService makeEmbedMessage(MessageCreateEvent event, String title, String body) {
        String name = event.getMessageAuthor().getDisplayName();
        Icon avatar = event.getMessageAuthor().getAvatar();
        this.setEmbed(new EmbedBuilder()
                        .setFooter(name, avatar)
                        .setTitle(title)
                        .setDescription(body)
                        .setColor(IRIS));
        return this;
    }

    public void sendComponentResponse(MessageComponentCreateEvent event, String title, String body) {
        TextChannel channel = event.getMessageComponentInteraction().getChannel().get();
        Server server = event.getMessageComponentInteraction().getServer().get();
        User user = event.getMessageComponentInteraction().getUser();
        String name = user.getDisplayName(server);
        Icon avatar = user.getAvatar();

        new MessageService()
                .setEmbed(new EmbedBuilder()
                        .setFooter(name, avatar)
                        .setTitle(title)
                        .setDescription(body)
                        .setColor(IRIS))
                .send(channel);
    }
}
