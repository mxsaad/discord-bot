package com.github.mxsaad.listener;

import com.github.mxsaad.service.MessageService;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

public class WelcomeListener implements ServerMemberJoinListener {

    @Override
    public void onServerMemberJoin(ServerMemberJoinEvent event) {
        if (event.getServer().getSystemChannel().isPresent()) {
            Server server = event.getServer();
            ServerTextChannel channel = server.getSystemChannel().get();
            User user = event.getUser();

            String name = user.getDisplayName(server);
            Icon avatar = user.getAvatar();
            String body = "Everybody welcome " + name + "! \uD83E\uDD73";

            new MessageService()
                    .setEmbed(new EmbedBuilder()
                            .setFooter(name, avatar)
                            .setDescription(body)
                            .setColor(MessageService.IRIS))
                    .send(channel);

        }
    }
}
