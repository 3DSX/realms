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

	private final List<String> mensajes = Arrays.asList(
			"Bienvenido a Realms. &yUna experienca única, inólvidable",
			"Estas preparado para el &b#Regregso?",
			"Tiempo local: " + TimeUtil.getFormattedDateShort());

	@Override
	public void run() {
		final String prefix = "&b[ANUNCIO] &7";
		final String mensaje;
		final String mensajeAnterior = "";

		//TODO: Message badge to prevent repeating same messages twice, fullfi
		mensaje = RandomUtil.nextItem(mensajes, (String s) -> !s.equals(mensajeAnterior));

		for (final Player player : Remain.getOnlinePlayers())
			Common.tell(player, prefix + mensaje);
	}

}
