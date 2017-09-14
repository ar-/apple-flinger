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
package com.gitlab.ardash.appleflinger.global;

import java.util.EnumSet;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Logger;
import com.gitlab.ardash.appleflinger.screens.GameScreen;

public class Assets {
	
	public enum LabelStyleAsset {
		MINILABEL, MENUSTYLE, MISSIONNUMBER, HEADLINE, THNINKING;
		
		public LabelStyle style;
		static {
			MINILABEL.style = new LabelStyle();
			MINILABEL.style.font = Assets.FontAsset.CRASHLANDING_64.font;
			MINILABEL.style.fontColor=Color.WHITE;
			
			THNINKING.style = new LabelStyle(MINILABEL.style);
			THNINKING.style.fontColor=Color.BLACK;

			MENUSTYLE.style = new LabelStyle();
			MENUSTYLE.style.font = Assets.FontAsset.GRINCHED_73.font;
			MENUSTYLE.style.fontColor=Color.WHITE;
			MENUSTYLE.style.fontColor=new Color(1,1,1,0.8f);

			MISSIONNUMBER.style = new LabelStyle();
			MISSIONNUMBER.style.font = Assets.FontAsset.BURNSTOWNDAM_75.font;
			MISSIONNUMBER.style.fontColor=Color.WHITE;

			HEADLINE.style = new LabelStyle();
			HEADLINE.style.font = Assets.FontAsset.BURNSTOWNDAM_216.font;
			HEADLINE.style.fontColor=Color.BLACK;

		}
	}
	
	public enum FontAsset {
		GRINCHED_73, BURNSTOWNDAM_216, BURNSTOWNDAM_75, NJNARUTO_72, CRASHLANDING_64;
		
		public BitmapFont font;
		// init
		static {
			float FONT_SIZE_LARGE = 0.2f * GameScreen.SCREEN_HEIGHT;
			float FONT_SIZE_MEDIUM = 0.09f * GameScreen.SCREEN_HEIGHT;
			float FONT_SIZE_MEDIUM_73 = 0.07f * GameScreen.SCREEN_HEIGHT;
			float FONT_SIZE_SMALL = 0.04f * GameScreen.SCREEN_HEIGHT;
			FreeTypeFontGenerator generator;
			FreeTypeFontParameter parameter;
			generator = Assets.getFontGenerator(FontGeneratorAsset.BURNSTOWNDAM);
			parameter = new FreeTypeFontParameter();
			parameter.minFilter = Texture.TextureFilter.Nearest;
			parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;
			//parameter.size = FONT_SIZE_LARGE; // 72*3
			parameter.size = (int)Math.ceil(FONT_SIZE_LARGE);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_LARGE));
			
//			parameter.borderColor = Color.WHITE;
//			parameter.borderStraight=false;
//			parameter.borderWidth=0.1f;
			parameter.borderColor = Color.WHITE;
			parameter.borderStraight=false;
			parameter.borderWidth=0.1f;

