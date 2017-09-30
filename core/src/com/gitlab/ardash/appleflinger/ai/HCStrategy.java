/*******************************************************************************
 * Copyright (C) 2017 Andreas Redmer <andreasredmer@mailchuck.com>
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
import java.util.TreeSet;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gitlab.ardash.appleflinger.AdvancedStage;
import com.gitlab.ardash.appleflinger.actors.TargetActor;
import com.gitlab.ardash.appleflinger.actors.TntActor;
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
	
	private static Vector2 defaultPullVector = new Vector2(0.7672596f,-0.22652423f);

	@Override
	public Vector2 getPullVector() 
	{
		Vector2 ret;

		// select target
		final List<Vector2> targets = getCurrentTragets();
		final int selectedTargetId = MathUtils.random(targets.size()-1);
		Vector2 target = targets.get(selectedTargetId);
		
		// select shot
		final List<Shot> shotsForTarget = getShotsForTarget(target);
		final int selectedShotId = MathUtils.random(shotsForTarget.size()-1);
		ret= shotsForTarget.get(selectedShotId).pullVector;
		
		return ret;
	}
	
	private List<Shot> getShotsForTarget(Vector2 t)
	{
		List<Shot> ret = new ArrayList<Shot>();
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
		
		if (ret.isEmpty())
			throw new RuntimeException("No shots to shoot");
		
		return ret;
	}
	
	private List<Shot> getShotsOfCurrentMission()
	{
		List<Shot> ret = new ArrayList<Shot>();
		Mission mission = GameManager.getInstance().getCurrentMission();
		for (Shot sh : allShots) {
			if (sh.mission.equals(mission))
			{
				ret.add(sh);
			}
		}
		return ret;
	}

	private List<Vector2> getCurrentTragets()
	{
		List<Vector2> ret = new ArrayList<Vector2>();
		GameManager gm = GameManager.getInstance();
		final AdvancedStage stage = gm.currentGameScreen.getRenderer().world.stage;
		Set<Actor> allActors = stage.getAllActors();
		for (Actor a : allActors) {
			if (a instanceof TargetActor) {
				TargetActor ta = (TargetActor) a;
				if (ta.getPlayerSide()==PlayerSide.LEFT)
				{
					Vector2 v = new Vector2(a.getX()+a.getOriginX(),a.getY()+a.getOriginY());
					ret.add(v);
				}
			}
		}
		if (ret.isEmpty())
			throw new RuntimeException("No targets found to aim for");
		return ret ;
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

		}
	}
	
}
