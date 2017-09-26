/*******************************************************************************
 * Copyright (C) 2015-2017 Andreas Redmer <andreasredmer@mailchuck.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.gitlab.ardash.appleflinger.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.badlogic.gdx.Gdx;
import com.gitlab.ardash.appleflinger.helpers.Pref;

public class I18N {
	
	public enum k{
		gameName,startNewGame
	}

//	private static final String BUNDLE_NAME = "com.gitlab.ardash.appleflinger.i18n.af"; //$NON-NLS-1$

//	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	// the following line need to be in release, otherwise the class wont be found after optimization
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle (getBundleBaseName());

	static
	{
		loadLanguageBundle(Pref.getLingo());
	}
	
	private I18N() {
	}
	
	private static String getBundleBaseName()
	{
		switch (Gdx.app.getType()) {
		case Android:
			return "assets/af";
		case Applet:
			return "";
		case Desktop:
//			return "com.gitlab.ardash.appleflinger.i18n.af";
			return "af";
		case HeadlessDesktop:
			return "assets/af";
		case WebGL:
			return "";
		case iOS:
			return "";
		}
//		return "com.gitlab.ardash.appleflinger.i18n.af";
		throw new RuntimeException("Unknown application type " + Gdx.app.getType());
	}
	
	public static void loadLanguageBundle(String languageCode)
	{
		Pref.setLingo(languageCode);
		if (languageCode.equals(""))
		{
			// set to system default
			RESOURCE_BUNDLE = ResourceBundle.getBundle (getBundleBaseName());
			return;
		}
		RESOURCE_BUNDLE = ResourceBundle.getBundle (getBundleBaseName()+"_"+languageCode);
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String s(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String g(k key) {
		return RESOURCE_BUNDLE.getString(key.name());
	}
}
