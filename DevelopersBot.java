package BotJava;

import BotJava.commands.commandsmanager;
import BotJava.listeneres.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class DevelopersBot {

      private final Dotenv config;
    private  final  ShardManager shardManager ;


    public DevelopersBot() throws LoginException {
        config= Dotenv.configure().load();
           String token = config.get("TOKEN");
        //Build shard manger
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("the work"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_PRESENCES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);
        shardManager = builder.build();

        //Register Listener
        shardManager.addEventListener(new EventListener(),new commandsmanager());
    }

    public Dotenv  getConfig(){
        return config;
    }

    public ShardManager getShardManager() {

        return shardManager;
    }

    public static void main(String[]args){
     try{
            DevelopersBot bot = new DevelopersBot();
       }catch (LoginException e ) {
         System.out.println("ERROR: Provided bot token is invalid! ");
        }
     }

}