			BURNSTOWNDAM_216.font = generator.generateFont(parameter);
			BURNSTOWNDAM_216.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			
			parameter.size = (int)Math.ceil(FONT_SIZE_MEDIUM);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_MEDIUM));
			BURNSTOWNDAM_75.font = generator.generateFont(parameter);
			BURNSTOWNDAM_75.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

			generator = Assets.getFontGenerator(FontGeneratorAsset.GRINCHED);
			parameter.borderColor = Color.BLACK;
			parameter.borderStraight=false;
			parameter.borderWidth=4f;
			parameter.size = (int)Math.ceil(FONT_SIZE_MEDIUM_73);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_MEDIUM_73));
			GRINCHED_73.font = generator.generateFont(parameter);
			GRINCHED_73.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			generator = Assets.getFontGenerator(FontGeneratorAsset.NJNARUTO);
			//parameter.color = Color.RED;
			parameter.borderColor = Color.BLACK;
			parameter.borderStraight=false;
			parameter.borderWidth=5f;
			parameter.size = (int)Math.ceil(FONT_SIZE_MEDIUM);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_MEDIUM));
			NJNARUTO_72.font = generator.generateFont(parameter);
			NJNARUTO_72.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

			generator = Assets.getFontGenerator(FontGeneratorAsset.CRASHLANDING);
			//parameter.color = Color.WHITE;
			parameter.borderColor = Color.BLACK;
			parameter.borderStraight=false;
			parameter.borderWidth=2f;
			parameter.size = (int)Math.ceil(FONT_SIZE_SMALL);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_SMALL));
			CRASHLANDING_64.font = generator.generateFont(parameter);
			CRASHLANDING_64.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
}
		@Override
		public String toString() {
			return "size" + super.toString().replaceAll("[^\\d.]", "") + ".ttf"; // "size72.ttf" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		};
	}

	public enum FontGeneratorAsset {
		BURNSTOWNDAM, NJNARUTO, CRASHLANDING, GRINCHED;
		@Override
		public String toString() {
			return "" + super.toString().toLowerCase() + ".ttf"; // "njnaruto.ttf" //$NON-NLS-1$ //$NON-NLS-2$
		};
	}

	public enum ParticleAsset {
		APPLE, WOOD_RECT;
		@Override
		public String toString() {
			return "" + super.toString().toLowerCase() + ".p"; // "apple.p" //$NON-NLS-1$ //$NON-NLS-2$
		};
	}

	public enum TextureAsset {
		APPLE,SLINGSHOTB,SLINGSHOTF,STONE,BACKGR,INVISIBLE,LARGE_DIALOG,VOLUME,WOODHANGER,THINKING;
		@Override
		public String toString() {
			return "" + super.toString().toLowerCase() + ".png"; // "backgr.png" //$NON-NLS-1$ //$NON-NLS-2$
		};
	}

	public enum MusicAsset {
		BG;
		@Override
		public String toString() {
			return "sounds/" + super.toString().toLowerCase() + ".mp3"; // "sounds/bg.mp3" //$NON-NLS-1$ //$NON-NLS-2$
		};
	}

	/**
	 * Sound asset (for single sounds)
	 * 
	 */
	public enum SoundAsset {
		NOTIFICATION,
		
		EXPLOSION_0,EXPLOSION_1,
		
		WHIZZ_0,WHIZZ_1,WHIZZ_2,
		
		RUBBER_0, RUBBER_1, RUBBER_2, RUBBER_3, 
		
		DORK_HIT_0, DORK_HIT_1, DORK_HIT_2, DORK_HIT_3, DORK_HIT_4, DORK_HIT_5, DORK_HIT_6, DORK_HIT_7, DORK_HIT_8,
		
		DORK_HIT_9, DORK_HIT_10, DORK_HIT_11, DORK_HIT_12, DORK_HIT_13, DORK_HIT_14, DORK_HIT_15, DORK_HIT_16,

		WOOD_DEST_0, WOOD_DEST_1, WOOD_DMG_0, WOOD_DMG_1, WOOD_DMG_2, WOOD_HIT_0, WOOD_HIT_1, WOOD_HIT_2, WOOD_HIT_3, WOOD_HIT_4,

		APPLE_HIT_0, APPLE_HIT_1, APPLE_HIT_2, APPLE_HIT_3, APPLE_HIT_4, APPLE_DEST_0;

		@Override
		public String toString() {
			return "sounds/" + super.toString().toLowerCase() + ".mp3"; // "sounds/apple_hit_1.mp3" //$NON-NLS-1$ //$NON-NLS-2$
		};
	}
	
