/*******************************************************************************
 * Copyright (C) 2017-2018 Andreas Redmer <ar-appleflinger@abga.be>
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
package com.gitlab.ardash.appleflinger.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gitlab.ardash.appleflinger.AdvancedStage;
import com.gitlab.ardash.appleflinger.actors.GeneralTargetActor;
import com.gitlab.ardash.appleflinger.global.GameManager;
import com.gitlab.ardash.appleflinger.global.PlayerStatus.PlayerSide;
import com.gitlab.ardash.appleflinger.missions.Mission;

public class HCStrategy implements Strategy {
	/**
	 * Map from mission to shotlist.
	 * They  are intentionally all in one list, so if one mission
	 * is out of options, then shots of other missions can be taken.
	 */
	private List<Shot> allShots = new HCStrategyData();
	
	/**
	 * A shot straight forward. Only to be used if there are no enemies left, to gain some more points.
	 * This is the shot that fixes the crash in #33 . 
	 */
	private static Vector2 defaultPullVector = new Vector2(0.7672596f,-0.22652423f);

	@Override
	public Vector2 getPullVector() 
	{
		Vector2 ret;

		// select target
		final List<Vector2> targets = getCurrentTargets();
		final int numberOfTargets = targets.size();
		if (numberOfTargets == 0 )
			return defaultPullVector; // fix for #33
		final int selectedTargetId = MathUtils.random(numberOfTargets-1);
		Vector2 target = targets.get(selectedTargetId);
		
		// select shot
		final List<Shot> shotsForTarget = getShotsForTarget(target);
		final int numberOfShots = shotsForTarget.size();
		if (numberOfShots == 0 )
			return defaultPullVector; // fix for #33
		final int selectedShotId = MathUtils.random(numberOfShots-1);
		ret= shotsForTarget.get(selectedShotId).pullVector;
		
		return ret;
	}
	
	private List<Shot> getShotsForTarget(Vector2 t)
	{
		List<Shot> ret = new ArrayList<>();
		final float targetSize = 0.9f;
		List<Shot> possibleShots = getShotsOfCurrentMission();

		// first try to pick only shots with target in range
		for (Shot shot : possibleShots) {
			final float distance = shot.targetpos.dst(t);
			if (distance<=targetSize)
			{
				ret.add(shot);
			}
		}
		
		// second try the shot closest to the target
		if (ret.isEmpty())
		{
			float MINDIST = Float.MAX_VALUE;
			Shot minShot = null;
			for (final Shot shot : possibleShots) 
			{
				final float distance = shot.targetpos.dst(t);
				if (distance<=MINDIST)
				{
					MINDIST = distance;
					minShot = shot;
				}
			}
			ret.add(minShot);
		}
		
		// don't crash the game, but log it, because this is serious malfunction
		if (ret.isEmpty())
			Gdx.app.error(getClass().getName(), "No shots to shoot, AI has no training at all");
		
		return ret;
	}
	
	private List<Shot> getShotsOfCurrentMission()
	{
		return getShotsOfMission(GameManager.getInstance().getCurrentMission());
	}

	private List<Shot> getShotsOfMission(Mission mission)
	{
		List<Shot> ret = new ArrayList<>();
		for (Shot sh : allShots) {
			if (sh.mission.equals(mission))
			{
				ret.add(sh);
			}
		}
		return ret;
	}
	
	public static void validate()
	{
		HCStrategy hcs = new HCStrategy();
		for (Mission m : Mission.values())
		{
			final List<Shot> shotsOfMission = hcs.getShotsOfMission(m);
			if (Mission.getDummies().contains(m))
				continue;
			if (shotsOfMission.isEmpty())
			{
				throw new RuntimeException("no HCStrategy for Mission "+m);
			}
		}
	}

	private static List<Vector2> getCurrentTargets()
	{
		List<Vector2> ret = new ArrayList<>();
		GameManager gm = GameManager.getInstance();
		final AdvancedStage stage = gm.currentGameScreen.getRenderer().world.stage;
		Set<Actor> allActors = stage.getAllActors();
		for (Actor a : allActors) {
			if (a instanceof GeneralTargetActor) {
				GeneralTargetActor ta = (GeneralTargetActor) a;
				if (ta.getPlayerSide()==PlayerSide.LEFT)
				{
					Vector2 v = new Vector2(a.getX()+a.getOriginX(),a.getY()+a.getOriginY());
					ret.add(v);
				}
			}
		}
		
		// this is commented, if can happen if player kill own targets
		//if (ret.isEmpty()) throw new RuntimeException("No targets found to aim for");
		return ret;
	}

	class HCStrategyData extends ArrayList<Shot>
	{
		private static final long serialVersionUID = 1403750377307534836L;

		public HCStrategyData() {
			add(new Shot(Mission.M_1_1, 0.767046f, -0.22724557f, 3.6239533f, 1.4658197f, 71.17609f));
			add(new Shot(Mission.M_1_1, 0.72449684f, -0.3392701f, 2.616177f, 1.7099137f, 65.10938f));
			add(new Shot(Mission.M_1_1, 0.78013515f, -0.17716813f, 3.759899f, 1.1767365f, 75.71155f));
			add(new Shot(Mission.M_1_1, 0.7940321f, -0.0975368f, 4.54239f, 1.0636654f, 79.413055f));
			add(new Shot(Mission.M_1_2, 0.75156593f, -0.27413154f, 1.7935802f, 1.2075309f, 63.296646f));
			add(new Shot(Mission.M_1_2, 0.75156593f, -0.27413154f, 1.1054798f, 1.0515836f, 9.392569f));
			add(new Shot(Mission.M_1_2, 0.7849655f, -0.15436876f, 3.3178306f, 1.0877277f, 68.87878f));
			add(new Shot(Mission.M_1_2, 0.75423527f, -0.2667004f, 1.1590805f, 0.978038f, 47.01944f));
			add(new Shot(Mission.M_1_2, 0.75423527f, -0.2667004f, 0.7008432f, 0.75791f, 8.082604f));
			add(new Shot(Mission.M_1_2, 0.76063156f, -0.24786973f, 2.020618f, 1.1998295f, 41.868576f));
			add(new Shot(Mission.M_1_2, 0.76063156f, -0.24786973f, 0.7131124f, 1.6348642f, 6.0445075f));
			add(new Shot(Mission.M_1_2, 0.76063156f, -0.24786973f, 2.8677454f, 1.2004548f, 1.0030509f));
			add(new Shot(Mission.M_1_3, 0.7728157f, -0.20677352f, 1.8900845f, 1.9135134f, 51.02276f));
			add(new Shot(Mission.M_1_3, 0.33848858f, -0.72486234f, 2.546601f, 0.3310855f, 87.79859f));
			add(new Shot(Mission.M_1_3, 0.7912359f, -0.11809492f, 1.1709759f, 0.44473004f, 58.72811f));
			add(new Shot(Mission.M_1_3, 0.36737442f, -0.710659f, 1.8398266f, 0.60376203f, 7.679185f));
			add(new Shot(Mission.M_1_3, 0.3114519f, -0.73688364f, 3.495739f, 1.7320778f, 71.62326f));
			add(new Shot(Mission.M_1_3, 0.3152876f, -0.73525095f, 3.0184863f, 0.5096152f, 20.765867f));
			add(new Shot(Mission.M_1_3, 0.35827732f, -0.7152884f, 2.2367494f, 0.50483644f, 26.634754f));
			add(new Shot(Mission.M_1_3, 0.40698338f, -0.68874145f, 1.4024985f, 0.45256186f, 49.7718f));
			
			add(new Shot(Mission.M_1_4, 0.2844863f, -0.7477083f, 2.3721464f, 3.9060338f, 4.2135177f));
			add(new Shot(Mission.M_1_4, 0.26926613f, -0.7533231f, 2.218674f, 3.9090383f, 14.677414f));
			add(new Shot(Mission.M_1_4, 0.34197617f, -0.72322345f, 1.2844435f, 4.207894f, 8.690521f));
			add(new Shot(Mission.M_1_4, 0.28863335f, -0.7461171f, 1.7324924f, 3.784243f, 10.385524f));
			add(new Shot(Mission.M_1_4, 0.45624924f, -0.13437486f, 2.5772884f, 0.483374f, 16.576803f));
			add(new Shot(Mission.M_1_4, 0.79870987f, 0.045411825f, 1.397393f, 0.5024735f, 64.39398f));
			add(new Shot(Mission.M_1_5, 0.77446365f, -0.20051432f, 4.1341567f, 1.3310504f, 15.2541065f));
			add(new Shot(Mission.M_1_5, 0.7545147f, -0.26590848f, 3.6076832f, 2.8720195f, 61.02682f));
			add(new Shot(Mission.M_1_5, 0.2983265f, -0.74229455f, 4.0580354f, 1.004458f, 8.8101f));
			add(new Shot(Mission.M_1_5, 0.6213007f, -0.5039692f, 0.72882205f, 2.516105f, 7.5791216f));
			add(new Shot(Mission.M_1_5, 0.7965441f, -0.07427573f, 1.9270122f, 0.18366815f, 24.393955f));
			add(new Shot(Mission.M_1_6, 0.77446365f, -0.20051432f, 2.6359732f, 1.9697862f, 12.547957f));
			add(new Shot(Mission.M_1_6, 0.77446365f, -0.20051432f, 2.5521271f, 1.8938808f, 1.9752927f));
			add(new Shot(Mission.M_1_6, 0.7448263f, -0.29194903f, 1.7286342f, 1.9736757f, 2.1299653f));
			add(new Shot(Mission.M_1_6, 0.76516247f, -0.23350978f, 2.414975f, 1.9222932f, 76.49876f));
			add(new Shot(Mission.M_1_6, 0.76516247f, -0.23350978f, 1.4359487f, 1.9794724f, 2.2498624f));
			add(new Shot(Mission.M_1_6, 0.70835114f, -0.37180448f, 4.015095f, 0.5642649f, 5.7038603f));
			add(new Shot(Mission.M_1_6, 0.78157234f, 0.17071915f, 4.4421325f, 0.18494691f, 37.61045f));
			add(new Shot(Mission.M_1_6, 0.7528467f, -0.27059603f, 1.4740788f, 1.5400753f, 79.40512f));
			add(new Shot(Mission.M_1_6, 0.7528467f, -0.27059603f, 1.7868953f, 0.53073496f, 4.652601f));
			add(new Shot(Mission.M_1_6, 0.79983044f, -0.0164783f, 2.9296174f, 0.5335885f, 3.1295254f));
			add(new Shot(Mission.M_1_6, 0.7998657f, 0.014676571f, 3.0495803f, 0.41170335f, 44.39424f));
			add(new Shot(Mission.M_1_6, 0.79290104f, -0.10633659f, 1.8154333f, 0.51235706f, 45.814022f));
			add(new Shot(Mission.M_1_6, 0.7787924f, -0.18298292f, 1.0954132f, 0.3156988f, 12.533438f));
			add(new Shot(Mission.M_1_7, 0.7997494f, 0.02004385f, 4.7549725f, 0.9865125f, 73.497284f));
			add(new Shot(Mission.M_1_7, 0.68971825f, -0.405326f, 2.7745137f, 2.4252288f, 33.47885f));
			add(new Shot(Mission.M_1_7, 0.5468979f, -0.5838685f, 0.7879813f, 2.7107089f, 38.31393f));
			add(new Shot(Mission.M_1_7, 0.7665453f, -0.22892976f, 3.1969728f, 1.4896067f, 62.63134f));
			add(new Shot(Mission.M_1_7, 0.7303257f, -0.32653332f, 0.68034506f, 1.1915736f, 8.1161785f));
			add(new Shot(Mission.M_1_7, 0.7802429f, -0.1766963f, 2.3153882f, 0.9932322f, 10.332365f));
			add(new Shot(Mission.M_1_7, 0.7243433f, -0.3395977f, 0.6432329f, 1.0226294f, 2.970848f));
			add(new Shot(Mission.M_1_8, 0.79977417f, -0.019007444f, 3.1429143f, 0.5455391f, 2.4170005f));
			add(new Shot(Mission.M_1_8, 0.79993725f, 0.010046244f, 3.2139719f, 0.4910205f, 98.70442f));
			add(new Shot(Mission.M_1_8, 0.78987026f, -0.12690425f, 2.4582345f, 0.4292122f, 1.0781415f));
			add(new Shot(Mission.M_1_8, 0.78987026f, -0.12690425f, 2.5090344f, 0.3717897f, 1.6134088f));
			add(new Shot(Mission.M_1_8, 0.787096f, -0.14310837f, 1.4743165f, 0.2690907f, 7.461615f));
			add(new Shot(Mission.M_1_8, 0.77680683f, -0.19123435f, 1.1537855f, 0.5158317f, 17.539845f));
			add(new Shot(Mission.M_1_8, 0.7945204f, -0.09347296f, 2.0533493f, 0.5018749f, 79.97014f));
			add(new Shot(Mission.M_1_9, 0.6083784f, -0.5194951f, 2.2732055f, 3.0545905f, 36.53004f));
			add(new Shot(Mission.M_1_9, 0.6083784f, -0.5194951f, 1.2314186f, 0.05497782f, 41.029945f));
			add(new Shot(Mission.M_1_9, 0.3487587f, -0.71997714f, 0.52839696f, 0.49928576f, 34.046196f));
			add(new Shot(Mission.M_1_10, 0.79185295f, -0.11387873f, 3.8718717f, 2.5510123f, 7.0978155f));
			add(new Shot(Mission.M_1_10, 0.7676611f, -0.22516012f, 2.9060512f, 2.6456666f, 21.204346f));
			add(new Shot(Mission.M_1_10, 0.79327106f, -0.103542805f, 3.016837f, 1.7916381f, 87.01596f));
			add(new Shot(Mission.M_1_10, 0.7968464f, -0.07096815f, 2.6315963f, 1.2088596f, 2.1590998f));
			add(new Shot(Mission.M_1_10, 0.7968464f, -0.07096815f, 2.6772623f, 1.0743463f, 2.8377001f));
			add(new Shot(Mission.M_1_10, 0.7559767f, -0.26172304f, 0.9764065f, 1.5666251f, 9.945965f));
			add(new Shot(Mission.M_1_10, 0.79596233f, -0.08027983f, 2.3045542f, 0.98969966f, 9.130238f));
			add(new Shot(Mission.M_1_10, 0.3819723f, -0.70292044f, 1.9356549f, 0.518985f, 7.3315268f));
			add(new Shot(Mission.M_1_10, 0.19062519f, -0.54999995f, 5.2929573f, 1.0813801f, 4.2537513f));
			add(new Shot(Mission.M_1_10, 0.19062519f, -0.54999995f, 5.2617464f, 1.0869638f, 3.5635252f));
			add(new Shot(Mission.M_1_10, 0.19062519f, -0.54999995f, 5.2778635f, 1.0926447f, 1.0272254f));
			add(new Shot(Mission.M_1_10, 0.30312443f, -0.54999995f, 4.5663896f, 1.2592351f, 21.54787f));
			add(new Shot(Mission.M_1_10, 0.30312443f, -0.54999995f, 4.8567924f, 0.88881594f, 1.4344339f));
			add(new Shot(Mission.M_1_10, 0.7994461f, -0.02976656f, 1.9389994f, 0.5006195f, 82.0613f));
			add(new Shot(Mission.M_1_10, 0.7994461f, -0.02976656f, 1.8353729f, 0.48271573f, 39.747093f));
			add(new Shot(Mission.M_1_10, 0.23750019f, -0.54062533f, 5.6039615f, 0.8503962f, 9.098359f));
			add(new Shot(Mission.M_1_10, 0.54687405f, -0.109375f, 4.1421013f, 0.66993773f, 3.1717722f));
			add(new Shot(Mission.M_1_10, 0.5281248f, -0.22187519f, 3.7205403f, 1.0709084f, 64.071556f));
			add(new Shot(Mission.M_1_10, 0.5281248f, -0.22187519f, 3.058829f, 0.5305842f, 19.041687f));
			add(new Shot(Mission.M_1_10, 0.2937498f, -0.54062533f, 4.541158f, 0.44654512f, 15.216998f));
			add(new Shot(Mission.M_1_10, 0.7838955f, -0.15971279f, 0.8742444f, 0.5482128f, 98.32469f));
			
			add(new Shot(Mission.M_1_11, 0.61502314f, -0.51161194f, 2.1019611f, 4.1647277f, 36.225758f));
			add(new Shot(Mission.M_1_11, 0.61502314f, -0.51161194f, 0.559641f, 3.636055f, 6.223952f));
			add(new Shot(Mission.M_1_11, 0.38002253f, -0.7039764f, 0.47817236f, 3.6050236f, 34.638016f));
			add(new Shot(Mission.M_1_11, 0.7901988f, -0.12484288f, 0.5893678f, 1.4355806f, 1.3491069f));
			add(new Shot(Mission.M_1_11, 0.78797436f, -0.13818955f, 0.59312826f, 1.4390209f, 2.0240557f));
			add(new Shot(Mission.M_1_11, 0.7869053f, -0.14415288f, 0.5882887f, 1.439563f, 2.0318713f));
			add(new Shot(Mission.M_1_11, 0.7889571f, -0.13246322f, 1.0095623f, 1.3700974f, 66.38701f));
			add(new Shot(Mission.M_1_11, 0.7889571f, -0.13246322f, 0.57104754f, 1.4410106f, 66.38701f));

			add(new Shot(Mission.M_1_12, 0.34032822f, -0.72400045f, 1.1813155f, 4.0391135f, 11.037836f));
			add(new Shot(Mission.M_1_12, 0.21554518f, -0.7704158f, 2.2810433f, 3.6221592f, 12.145056f));
			add(new Shot(Mission.M_1_12, 0.79614735f, -0.078421354f, 1.24035f, 1.1660893f, 52.44951f));
			add(new Shot(Mission.M_1_12, 0.46562433f, -0.14374995f, 2.5249076f, 0.87536615f, 10.457404f));
			add(new Shot(Mission.M_1_12, 0.71226215f, -0.36425567f, 2.6525528f, 3.3449001f, 9.637957f));
			add(new Shot(Mission.M_1_12, 0.71726894f, -0.35429597f, 1.3707391f, 3.1585464f, 4.119082f));
			add(new Shot(Mission.M_1_12, 0.7311678f, -0.32464433f, 1.5231078f, 3.1580648f, 22.76068f));
			add(new Shot(Mission.M_1_12, 0.7960372f, -0.07952857f, 1.4338161f, 1.0814649f, 7.065713f));
			add(new Shot(Mission.M_1_12, 0.7960372f, -0.07952857f, 1.4587036f, 0.99775f, 1.3438896f));
			add(new Shot(Mission.M_1_12, 0.79950047f, -0.028270721f, 1.3059632f, 1.0787325f, 49.314148f));
			add(new Shot(Mission.M_1_12, 0.35312462f, -0.27499986f, 2.7340043f, 0.43650615f, 35.90502f));

			add(new Shot(Mission.M_1_13, 0.3749504f, -0.706691f, 3.9988368f, 0.58516705f, 26.910816f));
			add(new Shot(Mission.M_1_13, 0.41453934f, -0.6842203f, 2.3633206f, 0.55281085f, 12.555073f));
			add(new Shot(Mission.M_1_13, 0.41453934f, -0.6842203f, 2.14894f, 0.40111494f, 1.4211547f));
			add(new Shot(Mission.M_1_13, 0.37437916f, -0.70699406f, 2.145332f, 0.39241707f, 5.670599f));
			add(new Shot(Mission.M_1_13, 0.41119576f, -0.6862347f, 2.2432573f, 0.15617736f, 60.43402f));
			add(new Shot(Mission.M_1_13, 0.42108345f, -0.68021226f, 1.766558f, 0.08979733f, 5.533885f));
			add(new Shot(Mission.M_1_13, 0.43810654f, -0.66937494f, 1.7420416f, 0.080733106f, 6.2519646f));
			
			add(new Shot(Mission.M_1_14, 0.68393993f, -0.4150014f, 2.8745172f, 4.9619713f, 26.017458f));
			add(new Shot(Mission.M_1_14, 0.78325844f, -0.16280508f, 2.2792573f, 3.0976617f, 20.341692f));
			add(new Shot(Mission.M_1_14, 0.7643852f, -0.23604012f, 1.5471926f, 3.0968354f, 9.474553f));
			add(new Shot(Mission.M_1_14, 0.7819834f, -0.16882563f, 2.4612129f, 3.0693288f, 23.93781f));
			add(new Shot(Mission.M_1_14, 0.7819834f, -0.16882563f, 1.5497307f, 3.0549686f, 2.1698565f));
			add(new Shot(Mission.M_1_14, 0.38124943f, -0.4250002f, 2.1134055f, 0.056158826f, 2.699488f));
			add(new Shot(Mission.M_1_14, 0.34374905f, -0.4343748f, 2.1522245f, 0.05928345f, 1.5558046f));
			add(new Shot(Mission.M_1_14, 0.4093752f, -0.39687538f, 2.1231434f, 0.054907814f, 3.9963624f));
			
			add(new Shot(Mission.M_1_14, 0.28593254f, -0.74715614f, 3.0600204f, 0.3352782f, 1.4229473f));
			add(new Shot(Mission.M_1_14, 0.28593254f, -0.74715614f, 3.0712101f, 0.34926486f, 3.5147576f));
			add(new Shot(Mission.M_1_14, 0.28593254f, -0.74715614f, 3.0927317f, 0.18454131f, 2.1319287f));
			add(new Shot(Mission.M_1_14, 0.28593254f, -0.74715614f, 3.0927317f, 0.18454131f, 1.8421788f));
			add(new Shot(Mission.M_1_14, 0.28593254f, -0.74715614f, 2.995223f, 0.15685964f, 5.3848767f));

			add(new Shot(Mission.M_1_14, 0.79974174f, 0.020319939f, 1.4833646f, 1.7933257f, 1.7300074f));
			add(new Shot(Mission.M_1_14, 0.79974174f, 0.020319939f, 1.4726307f, 1.7990932f, 1.1438392f));
			add(new Shot(Mission.M_1_14, 0.79974174f, 0.020319939f, 1.6502105f, 1.5456045f, 2.6640677f));
			add(new Shot(Mission.M_1_14, 0.7994776f, 0.028916836f, 2.1520379f, 1.609506f, 19.482641f));
			add(new Shot(Mission.M_1_14, 0.7994776f, 0.028916836f, 1.6552823f, 1.5549997f, 19.482641f));

			add(new Shot(Mission.M_1_15, 0.6534977f, -0.46145487f, 1.8517284f, 3.0188062f, 58.500565f));
			add(new Shot(Mission.M_1_15, 0.78970623f, 0.12791967f, 4.574028f, 0.41466022f, 102.92378f));
			add(new Shot(Mission.M_1_15, 0.79993343f, 0.010361671f, 3.2760198f, 0.31713986f, 40.861153f));
			add(new Shot(Mission.M_1_15, 0.77274513f, -0.20703745f, 1.3426446f, 0.5392373f, 2.929116f));
			add(new Shot(Mission.M_1_15, 0.79456806f, -0.09307003f, 1.7752342f, 0.059649423f, 12.660957f));

			add(new Shot(Mission.M_1_16, 0.43391418f, -0.6721003f, 3.7247064f, 4.9419765f, 13.618053f));
			add(new Shot(Mission.M_1_16, 0.7893524f, -0.1300888f, 4.1903133f, 2.0458472f, 40.44603f));
			add(new Shot(Mission.M_1_16, 0.77100563f, -0.21342707f, 1.7812865f, 1.9224378f, 16.954039f));
			
			add(new Shot(Mission.M_1_17, 0.5752907f, -0.55591416f, 1.0166734f, 3.8302104f, 16.761484f));
			add(new Shot(Mission.M_1_17, 0.58841324f, -0.5420058f, 1.0637699f, 4.4464974f, 5.202486f));
			add(new Shot(Mission.M_1_17, 0.58841324f, -0.5420058f, 1.4480648f, 4.1049976f, 12.355836f));
			add(new Shot(Mission.M_1_17, 0.58841324f, -0.5420058f, 1.0687728f, 4.3311286f, 12.355836f));

			add(new Shot(Mission.M_1_17, 0.79958725f, -0.025689363f, 2.479181f, 0.7235719f, 12.71202f));
			add(new Shot(Mission.M_1_17, 0.79958725f, -0.025689363f, 2.1385694f, 2.5874872f, 12.71202f));
			add(new Shot(Mission.M_1_17, 0.7986479f, -0.01649332f, 2.5556078f, 0.61443573f, 13.632548f));
			
			add(new Shot(Mission.M_1_18, 0.34683418f, -0.7209065f, 2.2600636f, 0.48143458f, 38.19589f));
			add(new Shot(Mission.M_1_18, 0.34683418f, -0.7209065f, 2.3937502f, 0.054999948f, 38.19589f));
			add(new Shot(Mission.M_1_18, 0.30432415f, -0.73985577f, 3.939432f, 1.9288182f, 69.321846f));
			add(new Shot(Mission.M_1_18, 0.30432415f, -0.73985577f, 3.8469133f, 1.4407814f, 69.321846f));
			add(new Shot(Mission.M_1_18, 0.7605591f, -0.2480917f, 1.6742465f, 1.6596237f, 6.9939404f));
			add(new Shot(Mission.M_1_18, 0.7605591f, -0.2480917f, 2.672437f, 0.4914326f, 16.008932f));
			add(new Shot(Mission.M_1_18, 0.7605591f, -0.2480917f, 2.3937502f, 0.054999948f, 16.008932f));
			add(new Shot(Mission.M_1_18, 0.48499775f, -0.6362214f, 2.6945844f, 0.41992062f, 20.234732f));
			add(new Shot(Mission.M_1_18, 0.48499775f, -0.6362214f, 2.3937502f, 0.054999948f, 20.234732f));
			add(new Shot(Mission.M_1_18, 0.44302464f, -0.66613007f, 2.023056f, 2.454188f, 62.754673f));
			add(new Shot(Mission.M_1_18, 0.44302464f, -0.66613007f, 1.6741832f, 2.2181861f, 62.754673f));
			add(new Shot(Mission.M_1_18, 0.79985046f, -0.015466452f, 4.0888886f, 1.1556637f, 3.6009245f));
			add(new Shot(Mission.M_1_18, 0.79985046f, -0.015466452f, 3.7214196f, 1.418392f, 3.6009245f));
			add(new Shot(Mission.M_1_18, 0.79985046f, -0.015466452f, 3.9313476f, 1.1543366f, 1.0366205f));
			add(new Shot(Mission.M_1_18, 0.79985046f, -0.015466452f, 3.469627f, 1.2856104f, 1.0366205f));
			add(new Shot(Mission.M_1_18, 0.79985046f, -0.015466452f, 3.3541024f, 1.1572634f, 1.3616502f));
			add(new Shot(Mission.M_1_18, 0.79985046f, -0.015466452f, 3.0215683f, 0.7832608f, 7.3445945f));

			
			// episode 2
			
			add(new Shot(Mission.M_2_1, 0.76955986f, -0.21858072f, 2.959427f, 1.0283555f, 33.53893f));
			add(new Shot(Mission.M_2_1, 0.76955986f, -0.21858072f, 2.73849f, 0.5950147f, 33.53893f));
			add(new Shot(Mission.M_2_1, 0.73134613f, -0.32424235f, 1.8607669f, 1.0021124f, 72.2779f));
			add(new Shot(Mission.M_2_1, 0.73134613f, -0.32424235f, 1.6827652f, 0.5955725f, 72.2779f));
			add(new Shot(Mission.M_2_1, 0.73134613f, -0.32424235f, 0.6389787f, 0.59517777f, 1.1696414f));
			add(new Shot(Mission.M_2_1, 0.5784006f, -0.55267787f, 0.66832674f, 1.1099834f, 7.384871f));
			add(new Shot(Mission.M_2_1, 0.5784006f, -0.55267787f, 0.59828526f, 0.595318f, 7.384871f));
			add(new Shot(Mission.M_2_1, 0.5175638f, -0.61002254f, 0.9564505f, 0.9717682f, 1.09806f));
			add(new Shot(Mission.M_2_1, 0.5175638f, -0.61002254f, 0.5965998f, 0.59535915f, 1.09806f));

			add(new Shot(Mission.M_2_2, 0.73729134f, -0.31048608f, 1.7859832f, 1.084029f, 4.320441f));
			add(new Shot(Mission.M_2_2, 0.73729134f, -0.31048608f, 1.6954901f, 0.59575355f, 4.320441f));
			add(new Shot(Mission.M_2_2, 0.7756605f, -0.19583249f, 2.6862252f, 1.082069f, 9.348601f));
			add(new Shot(Mission.M_2_2, 0.7756605f, -0.19583249f, 2.7374635f, 0.5950625f, 9.348601f));
			add(new Shot(Mission.M_2_2, 0.7756605f, -0.19583249f, 1.8063529f, 1.0811855f, 2.6426866f));
			add(new Shot(Mission.M_2_2, 0.7756605f, -0.19583249f, 1.6872478f, 0.59568506f, 2.6426866f));
			add(new Shot(Mission.M_2_2, 0.7522125f, -0.27235222f, 2.0349503f, 0.91877586f, 18.84822f));
			add(new Shot(Mission.M_2_2, 0.7522125f, -0.27235222f, 1.6883918f, 0.595614f, 18.84822f));
			add(new Shot(Mission.M_2_2, 0.7728052f, -0.206815f, 2.9729817f, 1.0149924f, 30.385574f));
			add(new Shot(Mission.M_2_2, 0.7728052f, -0.206815f, 2.7367277f, 0.5950633f, 30.385574f));
			add(new Shot(Mission.M_2_2, 0.7108526f, -0.3669994f, 1.0722005f, 0.69670004f, 9.720369f));
			add(new Shot(Mission.M_2_2, 0.7108526f, -0.3669994f, 0.5764724f, 0.59559417f, 9.720369f));

			add(new Shot(Mission.M_2_3, 0.7710285f, -0.21334267f, 4.463528f, 1.7287991f, 68.42511f));
			add(new Shot(Mission.M_2_3, 0.7710285f, -0.21334267f, 4.0884585f, 1.3744006f, 68.42511f));
			add(new Shot(Mission.M_2_3, 0.7710285f, -0.21334267f, 3.0182958f, 0.95676833f, 19.777222f));
			add(new Shot(Mission.M_2_3, 0.7710285f, -0.21334267f, 2.5723991f, 0.8866807f, 19.777222f));
			add(new Shot(Mission.M_2_3, 0.79988575f, -0.01349473f, 7.3965034f, 1.8628204f, 73.974785f));
			add(new Shot(Mission.M_2_3, 0.79988575f, -0.01349473f, 7.4115744f, 1.3743997f, 73.974785f));
			add(new Shot(Mission.M_2_3, 0.79988575f, -0.01349473f, 4.585267f, 0.9527033f, 15.148841f));
			add(new Shot(Mission.M_2_3, 0.79988575f, -0.01349473f, 4.2440906f, 0.53051335f, 15.148841f));
			add(new Shot(Mission.M_2_3, 0.7552366f, -0.26385188f, 1.1101087f, 0.4105745f, 2.7870967f));
			add(new Shot(Mission.M_2_3, 0.74967f, -0.27927518f, 1.6479598f, 0.47988397f, 79.07391f));
			add(new Shot(Mission.M_2_3, 0.74967f, -0.27927518f, 1.7095263f, 0.058633924f, 79.07391f));
			add(new Shot(Mission.M_2_3, 0.5840702f, -0.5466826f, 0.7008814f, 1.1917554f, 6.4170537f));
			add(new Shot(Mission.M_2_3, 0.5840702f, -0.5466826f, 0.970696f, 0.87374425f, 6.4170537f));
			add(new Shot(Mission.M_2_3, 0.74832726f, -0.28285313f, 2.7587209f, 0.051765323f, 8.060161f));
			add(new Shot(Mission.M_2_3, 0.74832726f, -0.28285313f, 2.7587209f, 0.051765323f, 8.060161f));
			add(new Shot(Mission.M_2_3, 0.758214f, -0.25516868f, 4.193864f, 1.8850374f, 66.016716f));
			add(new Shot(Mission.M_2_3, 0.758214f, -0.25516868f, 4.088591f, 1.3776066f, 66.016716f));
			add(new Shot(Mission.M_2_3, 0.758214f, -0.25516868f, 0.59051716f, 0.51372695f, 2.4404094f));
			add(new Shot(Mission.M_2_3, 0.758214f, -0.25516868f, 0.5906384f, 0.30654085f, 2.8069594f));
			add(new Shot(Mission.M_2_3, 0.78807926f, -0.13759112f, 2.7379575f, 0.54233205f, 8.260936f));
			add(new Shot(Mission.M_2_3, 0.78807926f, -0.13759112f, 2.7154794f, 0.05958593f, 8.260936f));
			add(new Shot(Mission.M_2_3, 0.79265213f, -0.10817838f, 4.2317486f, 1.0096055f, 37.01789f));
			add(new Shot(Mission.M_2_3, 0.79265213f, -0.10817838f, 4.240876f, 0.52975625f, 37.01789f));
			add(new Shot(Mission.M_2_4, 0.74098015f, -0.3015771f, 3.0295446f, 1.6076988f, 66.60081f));
			add(new Shot(Mission.M_2_4, 0.74098015f, -0.3015771f, 2.7095165f, 1.3144267f, 66.60081f));
			add(new Shot(Mission.M_2_4, 0.74098015f, -0.3015771f, 1.4398615f, 1.3157979f, 2.164091f));
			add(new Shot(Mission.M_2_4, 0.74098015f, -0.3015771f, 0.94372964f, 1.3160549f, 2.164091f));
			add(new Shot(Mission.M_2_4, 0.7083101f, -0.37188196f, 1.2416857f, 0.97801083f, 13.180014f));
			add(new Shot(Mission.M_2_4, 0.7083101f, -0.37188196f, 0.921618f, 1.2660114f, 13.180014f));
			add(new Shot(Mission.M_2_4, 0.77048683f, -0.2152896f, 1.8407819f, 0.34077072f, 2.5335643f));
			add(new Shot(Mission.M_2_4, 0.74402237f, -0.2939918f, 1.8527629f, 0.7642007f, 59.531616f));
			add(new Shot(Mission.M_2_4, 0.74402237f, -0.2939918f, 1.8358543f, 0.34125197f, 59.531616f));
			add(new Shot(Mission.M_2_5, 0.7378397f, -0.30917954f, 4.6351194f, 0.5936408f, 1.9138353f));
			add(new Shot(Mission.M_2_5, 0.48945618f, -0.28470373f, 4.6699657f, 0.54292816f, 50.3387f));
			add(new Shot(Mission.M_2_5, 0.729249f, -0.32893133f, 2.0169368f, 0.07400563f, 14.930607f));
			add(new Shot(Mission.M_2_5, 0.729249f, -0.32893133f, 2.0169368f, 0.07400563f, 14.930607f));
			add(new Shot(Mission.M_2_5, 0.7797613f, -0.17880678f, 1.7784507f, 0.09920137f, 10.303542f));
			add(new Shot(Mission.M_2_5, 0.7797613f, -0.17880678f, 1.2793183f, 0.05499977f, 10.303542f));
			add(new Shot(Mission.M_2_5, 0.7797613f, -0.17880678f, 1.6298002f, 0.2064677f, 2.5768363f));
			add(new Shot(Mission.M_2_5, 0.7797613f, -0.17880678f, 1.2599887f, 0.46539515f, 2.5768363f));
			add(new Shot(Mission.M_2_5, 0.7797613f, -0.17880678f, 1.2599887f, 0.46539515f, 1.0161703f));
			add(new Shot(Mission.M_2_5, 0.7797613f, -0.17880678f, 1.6622596f, 0.060396597f, 1.8782126f));
			add(new Shot(Mission.M_2_5, 0.7797613f, -0.17880678f, 1.2876531f, 0.3147983f, 1.8782126f));
			add(new Shot(Mission.M_2_5, 0.7797613f, -0.17880678f, 1.2689687f, 0.25455654f, 2.456659f));
			add(new Shot(Mission.M_2_5, 0.7439375f, -0.2942059f, 1.4832778f, 0.4702928f, 78.76203f));
			add(new Shot(Mission.M_2_5, 0.7439375f, -0.2942059f, 1.2774825f, 0.055551857f, 78.76203f));

			// high shots
			add(new Shot(Mission.M_2_6, 0.41080856f, -0.68646646f, 8.015268f, 0.52890027f, 11.187565f));
			add(new Shot(Mission.M_2_6, 0.41080856f, -0.68646646f, 7.859375f, 0.054999948f, 11.187565f));
			add(new Shot(Mission.M_2_6, 0.36842823f, -0.7101128f, 8.697418f, 0.51642287f, 3.1032076f));
			add(new Shot(Mission.M_2_6, 0.36842823f, -0.7101128f, 8.796875f, 0.054999948f, 3.1032076f));
			add(new Shot(Mission.M_2_6, 0.41245937f, -0.6854763f, 3.811433f, 0.5135406f, 10.483822f));
			add(new Shot(Mission.M_2_6, 0.41245937f, -0.6854763f, 3.640625f, 0.054999948f, 10.483822f));
			add(new Shot(Mission.M_2_6, 0.41245937f, -0.6854763f, 1.7139531f, 0.059651613f, 1.6900561f));
			add(new Shot(Mission.M_2_6, 0.41245937f, -0.6854763f, 1.752882f, 0.05499327f, 1.8058726f));
			add(new Shot(Mission.M_2_6, 0.3483553f, -0.72017264f, 2.9088666f, 0.48438537f, 14.208538f));
			add(new Shot(Mission.M_2_6, 0.3483553f, -0.72017264f, 2.759849f, 0.054999948f, 14.208538f));
			add(new Shot(Mission.M_2_6, 0.40455914f, -0.69016814f, 2.0595105f, 0.35984576f, 74.7936f));
			add(new Shot(Mission.M_2_6, 0.40455914f, -0.69016814f, 1.726242f, 0.054999948f, 74.7936f));
		
			// low shots
			add(new Shot(Mission.M_2_6, 0.75249386f, -0.27157593f, 7.8362427f, 0.48274142f, 17.46623f));
			add(new Shot(Mission.M_2_6, 0.75249386f, -0.27157593f, 7.859375f, 0.054999948f, 17.46623f));
			add(new Shot(Mission.M_2_6, 0.75249386f, -0.27157593f, 8.796875f, 0.054999948f, 7.1575065f));
			add(new Shot(Mission.M_2_6, 0.75249386f, -0.27157593f, 8.788332f, 0.054999948f, 3.8705091f));
			add(new Shot(Mission.M_2_6, 0.75249386f, -0.27157593f, 9.804572f, 0.05948016f, 1.1958208f));
			add(new Shot(Mission.M_2_6, 0.77812576f, -0.18579793f, 3.5830312f, 0.5207483f, 6.875959f));
			add(new Shot(Mission.M_2_6, 0.77812576f, -0.18579793f, 3.640625f, 0.054999948f, 6.875959f));
			add(new Shot(Mission.M_2_6, 0.7866087f, -0.14576316f, 2.9498806f, 0.4655118f, 15.13888f));
			add(new Shot(Mission.M_2_6, 0.7866087f, -0.14576316f, 2.703125f, 0.054999948f, 15.13888f));
			add(new Shot(Mission.M_2_6, 0.7866087f, -0.14576316f, 3.6982927f, 0.054999948f, 5.0198646f));
			add(new Shot(Mission.M_2_6, 0.7667036f, -0.22839928f, 2.2052624f, 0.29035574f, 8.183056f));
			add(new Shot(Mission.M_2_6, 0.7667036f, -0.22839928f, 1.7281246f, 0.059642434f, 8.183056f));
			add(new Shot(Mission.M_2_6, 0.7787638f, -0.1831057f, 2.1823623f, 0.04633467f, 19.517334f));
			add(new Shot(Mission.M_2_6, 0.7787638f, -0.1831057f, 1.690644f, 0.054999948f, 19.517334f));
			
			add(new Shot(Mission.M_2_7, 0.7872782f, -0.14210415f, 4.027368f, 2.481229f, 72.94402f));
			add(new Shot(Mission.M_2_7, 0.7872782f, -0.14210415f, 3.6376808f, 2.3713226f, 72.94402f));
			add(new Shot(Mission.M_2_7, 0.7982025f, -0.05359626f, 4.0018735f, 1.9245279f, 7.868862f));
			add(new Shot(Mission.M_2_7, 0.7982025f, -0.05359626f, 3.655101f, 1.5667874f, 7.868862f));
			add(new Shot(Mission.M_2_7, 0.7982025f, -0.05359626f, 5.05582f, 1.190386f, 2.5175061f));
			add(new Shot(Mission.M_2_7, 0.7982025f, -0.05359626f, 5.1756845f, 0.7424505f, 2.5175061f));
			add(new Shot(Mission.M_2_7, 0.79668045f, 0.07280159f, 4.1033072f, 1.3033031f, 5.408639f));
			add(new Shot(Mission.M_2_7, 0.79668045f, 0.07280159f, 3.647508f, 1.4457568f, 5.408639f));
			add(new Shot(Mission.M_2_7, 0.79553795f, 0.08437514f, 4.050988f, 1.0684114f, 48.961014f));
			add(new Shot(Mission.M_2_7, 0.79553795f, 0.08437514f, 3.7167387f, 0.7414068f, 48.961014f));
			add(new Shot(Mission.M_2_7, 0.38437462f, -0.13750005f, 5.600375f, 0.9541827f, 45.507782f));
			add(new Shot(Mission.M_2_7, 0.38437462f, -0.13750005f, 5.209394f, 0.74239385f, 45.507782f));
			add(new Shot(Mission.M_2_7, 0.7985344f, -0.04839611f, 2.7671244f, 1.0491282f, 10.323119f));
			add(new Shot(Mission.M_2_7, 0.7985344f, -0.04839611f, 2.3582194f, 0.7412217f, 10.323119f));
			add(new Shot(Mission.M_2_7, 0.7985344f, -0.04839611f, 1.9513273f, 0.55451566f, 2.735863f));
			add(new Shot(Mission.M_2_7, 0.7985344f, -0.04839611f, 1.8406248f, 0.059061944f, 2.735863f));
			add(new Shot(Mission.M_2_7, 0.7985344f, -0.04839611f, 1.5981809f, 0.45437968f, 1.1775459f));
			add(new Shot(Mission.M_2_7, 0.7985344f, -0.04839611f, 1.836453f, 0.059061944f, 1.1775459f));
			add(new Shot(Mission.M_2_7, 0.7985344f, -0.04839611f, 1.5552945f, 0.41370314f, 1.5739541f));
			add(new Shot(Mission.M_2_7, 0.7985344f, -0.04839611f, 1.8357701f, 0.059061944f, 1.5739541f));
			add(new Shot(Mission.M_2_7, 0.798831f, -0.0432415f, 1.9503412f, 0.49386936f, 58.834145f));
			add(new Shot(Mission.M_2_7, 0.798831f, -0.0432415f, 1.8335636f, 0.059061944f, 58.834145f));
			
			add(new Shot(Mission.M_2_8, 0.3209529f, -0.7327956f, 1.2987515f, 2.6105936f, 36.067627f));
			add(new Shot(Mission.M_2_8, 0.3209529f, -0.7327956f, 1.3343754f, 2.1784885f, 36.067627f));
			add(new Shot(Mission.M_2_8, 0.2653675f, -0.7547052f, 2.016709f, 1.6353656f, 9.90425f));
			add(new Shot(Mission.M_2_8, 0.2653675f, -0.7547052f, 2.0f, 1.1854502f, 9.90425f));
			add(new Shot(Mission.M_2_8, 0.27581215f, -0.7509512f, 2.010451f, 1.6302868f, 28.461231f));
			add(new Shot(Mission.M_2_8, 0.27581215f, -0.7509512f, 1.9841044f, 1.1854494f, 28.461231f));
			add(new Shot(Mission.M_2_8, 0.23772144f, -0.7638643f, 2.7617447f, 2.6576393f, 47.79635f));
			add(new Shot(Mission.M_2_8, 0.23772144f, -0.7638643f, 2.7124999f, 2.18545f, 47.79635f));
			add(new Shot(Mission.M_2_8, 0.7264824f, -0.3349967f, 1.6100115f, 2.572815f, 14.867577f));
			add(new Shot(Mission.M_2_8, 0.7264824f, -0.3349967f, 1.3343754f, 2.1784885f, 14.867577f));
			add(new Shot(Mission.M_2_8, 0.74090576f, -0.30175972f, 2.7085128f, 2.6677105f, 56.50676f));
			add(new Shot(Mission.M_2_8, 0.74090576f, -0.30175972f, 2.7124999f, 2.18545f, 56.50676f));
			add(new Shot(Mission.M_2_8, 0.2627635f, -0.75561583f, 2.1464279f, 1.6361883f, 9.169114f));
			add(new Shot(Mission.M_2_8, 0.2627635f, -0.75561583f, 2.0f, 1.1854502f, 9.169114f));
			add(new Shot(Mission.M_2_8, 0.2846732f, -0.74763715f, 2.083602f, 1.6603206f, 9.029316f));
			add(new Shot(Mission.M_2_8, 0.2846732f, -0.74763715f, 1.9966092f, 1.1854502f, 9.029316f));

			add(new Shot(Mission.M_2_9, 0.7983446f, -0.0514369f, 5.356827f, 1.2238214f, 12.383312f));
			add(new Shot(Mission.M_2_9, 0.7983446f, -0.0514369f, 5.1694794f, 0.75112355f, 12.383312f));
			add(new Shot(Mission.M_2_9, 0.7947979f, -0.09108186f, 4.508191f, 1.0075352f, 1.3650105f));
			add(new Shot(Mission.M_2_9, 0.7947979f, -0.09108186f, 4.0702667f, 0.74319804f, 1.3650105f));
			add(new Shot(Mission.M_2_9, 0.78748894f, -0.14093208f, 4.350569f, 1.1394347f, 29.739397f));
			add(new Shot(Mission.M_2_9, 0.78748894f, -0.14093208f, 4.0589724f, 0.7420152f, 29.739397f));
			add(new Shot(Mission.M_2_9, 0.78748894f, -0.14093208f, 3.008271f, 0.7473635f, 1.1664591f));
			add(new Shot(Mission.M_2_9, 0.79300404f, -0.10556626f, 2.977853f, 0.74697983f, 4.2845187f));
			add(new Shot(Mission.M_2_9, 0.79300404f, -0.10556626f, 3.0370169f, 0.7440756f, 1.2578093f));
			add(new Shot(Mission.M_2_9, 0.7717314f, -0.21078634f, 3.4233348f, 1.1276449f, 16.611418f));
			add(new Shot(Mission.M_2_9, 0.7717314f, -0.21078634f, 3.1516337f, 0.7430328f, 16.611418f));
			add(new Shot(Mission.M_2_9, 0.74464035f, -0.292423f, 2.2396045f, 1.038432f, 34.758347f));
			add(new Shot(Mission.M_2_9, 0.74464035f, -0.292423f, 1.8947058f, 0.7478206f, 34.758347f));
			
			add(new Shot(Mission.M_2_10, 0.3419037f, -0.72325796f, 4.270017f, 1.6466818f, 42.83553f));
			add(new Shot(Mission.M_2_10, 0.3419037f, -0.72325796f, 4.2383614f, 1.1532248f, 42.83553f));
			add(new Shot(Mission.M_2_10, 0.3496275f, -0.71955585f, 3.4012563f, 1.1661566f, 4.0292964f));
			add(new Shot(Mission.M_2_10, 0.3496275f, -0.71955585f, 3.3958817f, 1.6587601f, 4.0292964f));
			add(new Shot(Mission.M_2_10, 0.4620819f, -0.6530543f, 3.4102495f, 2.131601f, 37.171898f));
			add(new Shot(Mission.M_2_10, 0.4620819f, -0.6530543f, 3.3790975f, 1.6602907f, 37.171898f));
			add(new Shot(Mission.M_2_10, 0.34448147f, -0.72203356f, 2.6828337f, 0.33340794f, 3.6328444f));
			add(new Shot(Mission.M_2_10, 0.34448147f, -0.72203356f, 3.2276213f, 1.0414816f, 1.8641503f));
			add(new Shot(Mission.M_2_10, 0.34448147f, -0.72203356f, 3.2276213f, 1.0414816f, 1.9491538f));
			add(new Shot(Mission.M_2_10, 0.3932972f, -0.69664735f, 3.5202987f, 1.4673333f, 44.913292f));
			add(new Shot(Mission.M_2_10, 0.3932972f, -0.69664735f, 3.2588286f, 1.0243194f, 44.913292f));
			add(new Shot(Mission.M_2_10, 0.3932972f, -0.69664735f, 2.6748776f, 0.3335436f, 3.2881746f));
			add(new Shot(Mission.M_2_10, 0.43390846f, -0.6721037f, 2.670047f, 0.7703382f, 24.010824f));
			add(new Shot(Mission.M_2_10, 0.43390846f, -0.6721037f, 2.5522985f, 0.33250445f, 24.010824f));
			add(new Shot(Mission.M_2_10, 0.3450575f, -0.7217586f, 3.7596946f, 0.7542778f, 21.07695f));
			add(new Shot(Mission.M_2_10, 0.3450575f, -0.7217586f, 3.4827077f, 0.33445442f, 21.07695f));
			
			add(new Shot(Mission.M_2_11, 0.79988766f, 0.013381004f, 4.5083494f, 0.6996138f, 26.932434f));
			add(new Shot(Mission.M_2_11, 0.79988766f, 0.013381004f, 4.256855f, 0.33981496f, 26.932434f));
			add(new Shot(Mission.M_2_11, 0.7931156f, -0.104724646f, 4.3412094f, 1.4674662f, 80.96642f));
			add(new Shot(Mission.M_2_11, 0.7931156f, -0.104724646f, 3.8463912f, 1.189918f, 80.96642f));
			add(new Shot(Mission.M_2_11, 0.7811661f, -0.17256975f, 3.6755602f, 1.5630162f, 77.00752f));
			add(new Shot(Mission.M_2_11, 0.7811661f, -0.17256975f, 3.354109f, 1.1968586f, 77.00752f));
			add(new Shot(Mission.M_2_11, 0.7811661f, -0.17256975f, 2.193569f, 0.8216656f, 4.9681597f));
			add(new Shot(Mission.M_2_11, 0.7811661f, -0.17256975f, 2.067424f, 0.33979297f, 4.9681597f));
			add(new Shot(Mission.M_2_11, 0.7883024f, -0.13630605f, 2.776498f, 0.8166513f, 46.03164f));
			add(new Shot(Mission.M_2_11, 0.7883024f, -0.13630605f, 2.8305001f, 0.34416008f, 46.03164f));
			add(new Shot(Mission.M_2_11, 0.7883024f, -0.13630605f, 2.3996198f, 0.67017895f, 24.73227f));
			add(new Shot(Mission.M_2_11, 0.7883024f, -0.13630605f, 2.0551233f, 0.33782804f, 24.73227f));
			add(new Shot(Mission.M_2_11, 0.7950449f, -0.08890605f, 3.5560892f, 0.84282213f, 86.94502f));
			add(new Shot(Mission.M_2_11, 0.7950449f, -0.08890605f, 3.55172f, 0.34751338f, 86.94502f));
			
			add(new Shot(Mission.M_2_12, 0.75386524f, -0.2677455f, 4.886171f, 2.2085838f, 52.00483f));
			add(new Shot(Mission.M_2_12, 0.75386524f, -0.2677455f, 4.5729685f, 1.6997819f, 52.00483f));
			add(new Shot(Mission.M_2_12, 0.75386524f, -0.2677455f, 2.3854706f, 1.508034f, 31.771011f));
			add(new Shot(Mission.M_2_12, 0.75386524f, -0.2677455f, 2.0450451f, 1.1441952f, 31.771011f));
			add(new Shot(Mission.M_2_12, 0.77181053f, -0.21049404f, 3.7325737f, 1.3710622f, 51.89777f));
			add(new Shot(Mission.M_2_12, 0.77181053f, -0.21049404f, 3.3189847f, 1.4152491f, 51.89777f));
			add(new Shot(Mission.M_2_12, 0.77181053f, -0.21049404f, 3.5146008f, 1.120966f, 7.1484637f));
			add(new Shot(Mission.M_2_12, 0.7055187f, -0.37715244f, 0.5009566f, 0.6037453f, 10.301839f));

			add(new Shot(Mission.M_2_13, 0.7998924f, 0.0131344795f, 1.8736267f, 1.8247631f, 1.2399808f));
			add(new Shot(Mission.M_2_13, 0.7998924f, 0.0131344795f, 1.8736267f, 1.8247631f, 1.9942267f));
			add(new Shot(Mission.M_2_13, 0.7908764f, 0.12047386f, 1.8097904f, 0.7380207f, 3.635431f));
			add(new Shot(Mission.M_2_13, 0.7953119f, 0.08647776f, 1.851062f, 0.05590056f, 6.5223255f));
			add(new Shot(Mission.M_2_13, 0.7953119f, 0.08647776f, 1.8093705f, 0.42270613f, 6.5223255f));
			
			add(new Shot(Mission.M_2_14, 0.2987919f, -0.7421076f, 3.1126556f, 1.9687679f, 4.2096553f));
			add(new Shot(Mission.M_2_14, 0.2987919f, -0.7421076f, 3.1141477f, 1.9690642f, 1.2438511f));
			add(new Shot(Mission.M_2_14, 0.2987919f, -0.7421076f, 3.1103327f, 1.9691794f, 1.7260823f));
			add(new Shot(Mission.M_2_14, 0.39691162f, -0.6945946f, 3.0468898f, 2.4277925f, 5.1886597f));
			add(new Shot(Mission.M_2_14, 0.39691162f, -0.6945946f, 3.110789f, 1.9680147f, 5.1886597f));
			add(new Shot(Mission.M_2_14, 0.3609352f, -0.7139509f, 2.230395f, 1.7817703f, 2.4706821f));
			add(new Shot(Mission.M_2_14, 0.3609352f, -0.7139509f, 2.0630255f, 1.2823446f, 2.4706821f));
			add(new Shot(Mission.M_2_14, 0.36705685f, -0.71082306f, 2.8537683f, 1.6284026f, 11.068379f));
			add(new Shot(Mission.M_2_14, 0.36705685f, -0.71082306f, 3.1167648f, 1.2824029f, 11.068379f));
			add(new Shot(Mission.M_2_14, 0.36705685f, -0.71082306f, 2.564831f, 1.2647675f, 3.5747485f));
			add(new Shot(Mission.M_2_14, 0.36705685f, -0.71082306f, 2.064953f, 1.2815436f, 3.5747485f));
			add(new Shot(Mission.M_2_14, 0.4032402f, -0.6909394f, 2.3474002f, 1.6741406f, 61.39624f));
			add(new Shot(Mission.M_2_14, 0.4032402f, -0.6909394f, 2.0149157f, 1.2829528f, 61.39624f));
			add(new Shot(Mission.M_2_14, 0.4032402f, -0.6909394f, 2.2716556f, 1.0697739f, 5.5371146f));
			add(new Shot(Mission.M_2_14, 0.4032402f, -0.6909394f, 2.0471265f, 0.5955241f, 30.620508f));
			add(new Shot(Mission.M_2_14, 0.4032402f, -0.6909394f, 4.217749f, 0.59528244f, 5.5300994f));
			add(new Shot(Mission.M_2_14, 0.4032402f, -0.6909394f, 3.1450734f, 0.5952578f, 21.644007f));
			add(new Shot(Mission.M_2_14, 0.4032402f, -0.6909394f, 4.289815f, 0.5960337f, 8.084572f));

			add(new Shot(Mission.M_2_15, 0.76049614f, -0.24828541f, 2.0935965f, 0.55754703f, 1.3626093f));
			add(new Shot(Mission.M_2_15, 0.76049614f, -0.24828541f, 1.6062498f, 0.55624986f, 1.3626093f));
			add(new Shot(Mission.M_2_15, 0.77503204f, -0.19830501f, 1.9555624f, 0.8215812f, 20.915545f));
			add(new Shot(Mission.M_2_15, 0.77503204f, -0.19830501f, 1.5871627f, 0.5563484f, 20.915545f));
			add(new Shot(Mission.M_2_15, 0.6814823f, -0.41902483f, 1.578125f, 1.7561738f, 2.645091f));
			add(new Shot(Mission.M_2_15, 0.67578125f, -0.428159f, 1.9013491f, 1.9029961f, 22.857883f));
			add(new Shot(Mission.M_2_15, 0.67578125f, -0.428159f, 1.47772f, 1.7558508f, 22.857883f));
			add(new Shot(Mission.M_2_15, 0.46616745f, -0.65014446f, 1.7017273f, 3.4392507f, 9.789977f));
			add(new Shot(Mission.M_2_15, 0.46616745f, -0.65014446f, 1.5844971f, 2.9561737f, 9.789977f));
			add(new Shot(Mission.M_2_15, 0.4289627f, -0.67527086f, 1.887492f, 3.3452656f, 22.667866f));
			add(new Shot(Mission.M_2_15, 0.4289627f, -0.67527086f, 1.5715545f, 2.9559503f, 22.667866f));
			
			add(new Shot(Mission.M_2_16, 0.5551472f, -0.57603073f, 7.1833444f, 2.8998299f, 32.159985f));
			add(new Shot(Mission.M_2_16, 0.5551472f, -0.57603073f, 4.283286f, 3.3658535f, 32.243748f));
			add(new Shot(Mission.M_2_16, 0.5551472f, -0.57603073f, 4.3163147f, 2.8991432f, 32.243748f));
			add(new Shot(Mission.M_2_16, 0.5551472f, -0.57603073f, 2.987934f, 2.3917727f, 29.007034f));
			add(new Shot(Mission.M_2_16, 0.5551472f, -0.57603073f, 2.5434434f, 1.9861717f, 29.007034f));
			add(new Shot(Mission.M_2_16, 0.60095596f, -0.528064f, 0.5673821f, 0.88543546f, 1.3661034f));
			add(new Shot(Mission.M_2_16, 0.60095596f, -0.528064f, 0.574975f, 1.9148998f, 3.986849f));
			add(new Shot(Mission.M_2_16, 0.60095596f, -0.528064f, 0.83483726f, 1.3144028f, 8.391495f));
			add(new Shot(Mission.M_2_16, 0.60095596f, -0.528064f, 0.5618938f, 0.88505626f, 8.391495f));
			add(new Shot(Mission.M_2_16, 0.60095596f, -0.528064f, 0.8984811f, 1.2478956f, 4.2282577f));
			add(new Shot(Mission.M_2_16, 0.60095596f, -0.528064f, 0.59501684f, 1.555028f, 4.2282577f));
			add(new Shot(Mission.M_2_16, 0.60095596f, -0.528064f, 0.5151809f, 0.89349127f, 4.74523f));
			add(new Shot(Mission.M_2_16, 0.60095596f, -0.528064f, 0.6176583f, 1.3726496f, 4.74523f));
			add(new Shot(Mission.M_2_16, 0.38673782f, -0.7003095f, 2.5522008f, 1.7766049f, 19.865562f));
			add(new Shot(Mission.M_2_16, 0.38673782f, -0.7003095f, 2.5621367f, 1.3202177f, 19.865562f));
			add(new Shot(Mission.M_2_16, 0.2329588f, -0.7653303f, 4.3300915f, 0.5908814f, 3.6037176f));
			add(new Shot(Mission.M_2_16, 0.2329588f, -0.7653303f, 4.3301167f, 0.5917402f, 5.98378f));
			add(new Shot(Mission.M_2_16, 0.2329588f, -0.7653303f, 3.512804f, 0.54268456f, 5.7128906f));
			add(new Shot(Mission.M_2_16, 0.2329588f, -0.7653303f, 4.2429852f, 0.5949914f, 4.830536f));
			add(new Shot(Mission.M_2_16, 0.2329588f, -0.7653303f, 3.510146f, 0.2278949f, 6.0540175f));
			
			add(new Shot(Mission.M_2_16, 0.3399992f, -0.7241552f, 4.2964973f, 2.5829482f, 3.3737268f));
			add(new Shot(Mission.M_2_16, 0.3399992f, -0.7241552f, 4.3191295f, 1.6968716f, 8.066028f));
			add(new Shot(Mission.M_2_16, 0.3399992f, -0.7241552f, 4.2954655f, 2.1625507f, 8.066028f));
			add(new Shot(Mission.M_2_16, 0.58305264f, -0.5477674f, 0.3820796f, 1.1505857f, 68.07524f));
			add(new Shot(Mission.M_2_16, 0.58305264f, -0.5477674f, 0.50760484f, 0.7439274f, 68.07524f));
			add(new Shot(Mission.M_2_16, 0.29149246f, -0.7450049f, 4.2882643f, 2.1410565f, 56.20188f));
			add(new Shot(Mission.M_2_16, 0.29149246f, -0.7450049f, 4.322974f, 1.7098299f, 56.20188f));
			add(new Shot(Mission.M_2_16, 0.2502699f, -0.7598455f, 4.7322598f, 1.0993857f, 28.054218f));
			add(new Shot(Mission.M_2_16, 0.2502699f, -0.7598455f, 4.3097415f, 0.88921034f, 28.054218f));
			add(new Shot(Mission.M_2_16, 0.28736687f, -0.7162123f, 4.20804f, 1.062253f, 65.53856f));
			add(new Shot(Mission.M_2_16, 0.28736687f, -0.7162123f, 4.24081f, 0.59577453f, 65.53856f));
			
			add(new Shot(Mission.M_2_17, 0.78446484f, -0.1568929f, 2.4962351f, 0.40037447f, 38.277424f));
			add(new Shot(Mission.M_2_17, 0.78446484f, -0.1568929f, 2.0466392f, 0.4950146f, 38.277424f));
			add(new Shot(Mission.M_2_17, 0.7160177f, -0.356817f, 2.5136707f, 1.5464978f, 51.79202f));
			add(new Shot(Mission.M_2_17, 0.7160177f, -0.356817f, 2.0468209f, 1.4911472f, 51.79202f));
			add(new Shot(Mission.M_2_17, 0.6239815f, -0.50064623f, 2.532074f, 2.4791803f, 37.45448f));
			add(new Shot(Mission.M_2_17, 0.6239815f, -0.50064623f, 2.0564198f, 2.480932f, 37.45448f));
			add(new Shot(Mission.M_2_17, 0.36989832f, -0.7093484f, 2.4764614f, 3.6556337f, 18.604778f));
			add(new Shot(Mission.M_2_17, 0.36989832f, -0.7093484f, 2.0656285f, 3.4711614f, 18.604778f));

			add(new Shot(Mission.M_2_18, 0.6485996f, -0.46831512f, 1.9944849f, 4.306442f, 43.679146f));
			add(new Shot(Mission.M_2_18, 0.6485996f, -0.46831512f, 1.5711569f, 4.1094284f, 43.679146f));
			add(new Shot(Mission.M_2_18, 0.30150414f, -0.7410095f, 3.0557036f, 1.4396914f, 21.209364f));
			add(new Shot(Mission.M_2_18, 0.30150414f, -0.7410095f, 2.6549697f, 1.178746f, 21.209364f));
			add(new Shot(Mission.M_2_18, 0.7782726f, 0.18518114f, 4.840625f, 0.054999948f, 2.6607401f));
			add(new Shot(Mission.M_2_18, 0.7791815f, 0.18131876f, 4.5272164f, 0.06735085f, 2.3522446f));
			add(new Shot(Mission.M_2_18, 0.7791815f, 0.18131876f, 4.9074855f, 0.054999948f, 2.3522446f));
			add(new Shot(Mission.M_2_18, 0.7824383f, 0.1667049f, 4.5350857f, 0.06599717f, 2.2354414f));
			add(new Shot(Mission.M_2_18, 0.7824383f, 0.1667049f, 4.9322557f, 0.05534026f, 2.2354414f));
			add(new Shot(Mission.M_2_18, 0.793581f, 0.101142645f, 4.5581f, 0.07219772f, 1.6218197f));
			add(new Shot(Mission.M_2_18, 0.793581f, 0.101142645f, 4.9426246f, 0.055354446f, 1.6218197f));
			add(new Shot(Mission.M_2_18, 0.75795937f, 0.255924f, 4.556279f, 0.05555691f, 2.7898602f));
			add(new Shot(Mission.M_2_18, 0.75795937f, 0.255924f, 4.9434943f, 0.055521697f, 2.7898602f));
			add(new Shot(Mission.M_2_18, -0.7702365f, 0.21618485f, 9.5875f, 3.1510606f, 1.8100549f));
			add(new Shot(Mission.M_2_18, -0.7702365f, 0.21618485f, 8.480362f, 1.3783319f, 22.717579f));
			add(new Shot(Mission.M_2_18, -0.7702365f, 0.21618485f, 8.845032f, 1.1791795f, 22.717579f));
			add(new Shot(Mission.M_2_18, 0.7813101f, 0.17191386f, 4.523084f, 0.3342343f, 20.45938f));
			add(new Shot(Mission.M_2_18, 0.7813101f, 0.17191386f, 4.840625f, 0.054999948f, 20.45938f));
			add(new Shot(Mission.M_2_18, 0.667325f, -0.44122195f, 1.5874996f, 4.109267f, 2.563268f));
			add(new Shot(Mission.M_2_18, 0.6443119f, -0.4741962f, 1.9048741f, 4.3425665f, 13.239559f));
			add(new Shot(Mission.M_2_18, 0.6443119f, -0.4741962f, 1.486018f, 4.107977f, 13.239559f));
			add(new Shot(Mission.M_2_18, 0.7846241f, -0.15609455f, 2.7078383f, 1.616173f, 15.764546f));
			add(new Shot(Mission.M_2_18, 0.7846241f, -0.15609455f, 2.655033f, 1.1712025f, 15.764546f));			
			//for first peng on bottom
			add(new Shot(Mission.M_2_18, 0.77439785f, 0.20076966f, 4.5011287f, 0.06192766f, 2.559462f));
			add(new Shot(Mission.M_2_18, 0.77439785f, 0.20076966f, 4.8900743f, 0.055321246f, 2.559462f));
			add(new Shot(Mission.M_2_18, 0.797081f, 0.06827855f, 4.532946f, 0.08144377f, 1.3980951f));
			add(new Shot(Mission.M_2_18, 0.797081f, 0.06827855f, 4.9135766f, 0.055335015f, 1.3980951f));
			add(new Shot(Mission.M_2_18, 0.76545143f, 0.23255897f, 4.542071f, 0.05951859f, 2.4742794f));
			add(new Shot(Mission.M_2_18, 0.76545143f, 0.23255897f, 4.9368787f, 0.055466294f, 2.4742794f));			
			// manually added on higher target
			add(new Shot(Mission.M_2_18, 0.7846241f, -0.15609455f, 1.8374996f, 4.359267f, 41.80834f));
			add(new Shot(Mission.M_2_18, 0.7846241f, -0.15609455f, 1.8374996f, 4.359267f, 41.80834f));
			
		}
	}
	
}
