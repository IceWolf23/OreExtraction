package it.icewolf23x.plugins.oreextraction;

import it.icewolf23x.plugins.events.BlockBreakEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class OreExtraction extends JavaPlugin {
    public static OreExtraction PLUGIN;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§bOre§3§lExtraction§8§o » §fPlugin caricato con successo");
        PLUGIN = this;
        // Plugin startup logic
        new BlockBreakEventListener(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
