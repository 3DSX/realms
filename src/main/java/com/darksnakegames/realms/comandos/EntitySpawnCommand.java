package com.darksnakegames.realms.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;

import java.util.ArrayList;
import java.util.List;

public class EntitySpawnCommand extends SimpleCommand {
	public EntitySpawnCommand() {
		super("crear");

		setMinArguments(1);
		setUsage("<type> [x] [y] [z] [world]");
		setDescription("Create a creature");
	}

	@Override
	protected void onCommand() {

		checkConsole();

		final EntityType entity = findEnum(EntityType.class, args[0],
				"&cCreature {enum} is invalid");

		checkBoolean(entity.isSpawnable(), entity.toString() + "is not a spawnable Creature");
		checkBoolean(entity.isAlive(), entity.toString() + "is not an alive Creature");

		final Location location;
		final World world;

		if (args.length == 4) {
			final int x = findNumber(1, "Specify an X coordinate");
			final int y = findNumber(2, "Specify an Y coordinate");
			final int z = findNumber(3, "Specify a Z coordinate");

			location = new Location(getPlayer().getWorld(), x, y, z);


		} else if (args.length == 5) {
			final int x = findNumber(1, "Specify an X coordinate");
			final int y = findNumber(2, "Specify an Y coordinate");
			final int z = findNumber(3, "Specify a Z coordinate");

			checkBoolean(checkWorldExists(args[4]), "This world does not exist");
			world = Bukkit.getServer().getWorld(args[4]);

			location = new Location(world, x, y, z);

		} else {
			location = getPlayer().getLocation();
		}

		checkNotNull(location.getWorld().spawnEntity(location, entity), "An error ocurred. ERR EBN9C. Maybe the world does not exist?");

		tell("&eSpawned " + entity.toString() + " in " + Common.shortLocation(location));

	}

	@Override
	protected List<String> tabComplete() {
		switch (args.length) {
			case 1:
				return completeLastWord(EntityType.values());
			case 2:
				return completeLastWord(getPlayer().getLocation().getBlockX());
			case 3:
				return completeLastWord(getPlayer().getLocation().getBlockY());
			case 4:
				return completeLastWord(getPlayer().getLocation().getBlockZ());
			case 5:
				return completeLastWord(Common.getWorldNames());
		}

		return new ArrayList<>();
	}

	private static boolean checkWorldExists(final String world) {
		return Common.getWorldNames().contains(world);
	}
}
