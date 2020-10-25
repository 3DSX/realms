package com.darksnakegames.realms.comandos.grupos;

import com.darksnakegames.realms.comandos.grupos.Admin.HideFrom;
import org.mineacademy.fo.command.SimpleCommandGroup;

public class AdminCommands extends SimpleCommandGroup {
	@Override
	protected void registerSubcommands() {
		registerSubcommand(new HideFrom(this));
	}

	@Override
	protected String getCredits() {
		return "DK Games SL";
	}


}
