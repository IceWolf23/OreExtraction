package it.icewolf23x.plugins.events;

import it.icewolf23x.plugins.events.utils.PermUtils;
import it.icewolf23x.plugins.oreextraction.OreExtraction;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collection;

public class BlockBreakEventListener implements Listener {
    public BlockBreakEventListener(OreExtraction plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);}

    String[] stone = new String[]{"copper_ore","lapis_ore","redstone_ore","diamond_ore", "coal_ore", "iron_ore", "gold_ore", "emerald_ore"};
    String[] deepslate = new String[]{"deepslate_copper_ore","deepslate_lapis_ore","deepslate_redstone_ore","deepslate_diamond_ore", "deepslate_coal_ore", "deepslate_iron_ore", "deepslate_gold_ore", "deepslate_emerald_ore"};
    String[] netherrack = new String[]{"nether_gold_ore","nether_quartz_ore"};
    String[] blackstone = new String[]{"gilded_blackstone"};

    private void replaceBlock(Player player, Block block, Material replacement, Collection<ItemStack> drops) {
        block.setType(replacement);

        Location loc = block.getLocation().clone();
        RayTraceResult trace = player.getLocation().getWorld().rayTraceBlocks(player.getEyeLocation(), player.getEyeLocation().getDirection(), 10, FluidCollisionMode.NEVER);
        if (trace == null || trace.getHitBlockFace() == null) {
            for (ItemStack item : drops) {
                block.getWorld().dropItemNaturally(block.getLocation(), item);
            }
            return;
        }

        switch (trace.getHitBlockFace()) {
            case SOUTH:
                loc.add(new Vector(0, 0, 0.75));
                break;
            case NORTH:
                loc.add(new Vector(0, 0, -0.75));
                break;
            case EAST:
                loc.add(new Vector(0.75, 0, 0));
                break;
            case WEST:
                loc.add(new Vector(-0.75, 0, 0));
                break;
            case UP:
                loc.add(new Vector(0, 0.75, 0));
                break;
            case DOWN:
                loc.add(new Vector(0, -0.75, 0));
                break;
            default:
                // Se non è stata colpita una faccia valida, non fare nulla
                return;
        }

        for (ItemStack item : drops) {
            block.getWorld().dropItemNaturally(loc, item);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {

        if (!PermUtils.hasPermission(event.getPlayer(), "oreexcavation.use")) {
            return;
        }

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
        Collection<ItemStack> drops = event.getBlock().getDrops();
        Player player = event.getPlayer();

        if (Arrays.asList(stone).contains(blockId.toLowerCase())) {
            event.setCancelled(true);
            replaceBlock(player, event.getBlock(), Material.COBBLESTONE, drops);
            return;
        }
        if (Arrays.asList(deepslate).contains(blockId.toLowerCase())) {
            event.setCancelled(true);
            replaceBlock(player, event.getBlock(), Material.COBBLED_DEEPSLATE, drops);
            return;
        }
        if (Arrays.asList(netherrack).contains(blockId.toLowerCase())) {
            event.setCancelled(true);
            replaceBlock(player, event.getBlock(), Material.NETHERRACK, drops);
            return;
        }
        if (Arrays.asList(blackstone).contains(blockId.toLowerCase())) {
            event.setCancelled(true);
            replaceBlock(player, event.getBlock(), Material.BLACKSTONE, drops);
        }
    }
}
