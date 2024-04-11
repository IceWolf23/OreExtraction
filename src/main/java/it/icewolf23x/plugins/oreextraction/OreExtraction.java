package it.icewolf23x.plugins.oreextraction;

import it.icewolf23x.plugins.events.BlockBreakEventListener;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class OreExtraction extends JavaPlugin {
    public static OreExtraction PLUGIN;

    @Override
    public void onEnable() {

        PLUGIN = this;
        new BlockBreakEventListener(this);
        BroadUtils.send(Bukkit.getConsoleSender(), MessageUtils.format("§fPlugin loaded successfully"));

    }




    public static LuckPerms getLuckPermsApi() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServer().getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            return provider.getProvider();
        }
        return null;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
