package nl.Steffion.BlockHunt.Managers;

import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Commands.*;
import nl.Steffion.BlockHunt.Managers.PlayerM.PermsC;

public enum CommandC {
	/*
	 * Made by @author Steffion, � 2013.
	 */

	INFO ("BlockHunt%_",
			"BlockHunt%_",
			new CMDinfo(),
			ConfigC.commandEnabled_info,
			PermsC.info,
			ConfigC.help_info,
			0,
			"-"),
	INFO2 ("BlockHunt%info_",
			"BlockHunt%i_",
			new CMDinfo(),
			ConfigC.commandEnabled_info,
			PermsC.info,
			ConfigC.help_info,
			1,
			W.pluginName + " [info|i]"),
	HELP ("BlockHunt%help_",
			"BlockHunt%h_",
			new CMDhelp(),

			ConfigC.commandEnabled_help,
			PermsC.help,
			ConfigC.help_help,
			1,
			W.pluginName + " <help|h> [pagenumber]"),
	RELOAD ("BlockHunt%reload_",
			"BlockHunt%r_",
			new CMDreload(),
			ConfigC.commandEnabled_reload,
			PermsC.reload,
			ConfigC.help_reload,
			1,
			W.pluginName + " <reload|r>"),
	WAND ("BlockHunt%wand_",
			"BlockHunt%w_",
			new CMDwand(),
			ConfigC.commandEnabled_wand,
			PermsC.create,
			ConfigC.help_wand,
			1,
			W.pluginName + " <wand|w>"),
	CREATE ("BlockHunt%create_",
			"BlockHunt%c_",
			new CMDcreate(),
			ConfigC.commandEnabled_create,
			PermsC.create,
			ConfigC.help_create,
			1,
			W.pluginName + " <create|c> <arenaname>"),
	NOT_FOUND ("%_",
			"%_",
			new CMDnotfound(),
			null,
			PermsC.info,
			ConfigC.help_info,
			0,
			"-");

	public String command;
	public String alias;
	public DefaultCMD cmd;
	public ConfigC enabled;
	public PermsC perm;
	public ConfigC help;
	public int minLenght;
	public String usage;

	private CommandC (String command, String alias, DefaultCMD cmd,
			ConfigC enabled, PermsC perm, ConfigC help, int minLenght,
			String usage) {
		this.command = command;
		this.alias = alias;
		this.cmd = cmd;
		this.enabled = enabled;
		this.perm = perm;
		this.help = help;
		this.minLenght = minLenght;
		this.usage = usage;
	}
}