// 	misc_atlas = new TextureAtlas(Gdx.files.internal("misc.atlas"));
//	seyes_closed = misc_atlas.createSprite("eyes_closed");
	public enum AtlasAsset {
		MISC, UI, WOOD;
		@Override
		public String toString() {
			return "" + super.toString().toLowerCase() + ".atlas"; // "misc.atlas" //$NON-NLS-1$ //$NON-NLS-2$
		};
	}

	public enum SpriteAsset {
		PUFF_0,PUFF_1,PUFF_2,PUFF_3,PUFF_4,PUFF_5,PUFF_6,PUFF_7,PUFF_8,PUFF_9,
		
		DORK_0,DORK_1,DORK_2,DORK_3,
		
		BIRD_0,BIRD_1,BIRD_2,BIRD_3,BIRD_4,BIRD_5,

		BIRD_6,BIRD_7,BIRD_8,BIRD_9,BIRD_10,BIRD_11,
		
		WOOD_TNT_0,
		WOOD_BL_11_0,WOOD_BL_11_1,WOOD_BL_11_2,WOOD_BL_11_3,
		
		WOOD_BL_21_0,WOOD_BL_21_1,WOOD_BL_21_2,WOOD_BL_21_3,
		WOOD_BL_22_0,WOOD_BL_22_1,WOOD_BL_22_2,WOOD_BL_22_3,
		
		WOOD_BL_41_0,WOOD_BL_41_1,WOOD_BL_41_2,WOOD_BL_41_3,
		WOOD_BL_42_0,WOOD_BL_42_1,WOOD_BL_42_2,WOOD_BL_42_3,
		
		WOOD_BL_81_0,WOOD_BL_81_1,WOOD_BL_81_2,WOOD_BL_81_3,
		
		WOOD_TRIA_0,WOOD_TRIA_1,WOOD_TRIA_2,WOOD_TRIA_3,
		WOOD_RECT_0,WOOD_RECT_1,WOOD_RECT_2,WOOD_RECT_3,
		
		EYES_CLOSED,
		EYES_DOWN,
		EYES_INNER,
		EYES_LEFT,
		EYES_OUTER,
		EYES_RIGHT,
		EYES_UP,

		DIALOG,
		SLIDERBACK,
		BTN_INFO,
		BTN_JOYPAD,
		BTN_LEADER,
		BTN_ACHI,
		BTN_SIGNIN,
		BTN_SQ_EMPTY,
		BTN_FL_EMPTY,
		BTN_TW,
		BTN_FB,
		BTN_GP,
		BTN_PI,
		BTN_CLOSE,
		BTN_PLAY,
		BTN_1PLAYER,
		BTN_2PLAYERS,
		BTN_SETTINGS,
		BTN_SOUND_ON,
		BTN_SOUND_OFF,
		BTN_BACK,
		BTN_PAUSE,
		BTN_REFRESH,
		BTN_ABORT,
		BTN_BLANK;
		
		static {
			// init all sprites
			for (SpriteAsset e : SpriteAsset.values()) {
				// first the name and index must be set, so thString(works properly)
				final String lowername = e.name().toLowerCase();
				if (lowername.contains("_")) //$NON-NLS-1$
				{
					final int uscp = lowername.lastIndexOf('_'); // underscore position
					e.rname = lowername.substring(0, uscp);
					final String lIndex = lowername.substring(uscp+1, lowername.length());
					try {
						e.rindex = Integer.valueOf(lIndex);
					} catch (NumberFormatException e1) {
						e.rname = lowername;
						e.rindex = -1;
					}
				}
				else
				{
					e.rname = lowername;
					e.rindex = -1;
				}
				
				// the sprite could be found automatically here, by searching all regions in the atlas for that name
				// but then all atlasses would have to be preloaded right away as soon as only one sprite is needed. is that good?
				
				// try it
				TextureAtlas foundAtlas = null;
				for (AtlasAsset aa : AtlasAsset.values())
				{
					final TextureAtlas atlas = getAtlas(aa);
					final AtlasRegion foundRegion = atlas.findRegion(e.rname,e.rindex);
					if (foundRegion!= null)
					{
						foundAtlas = atlas;
						if (foundRegion.index !=e.rindex)
							throw new RuntimeException("found region "+ e.toString() + " but with wrong index"); //$NON-NLS-1$ //$NON-NLS-2$
						break;
					}
				}
				if (foundAtlas == null)
					throw new RuntimeException("No atlas found for "+e.toString()); //$NON-NLS-1$
				foundAtlas.getTextures().first().setFilter(TextureFilter.Linear, TextureFilter.Linear);
				e.sprite= foundAtlas.createSprite(e.rname,e.rindex);
				if (e.sprite == null)
					throw new RuntimeException("Sprite not created for "+e.toString()); //$NON-NLS-1$
			}
		}

		private Sprite sprite;
		private String rname =""; //$NON-NLS-1$
		private int rindex=-1;
		public Sprite get()
		{
			return sprite;
		}
		
		@Override
		public String toString() {
			return "" + rname + (rindex ==-1 ? "" : "_"+rindex); // "eyes_closed" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		};
	}


	/**
	 * Sound group
	 *
	 */
	public enum SoundGroupAsset {
		RUBBER, WHIZZ, EXPLOSION, 
		
		DORK_HIT,

		APPLE_HIT, APPLE_DEST,
		
		WOOD_HIT, WOOD_DMG, WOOD_DEST;

		static {
			for (SoundGroupAsset e : SoundGroupAsset.values()) {
				if (e.members.size() == 0) {
					// apply default format - get format from own name
					final String format = e.name() + "_%d"; //$NON-NLS-1$
					e.fillMembersByFormat(format, e.members);
				}
				if (e.members.size() == 0)
					throw new RuntimeException("Empty Asset Group created in " + e.toString()); //$NON-NLS-1$
			}
		}

		public final EnumSet<SoundAsset> members; // EnumSet has no efficient way of
												// choosing a random element

		/**
		 * creates the sound group exactly from the specified enum set
		 * 
		 * @param members
		 */
		SoundGroupAsset(EnumSet<SoundAsset> members) {
			if (members == null)
				throw new RuntimeException("members can't be null"); //$NON-NLS-1$
			this.members = members;
			if (members.size() == 0)
				throw new RuntimeException("Empty Asset Group created"); //$NON-NLS-1$
		}

		/**
		 * creates the sound group by the specified format
		 * 
		 * @param format
		 *            must contain %d ,which will be replaced by a number
		 *            [0,+inf]
		 */
		SoundGroupAsset(final String format) {
			final EnumSet<SoundAsset> result = EnumSet.noneOf(SoundAsset.class);
			fillMembersByFormat(format, result);

			this.members = result;
		}

		/**
		 * @param format
		 *            must contain %d ,which will be replaced by a number
		 *            [0,+inf]
		 * @param result
		 *            the memebrs array
		 */
		private void fillMembersByFormat(final String format, final EnumSet<SoundAsset> result) {
			for (int i = 0; i < SoundAsset.values().length; i++) {
				// get all the SAsset values that match this name structure
				try {
					final SoundAsset newE = SoundAsset.valueOf(String.format(format, i));
					result.add(newE);
				} catch (Throwable t) {
					break;
				}
			}
		}

		/**
		 * creates the members automatically using its own name as format,
		 * happens in a static block after <init>
		 * 
		 * @param format
		 */
		SoundGroupAsset() {
			EnumSet<SoundAsset> result = EnumSet.noneOf(SoundAsset.class);
			this.members = result;
		}

		public SoundAsset getRandom() {
			return (SoundAsset) (members.toArray())[MathUtils.random(0, members.size() - 1)];
		}
	}

	/**
	 * Sound group
	 *
	 */
	public enum SpriteGroupAsset {
		
		WOOD_RECT,
		WOOD_TRIA,
		WOOD_BL_81,
		WOOD_BL_42,
		WOOD_BL_41,
		WOOD_BL_22,
		WOOD_BL_21,
		WOOD_BL_11,
		
		WOOD_TNT,
		PUFF,
		BIRD,
		DORK;

		static {
			for (SpriteGroupAsset e : SpriteGroupAsset.values()) {
				if (e.members.size() == 0) {
					// apply default format - get format from own name
					final String format = e.name() + "_%d"; //$NON-NLS-1$
					e.fillMembersByFormat(format, e.members);
				}
				if (e.members.size() == 0)
					throw new RuntimeException("Empty Asset Group created in " + e.toString()); //$NON-NLS-1$
			}
		}

		public final EnumSet<SpriteAsset> members;

		/**
		 * creates the sound group by the specified format
		 * 
		 * @param format
		 *            must contain %d ,which will be replaced by a number
		 *            [0,+inf]
		 */
		SpriteGroupAsset(final String format) {
			final EnumSet<SpriteAsset> result = EnumSet.noneOf(SpriteAsset.class);
			fillMembersByFormat(format, result);

			this.members = result;
		}

		/**
		 * @param format
		 *            must contain %d ,which will be replaced by a number
		 *            [0,+inf]
		 * @param result
		 *            the memebrs array
		 */
		private void fillMembersByFormat(final String format, final EnumSet<SpriteAsset> result) {
			for (int i = 0; i < SpriteAsset.values().length; i++) {
				// get all the SAsset values that match this name structure
				try {
					final SpriteAsset newE = SpriteAsset.valueOf(String.format(format, i));
					result.add(newE);
				} catch (Throwable t) {
					break;
				}
			}
		}

		/**
		 * creates the members automatically using its own name as format,
		 * happens in a static block after <init>
		 * 
		 * @param format
		 */
		SpriteGroupAsset() {
			EnumSet<SpriteAsset> result = EnumSet.noneOf(SpriteAsset.class);
			this.members = result;
		}

		public SpriteAsset getRandom() {
			return (SpriteAsset) (members.toArray())[MathUtils.random(0, members.size() - 1)];
		}
		public SpriteAsset get(int i) {
			return (SpriteAsset) (members.toArray())[i];
		}
		public int size() {
			return members.size();
		}
	}

	private static final AssetManager manager = new AssetManager();
	static {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver)); //$NON-NLS-1$
	}
	
	private static Logger log = new Logger("Assets", Application.LOG_NONE); //$NON-NLS-1$

	// actually not needed, just to contentrate the loading time
	// needed to preload all sounds, otherwise sound on android are not plyed the first time.
	// so call this on fist opening of game screen
	public static void load() {
		// manager.load(MAsset.BG.toString(), Music.class);
		// can iterate over all enums, but even if not, the
		// get{Music|Sound|Texture} Methods will load it
		for (SoundAsset sa : SoundAsset.values()) {
			getSound(sa);
		}
		for (MusicAsset ma : MusicAsset.values()) {
			getMusic(ma);
		}
		for (TextureAsset ta : TextureAsset.values()) {
			getTexture((ta));
		}
		// not really needed as they are loaded statically in the enum constructor of SpriteAssets
		for (AtlasAsset aa : AtlasAsset.values()) {
			getAtlas(aa);
		}
		// makesure to generate all sprite from atlasses
		for (SpriteAsset aa : SpriteAsset.values()) {
			aa.get();
		}
		

		// manager.finishLoading(); // is included in getters
	}
	
	/**
	 * this is the preparation of anync load, so it knows what is supposed to be loaded
	 * for load() it is included
	 */
	public static void enqueueAll() {
		// manager.load(MAsset.BG.toString(), Music.class);
		// can iterate over all enums, but even if not, the
		// get{Music|Sound|Texture} Methods will load it
		for (SoundAsset sa : SoundAsset.values()) {
			manager.load(sa.toString(), Sound.class);
		}
		for (MusicAsset ma : MusicAsset.values()) {
			manager.load(ma.toString(), Music.class);
		}
		for (AtlasAsset aa : AtlasAsset.values()) {
			manager.load(aa.toString(), TextureAtlas.class);
		}
		for (TextureAsset aa : TextureAsset.values()) {
			if (aa!=TextureAsset.INVISIBLE)
				manager.load(aa.toString(), Texture.class);
		}

		for (FontGeneratorAsset aa : FontGeneratorAsset.values()) {
				manager.load(aa.toString(), FreeTypeFontGenerator.class);
		}

	}
	
	// must be called repeatedly, will then load stuff in background
	public static boolean loadAsync() {
		return manager.update();
	}

	public static Music getMusic(MusicAsset m) {
		final String path = m.toString();
		if (!manager.isLoaded(path)) {
			manager.load(path, Music.class);
			manager.finishLoading();
		}
		final Music music = manager.get(path, Music.class);
		music.setLooping(true);
		return music;
	}

	public static Sound getSound(SoundAsset s) {
		final String path = s.toString();
		if (!manager.isLoaded(path)) {
			manager.load(path, Sound.class);
			manager.finishLoading();
		}
		return manager.get(path, Sound.class);
	}

	public static ParticleEffect getParticleEffect(ParticleAsset pa) {
		final String path = pa.toString();
		if (!manager.isLoaded(path)) {
			manager.load(path, ParticleEffect.class);
			manager.finishLoading();
		}
		return manager.get(path, ParticleEffect.class);
	}

	public static Texture getTexture(TextureAsset s) {
		if (s==TextureAsset.INVISIBLE)
			return null;
		final String path = s.toString();
		if (!manager.isLoaded(path)) {
			final TextureParameter param = new TextureParameter();
			param.minFilter = TextureFilter.MipMapLinearLinear;
			param.magFilter= TextureFilter.Linear;
			param.genMipMaps = true;
			manager.load(path, Texture.class,param);
			manager.finishLoading();
		}
		final Texture texture = manager.get(path, Texture.class);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//texture.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.MipMapLinearLinear);
		return texture;
	}

	public static Sound getRandomSound(SoundGroupAsset sg) {
		final SoundAsset random = sg.getRandom();
		final Sound sound = getSound(random);
		if (sound == null)
			log.error("sound was null"); //$NON-NLS-1$
			//throw new RuntimeException("sound was null");
		return sound;
	}

	private static TextureAtlas getAtlas(AtlasAsset aa) {
		final String path = aa.toString();
		if (!manager.isLoaded(path)) {
			manager.load(path, TextureAtlas.class);
			manager.finishLoading();
		}
		return manager.get(path, TextureAtlas.class);
	}

	private static FreeTypeFontGenerator getFontGenerator(FontGeneratorAsset aa) {
		final String path = aa.toString();
		if (!manager.isLoaded(path)) {
			manager.load(path, FreeTypeFontGenerator.class);
			manager.finishLoading();
		}
		return manager.get(path, FreeTypeFontGenerator.class);
	}

