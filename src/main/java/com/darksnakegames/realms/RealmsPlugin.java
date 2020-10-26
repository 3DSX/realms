package com.darksnakegames.realms;

import com.darksnakegames.realms.comandos.CoheteCommand;
import com.darksnakegames.realms.comandos.EntitySpawnCommand;
import com.darksnakegames.realms.comandos.grupos.AdminCommands;
import com.darksnakegames.realms.eventos.PlayerListener;
import com.darksnakegames.realms.tareas.AnuncioTarea;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;

public class RealmsPlugin extends SimplePlugin {

	private AnuncioTarea anuncio;

	@Override
	public void onPluginStart() {

		/**
		 * Commands
		 */
		registerCommand(new CoheteCommand());
		registerCommand(new EntitySpawnCommand());
		registerCommands("admin|a", new AdminCommands());

		/**
		 * Events
		 */
		registerEvents(new PlayerListener());

		/**
		 * Tasks
		 */
		anuncio = new AnuncioTarea();
		anuncio.runTaskTimer(this, 0, 30 * 20);
	}

	private void pararAnuncio() {
		if (anuncio != null) {
			try {
				anuncio.cancel();
			} catch (final IllegalStateException ex) {
			}
		}
	}

	@Override
	protected void onPluginStop() {
		pararAnuncio();
	}

	@Override
	protected void onPluginReload() {
		pararAnuncio();
	}

	@EventHandler
	public void onJoin(final PlayerJoinEvent event) {
		val player = event.getPlayer();

		Common.broadcast(player.getName() + " Se ha unido");

		player.getInventory().addItem(new ItemStack(Material.ACACIA_LOG
				, 1));
	}
}
