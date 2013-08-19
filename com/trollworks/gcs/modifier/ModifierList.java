/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is GURPS Character Sheet.
 *
 * The Initial Developer of the Original Code is Richard A. Wilkes.
 * Portions created by the Initial Developer are Copyright (C) 1998-2002,
 * 2005-2011 the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *
 * ***** END LICENSE BLOCK ***** */

package com.trollworks.gcs.modifier;

import com.trollworks.gcs.common.ListFile;
import com.trollworks.gcs.common.LoadState;
import com.trollworks.ttk.widgets.outline.OutlineModel;
import com.trollworks.ttk.widgets.outline.Row;
import com.trollworks.ttk.xml.XMLNodeType;
import com.trollworks.ttk.xml.XMLReader;

import java.awt.image.BufferedImage;
import java.io.IOException;

/** Data Object to hold several {@link Modifier} */
public class ModifierList extends ListFile {
	private static final int	CURRENT_VERSION	= 1;
	/** The XML tag for advantage lists. */
	public static final String	TAG_ROOT		= "modifier_list";	//$NON-NLS-1$

	/** Creates new {@link ModifierList}. */
	public ModifierList() {
		super();
	}

	/**
	 * Creates a new {@link ModifierList}.
	 * 
	 * @param modifiers The {@link ModifierList} to clone.
	 */
	public ModifierList(ModifierList modifiers) {
		this();
		for (Row Row : modifiers.getModel().getRows()) {
			getModel().getRows().add(Row);
		}
	}

	@Override
	protected void loadList(XMLReader reader, LoadState state) throws IOException {
		OutlineModel model = getModel();
		String marker = reader.getMarker();
		do {
			if (reader.next() == XMLNodeType.START_TAG) {
				String name = reader.getName();

				if (Modifier.TAG_MODIFIER.equals(name)) {
					model.addRow(new Modifier(this, reader, state), true);
				} else {
					reader.skipTag(name);
				}
			}
		} while (reader.withinMarker(marker));
	}

	@Override
	public BufferedImage getFileIcon(boolean large) {
		return null;
	}

	@Override
	public int getXMLTagVersion() {
		return CURRENT_VERSION;
	}

	@Override
	public String getXMLTagName() {
		return TAG_ROOT;
	}
}