/*******************************************************************************
 * Copyright (C) 2015-2018 Andreas Redmer <ar-appleflinger@abga.be>
 * Copyright (C) 2017 Сухичев Михаил Иванович <sukhichev@yandex.ru>
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
package com.gitlab.ardash.appleflinger.testing.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.gitlab.ardash.appleflinger.i18n.I18N;
import com.gitlab.ardash.appleflinger.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class AssetTest {

	@Test
	public void arePngFilesComplete() {
		assertTrue("Asset file not found.", Gdx.files
				.internal("assets/scene.png").exists());
		assertTrue("Asset file not found.", Gdx.files
				.internal("assets/backgr.png").exists());
	}
	
	@Test
	public void isI18NWorking() {
		I18N.loadLanguageBundle("en");
		assertEquals("Yes", I18N.s("yes"));
		I18N.loadLanguageBundle("");
		assertEquals("Yes", I18N.s("yes"));
	}

	@Test
	public void isI18NWorkingForNB() {
		I18N.loadLanguageBundle("nb");
		assertEquals("låst", I18N.s("locked"));
	}
	
	@Test
	public void isI18NWorkingForDE() {
		I18N.loadLanguageBundle("de");
		assertEquals("Lautstärke", I18N.s("Volume"));
	}
	
	@Test
	public void isI18NWorkingForEO() {
		I18N.loadLanguageBundle("eo");
		assertEquals("ŝargado", I18N.s("loading"));
		assertEquals("PAŬZO", I18N.s("PAUSE"));
	}
	
	@Test
	public void isI18NWorkingForES() {
		I18N.loadLanguageBundle("es");
		assertEquals("sí", I18N.s("yes"));
	}
	
	@Test
	public void isI18NWorkingForFR() {
		I18N.loadLanguageBundle("fr");
		assertEquals("touche l'écran pour continuer", I18N.s("touchScreenToContinue"));
		assertEquals("JEU TERMINÉ", I18N.s("gameOver"));
	}
	
	@Test
	public void isI18NWorkingForPL() {
		I18N.loadLanguageBundle("pl");
		assertEquals("domyślny", I18N.s("default"));
		assertEquals("Potwierdź", I18N.s("okay"));
	}
	
	@Test
	public void isI18NWorkingForPT() {
		I18N.loadLanguageBundle("pt");
		assertEquals("Não", I18N.s("no"));
		assertEquals("entrada necessária", I18N.s("inputRequired"));
	}
	
	@Test
	public void isI18NWorkingForRU() {
		I18N.loadLanguageBundle("ru");
		assertEquals("Громкость", I18N.s("Volume"));
		assertEquals("Да", I18N.s("yes"));
		assertEquals("ИГРА ОКОНЧЕНА", I18N.s("gameOver"));
	}
	
	@Test
	public void arePrpertyFilesComplete() {
		assertTrue("Asset file not found.", Gdx.files
				.internal("assets/af.properties").exists());
	}
}
