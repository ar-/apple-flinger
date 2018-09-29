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
package com.gitlab.ardash.appleflinger.global;

import java.util.EnumSet;

import com.gitlab.ardash.appleflinger.helpers.HasNextStates;

@SuppressWarnings("all")
public enum GameState implements HasNextStates {

	START_APP {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(LOADING_SCREEN);
			return ret;
		}
	},
	LOADING_SCREEN {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(MAIN_MENU,MISSION_SELECT,SETTINGS_MENU,INIT_WORLD);
			return ret;
		}
	},
	MAIN_MENU {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(LOADING_SCREEN);
			return ret;
		}
	},
	MISSION_SELECT {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(LOADING_SCREEN);
			return ret;
		}
	},
	INIT_WORLD {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(WAIT_FOR_PHYSICS);
			return ret;
		}
	},
	WAIT_FOR_PHYSICS {
		public EnumSet<GameState> nexts() {
			// with the pause screen, this state can go directly to loading screen (return to menu)
			EnumSet<GameState> ret = EnumSet.of(WAIT_FOR_DRAG,GAME_OVER_SCREEN,LOADING_SCREEN);
			return ret;
		}
	},
	/// waiting for user to pull the projectile (could also be wait for input, in case the setting screen or another click event (non-drag))
	WAIT_FOR_DRAG {
		// with the pause screen, this state can go directly to loading screen (return to menu)
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(DRAGGING,LOADING_SCREEN);
			return ret;
		}
	},
	/// projectile is being dragged right now by user
	/// can also come to loading screen if paused on drag (especially AI-drag) (fixes #23)
	DRAGGING {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(WAIT_FOR_PHYSICS,LOADING_SCREEN);
			return ret;
		}
	},
	GAME_OVER_SCREEN {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(LOADING_SCREEN);
			return ret;
		}
	},
	SETTINGS_MENU {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.of(MAIN_MENU);
			return ret;
		}
	},
	END {
		public EnumSet<GameState> nexts() {
			EnumSet<GameState> ret = EnumSet.noneOf(GameState.class);
			return ret;
		}
	};

}