//	public static BitmapFont getFont(FontAsset fa) {
//		final String path = fa.toString();
//		if (!manager.isLoaded(path)) {
//			FreeTypeFontLoaderParameter params = new FreeTypeFontLoaderParameter();
//			switch (fa) {
//			case BURNSTOWNDAM_75:
//				params.fontFileName = FontGeneratorAsset.BURNSTOWNDAM.toString();
//				params.fontParameters.size = 75;
//				break;
//			case BURNSTOWNDAM_216:
//				params.fontFileName = FontGeneratorAsset.BURNSTOWNDAM.toString();
//				params.fontParameters.size = 216;
//				break;
//			case NJNARUTO_72:
//				params.fontFileName = FontGeneratorAsset.NJNARUTO.toString();
//				params.fontParameters.size = 72;
//				// TODO params not supported yet - enable this function when they work
//				params.borderColor = Color.BLACK;
//				params.borderStraight=false;
//				params.borderWidth=5f;
//				break;
//			default:
//				throw new RuntimeException("font not set up: "+fa);
//			}
//			manager.load(path, BitmapFont.class, params);
//			//manager.load(path, BitmapFont.class);
//			manager.finishLoading();
//		}
//		return manager.get(path, BitmapFont.class);
//	}

	// public static RandomSet<Sound> getSoundGroup(SGAsset sg)
	// {
	// RandomSet<Sound> ret = new RandomSet<Sound>();
	// // this automatic loadign idea would be too slow, because on each sound
	// file load, if would first check if such a file exists
	// if(!manager.isLoaded(sg.toString()))
	// {
	// manager.load(sg.toString(), Sound.class);
	// manager.finishLoading();
	// }
	// //return manager.get(sg.toString(), Sound.class);
	//
	// return ret;
	// }

	/**
	 * early access all enum, so errors throw up early
	 */
	public static void validate() {
		for (SoundGroupAsset sg : SoundGroupAsset.values()) {
			sg.toString();
		}
	}

	public static void dispose() {
		manager.dispose();
		for (FontAsset fa : FontAsset.values())
		{
			try {
				fa.font.dispose(); // can throw if already disposed
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getPercentLoaded()
	{
		return (int)(manager.getProgress()*100);
	}
	
	
	

}
