package BotJava.commands;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class commandsmanager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName(); // hello
        //super.onSlashCommandInteraction(event);
        if(command.equals("Weclome")){ // command of welcome
            // run the /welcome command
         String userTag = event.getUser().getAsTag();
         event.reply("Welcome o the server, **  " +  userTag + "**!");
        }
    }

    // Guild command -- instantly updated (max 100)

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
     List<CommandData> commandData =  new ArrayList<>();
     commandData.add(Commands.slash("Welcome","Get welcome  by the bot "));
     //super.onGuildReady(event);
      event.getGuild().updateCommands().addCommands(commandData).queue();

    }

    //Global command -- up to  an hour  to update (unlimited)

    
}
