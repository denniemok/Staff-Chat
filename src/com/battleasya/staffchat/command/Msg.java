package com.battleasya.staffchat.command;

import com.battleasya.staffchat.handler.Config;
import com.battleasya.staffchat.handler.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Msg implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("This is a player-only command!");
            return true;
        }

        if(!sender.hasPermission("staffchat.use")) {
            Util.msgPlayer(sender, Config.noPermission);
            return true;
        }

        if (args.length == 0) {
            Util.msgPlayer(sender, Config.messageSyntax);
            return true;
        }

        StringBuilder str = new StringBuilder();

        for (String arg : args) {
            str.append(arg).append(" ");
        }

        Util.msgStaff(Config.chatMessage
                .replaceAll("%name%", sender.getName())
                .replaceAll("%message%", str.toString()));
        return true;

    }

}
