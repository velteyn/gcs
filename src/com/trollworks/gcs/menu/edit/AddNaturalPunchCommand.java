/*
 * Copyright (c) 1998-2014 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as defined
 * by the Mozilla Public License, version 2.0.
 */

package com.trollworks.gcs.menu.edit;

import com.trollworks.toolkit.annotation.Localize;
import com.trollworks.toolkit.utility.Localization;


import com.trollworks.gcs.character.GURPSCharacter;
import com.trollworks.gcs.character.SheetWindow;
import com.trollworks.toolkit.ui.menu.Command;

import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

/** Provides the "Add Natural Punch" command. */
public class AddNaturalPunchCommand extends Command {
	@Localize("Include Punch In Weapons")
	private static String ADD_NATURAL_PUNCH;

	static {
		Localization.initialize();
	}

	/** The action command this command will issue. */
	public static final String					CMD_ADD_NATURAL_PUNCH	= "AddNaturalPunch";			//$NON-NLS-1$

	/** The singleton {@link AddNaturalPunchCommand}. */
	public static final AddNaturalPunchCommand	INSTANCE				= new AddNaturalPunchCommand();

	private AddNaturalPunchCommand() {
		super(ADD_NATURAL_PUNCH, CMD_ADD_NATURAL_PUNCH);
	}

	@Override
	public void adjustForMenu(JMenuItem item) {
		Window window = getActiveWindow();
		if (window instanceof SheetWindow) {
			setEnabled(true);
			setMarked(((SheetWindow) window).getCharacter().includePunch());
		} else {
			setEnabled(false);
			setMarked(false);
		}
		updateMark(item);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		GURPSCharacter character = ((SheetWindow) getActiveWindow()).getCharacter();
		character.setIncludePunch(!character.includePunch());
	}
}