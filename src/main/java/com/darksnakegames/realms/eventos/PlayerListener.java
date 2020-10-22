package com.darksnakegames.realms.eventos;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerListener implements Listener {

	private final Set<UUID> proyectil = new HashSet<>();

	@EventHandler
	public void proyectilLaunch(final ProjectileLaunchEvent event) {
		final Projectile proj = event.getEntity();

		if (proj instanceof Egg && proj.getShooter() instanceof Player)
			proyectil.add(proj.getUniqueId());
	}

	@EventHandler
	public void proyectilFall(final ProjectileHitEvent event) {
		if (proyectil.contains(event.getEntity().getUniqueId())) {
			proyectil.remove(event.getEntity().getUniqueId());

			final Player player = (Player) event.getEntity().getShooter();

			final Wolf lobo = player.getWorld().spawn(player.getLocation(), Wolf.class);
			lobo.setAngry(true);
		}

	}
}
