/*******************************************************************************
 * Copyright (C) 2015-2023 Andreas Redmer <ar-appleflinger@abga.be>
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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.gitlab.ardash.appleflinger.helpers.Pref;

public class I18N {
	
	public enum k{
		gameName,startNewGame
	}

//	private static final String BUNDLE_NAME = "com.gitlab.ardash.appleflinger.i18n.af"; 

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
//			return "af";
			return "assets/af";
		case Applet:
			return "";
		case Desktop:
			return "af";
//			return "assets/af";
		case HeadlessDesktop:
			return "assets/af";
		case WebGL:
			return "";
		case iOS:
			return "";
		default:
			throw new RuntimeException("Unknown application type " + Gdx.app.getType());
		}
	}
	
	public static void loadLanguageBundle(String languageCode)
	{
		Pref.setLingo(languageCode);
		String bundleName;
		if (languageCode.equals(""))
		{
			// set to system default
			bundleName = getBundleBaseName();
		}
		else
		{
			bundleName = getBundleBaseName()+"_"+languageCode;
		}
		RESOURCE_BUNDLE = ResourceBundle.getBundle(bundleName,   
				new GdxFileControl("UTF-8", FileType.Classpath));
	}

	public static String getString(String key) {
		return s(key);
	}
	
	public static String s(String key) {
		try {
			String val = RESOURCE_BUNDLE.getString(key);
			return val;
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		} 
	}
	
	public static String g(k key) {
		return RESOURCE_BUNDLE.getString(key.name());
	}
	
	public static boolean isUserOccupiedByAFascistRegime() {
		// Parts of Ukraine and Georgia are occupied by Russia. These parts are variant.
		// We don't know where exactly the users are and what settings they use.
		// The occupied territories of Japan are not relevant for this function.
		String li = Pref.getLingo();
		if (li.equals("ru") || li.equals("uk")) {
			return true;
		}
		Locale lo = Locale.getDefault();
		if (lo != null) {
			String lc = lo.getCountry().toLowerCase();
			String ll = lo.getLanguage().toLowerCase();
			// String ls = lo.getScript().toLowerCase(); cyrl is also used in greek and serb
			
			if (lc.equals("ru")||lc.equals("ua")||lc.equals("ge")||lc.equals("by")) {
				return true;
			}
			
			if (ll.equals("ru")||ll.equals("uk")||ll.equals("ka")||ll.equals("be")) {
				return true;
			}
			
			try {
				String lc3 = lo.getISO3Country().toLowerCase();
				if (lc3.equals("rus")||lc3.equals("ukr")||lc3.equals("geo")||lc3.equals("blr")) {
					return true;
				}
				String ll3 = lo.getISO3Language();
				if (ll3.equals("rus")||ll3.equals("ukr")||ll3.equals("geo")||ll3.equals("kat")||ll3.equals("bel")) {
					return true;
				}
			} catch (MissingResourceException e) {
				return false;
			}
		}
		return false;
	}
}
