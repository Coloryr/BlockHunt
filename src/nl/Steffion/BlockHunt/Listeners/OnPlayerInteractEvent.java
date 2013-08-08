package nl.Steffion.BlockHunt.Listeners;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.ArenaHandler;
import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Managers.ConfigC;
import nl.Steffion.BlockHunt.Managers.MessageM;
import nl.Steffion.BlockHunt.Managers.PlayerM;
import nl.Steffion.BlockHunt.Managers.PlayerM.PermsC;
import nl.Steffion.BlockHunt.Serializables.LocationSerializable;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnPlayerInteractEvent implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (PlayerM.hasPerm(player, PermsC.create, false)) {
			ItemStack item = player.getItemInHand();
			if (item.getType() != Material.AIR) {
				if (item.getItemMeta().hasDisplayName()) {
					ItemMeta im = item.getItemMeta();
					if (im.getDisplayName().equals(
							MessageM.replaceAll((String) W.config
									.get(ConfigC.wandName)))) {
						Action action = event.getAction();
						if (event.hasBlock()) {
							LocationSerializable location = new LocationSerializable(
									event.getClickedBlock().getLocation());
							if (action.equals(Action.LEFT_CLICK_BLOCK)) {
								event.setCancelled(true);
								if (W.pos1.get(player) == null
										|| !W.pos1.get(player).equals(location)) {
									MessageM.sendFMessage(
											player,
											ConfigC.normal_wandSetPosition,
											true,
											"number-1",
											"pos-%N(%A" + location.getBlockX()
													+ "%N, %A"
													+ location.getBlockY()
													+ "%N, %A"
													+ location.getBlockZ()
													+ "%N)");
									W.pos1.put(player, location);
								}
							} else if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
								event.setCancelled(true);
								if (W.pos2.get(player) == null
										|| !W.pos2.get(player).equals(location)) {
									MessageM.sendFMessage(
											player,
											ConfigC.normal_wandSetPosition,
											true,
											"number-2",
											"pos-%N(%A" + location.getBlockX()
													+ "%N, %A"
													+ location.getBlockY()
													+ "%N, %A"
													+ location.getBlockZ()
													+ "%N)");
									W.pos2.put(player, location);
								}
							}
						}
					}
				}
			}
		}

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock() != null) {
				if (event.getClickedBlock().getType()
						.equals(Material.SIGN_POST)
						|| event.getClickedBlock().getType()
								.equals(Material.WALL_SIGN)) {
					Sign sign = (Sign) event.getClickedBlock().getState();
					if (sign.getLine(1) != null) {
						if (sign.getLine(1).contains("LEAVE")) {
							if (PlayerM.hasPerm(player, PermsC.joinsign, true)) {
								ArenaHandler.playerLeaveArena(player, true,
										true);
							}
						} else {
							for (Arena arena : W.arenaList) {
								if (sign.getLines()[1]
										.contains(arena.arenaName)) {
									if (PlayerM.hasPerm(player,
											PermsC.joinsign, true)) {
										ArenaHandler.playerJoinArena(player,
												arena.arenaName);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}