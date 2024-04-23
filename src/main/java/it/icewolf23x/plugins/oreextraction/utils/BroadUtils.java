package it.icewolf23x.plugins.oreextraction.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.command.ConsoleCommandSender;

public class BroadUtils {
    private static final String PREFIX = "§bOre§3§lExcavation§r§8§o » §r";

    public static void send(ConsoleCommandSender sendTo, Component message) {
        sendTo.sendMessage(MessageUtils.format(PREFIX).append(message));
    }
}
