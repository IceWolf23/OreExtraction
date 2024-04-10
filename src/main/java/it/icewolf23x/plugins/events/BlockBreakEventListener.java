package it.icewolf23x.plugins.events;

import it.icewolf23x.plugins.oreextraction.OreExtraction;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class BlockBreakEventListener implements Listener {
    public BlockBreakEventListener(OreExtraction plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);}

    String[] stone = new String[]{"copper_ore","lapis_ore","redstone_ore","diamond_ore", "coal_ore", "iron_ore", "gold_ore", "emerald_ore"};
    String[] deepslate = new String[]{"deepslate_copper_ore","deepslate_lapis_ore","deepslate_redstone_ore","deepslate_diamond_ore", "deepslate_coal_ore", "deepslate_iron_ore", "deepslate_gold_ore", "deepslate_emerald_ore"};
    String[] netherrack = new String[]{"nether_gold_ore","nether_quartz_ore"};
    String[] blackstone = new String[]{"gilded_blackstone"};

    private void replaceBlock(Block block, Material replacement) {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Codice da eseguire con 1 tick di ritardo
                // Ad esempio:
                block.getWorld().getBlockAt(block.getLocation()).setType(replacement);
            }
        }.runTaskLater(OreExtraction.PLUGIN, 1);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {

        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }

        if (!event.getPlayer().getItemInHand().getType().toString().contains("PICKAXE")) {
            return;
        }

        if (event.getPlayer().getItemInHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
            return;
        }

        String blockId = event.getBlock().getType().toString();

        if (Arrays.asList(stone).contains(blockId.toLowerCase())) {
            replaceBlock(event.getBlock(), Material.COBBLESTONE);
            return;
        }
        if (Arrays.asList(deepslate).contains(blockId.toLowerCase())) {
            replaceBlock(event.getBlock(), Material.COBBLED_DEEPSLATE);
            return;
        }
        if (Arrays.asList(netherrack).contains(blockId.toLowerCase())) {
            replaceBlock(event.getBlock(), Material.NETHERRACK);
            return;
        }
        if (Arrays.asList(blackstone).contains(blockId.toLowerCase())) {
            replaceBlock(event.getBlock(), Material.BLACKSTONE);
        }
    }
}
