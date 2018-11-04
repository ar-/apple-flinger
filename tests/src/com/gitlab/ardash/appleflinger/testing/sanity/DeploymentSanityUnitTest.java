/*******************************************************************************
 * Copyright (C) 2015-2018 Andreas Redmer <ar-appleflinger@abga.be>
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

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.gitlab.ardash.appleflinger.ai.HCStrategy;
import com.gitlab.ardash.appleflinger.global.Assets;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.missions.Mission;
import com.gitlab.ardash.appleflinger.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class DeploymentSanityUnitTest {

	@Test
	public void isDebugDisabled() {
		assertFalse(GameManager.DEBUG);
	}

	@Test
	public void isDebugZoomDisabled() {
		assertFalse(GameManager.DEBUGZOOM);
	}

	@Test
	public void isSandboxDisabled() {
		assertFalse(GameManager.SANDBOX);
	}

	@Test
	public void isAlllevelsDisabled() {
		assertFalse(GameManager.ALLLEVELS);
	}
	
	@Test
	public void isRecordingDisabled() {
		assertFalse(GameManager.RECORDSHOTS);
	}
	
	@Test
	public void areAllMissionsRecordedInAI() {
		HCStrategy.validate();
	}
	
	@Test
	public void areAllMissionsValid() {
		Mission.validate();
	}
	
	@Test
	public void areAllAssetsValid() {
		Assets.validate();
	}
}
