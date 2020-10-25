package com.darksnakegames.realms.comandos.grupos.Admin;

import com.darksnakegames.realms.RealmsPlugin;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;
import org.mineacademy.fo.remain.Remain;

import java.util.concurrent.TimeUnit;

public class HideFrom extends SimpleSubCommand {

	//TODO: implement in JoinEvent. Ref: private final Set<UUID> adminsOcultosParaTodos = new HashSet<>();

	public HideFrom(final SimpleCommandGroup parent) {
		super(parent, "hide|h");

		setMinArguments(1);

		setUsage("<player>");

		setDescription("&cAdmin Hide function");

		setCooldown(1, TimeUnit.SECONDS);
		setCooldownMessage("Do NOT Spam!");
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final Player player = getPlayer();

		if (args[0].equalsIgnoreCase("all")) {
			for (final Player playerObj : Remain.getOnlinePlayers()) {
				if (playerObj.canSee(player))
					playerObj.hidePlayer(RealmsPlugin.getInstance(), player);
				else
					playerObj.showPlayer(RealmsPlugin.getInstance(), player);
			}

		} else {
			final Player target = findPlayer(args[0]);
			checkBoolean(!player.getName().equals(target.getName()),
					"for hiding yourself to anyone use /admin hide all");

			if (target.canSee(player))
				target.hidePlayer(RealmsPlugin.getInstance(), player);
			else
				target.showPlayer(RealmsPlugin.getInstance(), player);
		}


	}
}
