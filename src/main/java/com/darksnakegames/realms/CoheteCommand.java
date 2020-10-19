package com.darksnakegames.realms;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommand;

public class CoheteCommand extends SimpleCommand {
	protected CoheteCommand() {
		super("fuego");
		final CoheteCommand cm = new CoheteCommand();

		//final List<String> lista = new ArrayList<>();
	}

	@Override
	protected void onCommand() {

		checkConsole();

		final Player player = getPlayer();

		final Entity t = player.getWorld().spawn(player.getLocation(), Firework.class);

		tell("Prueba " + t.getName());


	}
}
