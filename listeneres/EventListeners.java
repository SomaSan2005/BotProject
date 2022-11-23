package BotJava.listeneres;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

        User user = event.getUser();
        String emoji = event.getReaction().getReactionEmote().getAsReactionCode();
        String channelMetion = event.getChannel().getAsMention();
        String  jumplink= event.getJumpUrl();

        assert user != null;
        String message = user.getAsTag() + "reacted to  a message  with " + emoji + "in the " + channelMetion + "channel!";
        //super.onMessageReactionAdd(event);

        Objects.requireNonNull(event.getGuild().getDefaultChannel()).sendMessage(message).queue();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String massage = event.getMessage().getContentRaw();
        if(massage.contains("ping")){
            event.getChannel().sendMessage("pong").queue();
        }
        //super.onMessageReceived(event);
    }

    @Override
    public void onGenericGuildMember(@NotNull GenericGuildMemberEvent event) {
    String avatar = event.getUser().getEffectiveAvatarUrl();
    System.out.println(avatar);

        //super.onGenericGuildMember(event);
    }

    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {

        List<Member> members = event.getGuild().getMembers();
        int onlineMembers = 0;
        for (Member member : members) {
            if (member.getOnlineStatus() == OnlineStatus.ONLINE) {
                onlineMembers++;
            }
        }

        // this is tag for repehrence
       /** User user  = event.getUser();
        String message = user.getAsTag()+" update their status There are now " + onlineMembers + " user online  in this guild!";
        Objects.requireNonNull(event.getGuild().getDefaultChannel()).sendMessage(message).queue();
        //Objects.requireNonNull(event.getGuild().getNewsChannelById("1044253164083359855")).sendMessage(message).queue();
       // final List<TextChannel> spamChannel = event.getGuild().getTextChannelsByName("spam-bot",true);
       **/
        //super.onUserUpdateOnlineStatus(event);
    }

    // join of new member in server

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        final List<TextChannel> dontDothis  = event.getGuild().getTextChannelsByName("Welcome",true);
        if(dontDothis.isEmpty()){
         return;
        }
        final TextChannel   pleaseDontDoThisAtAll =  dontDothis.get(0);
        final String useGuildSpecificsSettimgsInstead =  String.format("welcome %s to %s",
                event.getMember().getUser().getAsTag(),event.getGuild().getName());
        pleaseDontDoThisAtAll.sendMessage(useGuildSpecificsSettimgsInstead).queue();
    }


    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {

        final List<TextChannel> dontDoThis = event.getGuild().getTextChannelsByName("Welcome",true);
        if(dontDoThis.isEmpty()){
            return;
        }
        final TextChannel pleaseDontDoThisAtAll = dontDoThis.get(0);
        final String useGuildSpecificsSettimgsInstead =  String.format("GoodBye %s to %s",
                Objects.requireNonNull(event.getMember()).getUser().getAsTag(),event.getGuild().getName());
        pleaseDontDoThisAtAll.sendMessage(useGuildSpecificsSettimgsInstead).queue();
        //super.onGuildMemberRemove(event);

    }


}
