package nl.Steffion.BlockHunt.Listeners;

import nl.Steffion.BlockHunt.BlockHunt;
import nl.Steffion.BlockHunt.PermissionsC.Permissions;
import nl.Steffion.BlockHunt.SignsHandler;
import nl.Steffion.BlockHunt.Managers.PermissionsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class OnSignChangeEvent implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onSignChangeEvent(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] lines = event.getLines();
		if (lines[0] != null) {
			if (lines[0].equalsIgnoreCase("[" + BlockHunt.pdfFile.getName() + "]")) {
				if (PermissionsManager.hasPerm(player, Permissions.signcreate, true)) {
					SignsHandler.createSign(event, lines, event.getBlock().getLocation());
				}
			}
		}
	}
}
