package it.icewolf23x.plugins.events.utils;

import it.icewolf23x.plugins.oreextraction.OreExtraction;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class PermUtils {
    private static final LuckPerms api = OreExtraction.getLuckPermsApi();

    public static boolean hasPermission(Player player, String permission) {
        assert api != null;
        User user = api.getUserManager().getUser(player.getUniqueId());
        assert user != null;
        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
}
