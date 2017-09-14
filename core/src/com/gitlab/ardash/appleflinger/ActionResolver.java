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
package com.gitlab.ardash.appleflinger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.gitlab.ardash.appleflinger.i18n.I18N;

public interface ActionResolver {
	
	public static final String recommendationText = I18N.getString("twitterRecommendationtext"); //$NON-NLS-1$
	public static final String marketUrl = "https://play.google.com/store/apps/details?id=com.gitlab.ardash.appleflinger.android"; //$NON-NLS-1$
	
	public static class ARH
	{
		
		public static String urlEncode(String s) {
		    try {
		        return URLEncoder.encode(s, "UTF-8"); //$NON-NLS-1$
		    }
		    catch (UnsupportedEncodingException e) {
		        //Log.wtf(TAG, "UTF-8 should always be supported", e);
		        //throw new RuntimeException("URLEncoder.encode() failed for " + s);
		    }
		
		return ""; //$NON-NLS-1$
		}
	}
	
	
	public boolean twPostRecommendation();
	public boolean fbPostRecommendation();
	public boolean gpPostRecommendation();
	public boolean piPostRecommendation();

	public void submitScoreGPGS(String LeaderBoardId, int score);
	public void unlockAchievementGPGS(String achievementId);
	public void incrementAchievementGPGS(String achievementId, int NumSteps);
	public void getLeaderboardGPGS(String LeaderBoardId);
	public void getAchievementsGPGS();
	
}
