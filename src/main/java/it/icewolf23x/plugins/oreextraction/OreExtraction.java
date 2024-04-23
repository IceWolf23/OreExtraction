package it.icewolf23x.plugins.oreextraction;

import it.icewolf23x.plugins.oreextraction.events.BlockBreakEventListener;
import it.icewolf23x.plugins.oreextraction.utils.BroadUtils;
import it.icewolf23x.plugins.oreextraction.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class OreExtraction extends JavaPlugin {
    public static OreExtraction PLUGIN;

    @Override
    public void onEnable() {

        PLUGIN = this;
        new BlockBreakEventListener(this);
        BroadUtils.send(Bukkit.getConsoleSender(), MessageUtils.format("§fPlugin loaded successfully"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
