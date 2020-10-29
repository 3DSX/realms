package com.darksnakegames.realms.tareas;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.RandomUtil;
import org.mineacademy.fo.TimeUtil;
import org.mineacademy.fo.remain.Remain;

import java.util.Arrays;
import java.util.List;

public class AnuncioTarea extends BukkitRunnable {

	private String mensajeAnterior = "";

	private final List<String> mensajes = Arrays.asList(
			"Bienvenido a Realms. &yUna experienca única, inólvidable",
			"Estas preparado para el &b#Regregso?",
			"Tiempo local: " + TimeUtil.getFormattedDateShort());

	@Override
	public void run() {
		final String prefix = "&b[ANUNCIO] &7";
		String mensaje;

		if (mensajeAnterior.equals("")) {
			mensaje = RandomUtil.nextItem(mensajes);
		} else {
			do {
				mensaje = RandomUtil.nextItem(mensajes);
			} while (mensajeAnterior.equals(mensaje));
		}

		//TODO: Message badge to prevent repeating same messages twice

		mensajeAnterior = mensaje;

		for (final Player player : Remain.getOnlinePlayers())
			Common.tell(player, prefix + mensaje);
	}
}
