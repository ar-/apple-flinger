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
package com.gitlab.ardash.appleflinger.global;

import java.util.EnumSet;
import java.util.Locale;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
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
import com.gitlab.ardash.appleflinger.helpers.Pref;
import com.gitlab.ardash.appleflinger.screens.GameScreen;

public class Assets {
	
	private static final String RUSSIAN_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
            + "1234567890.,:;_¡!¿?\"'+-*/()[]={}";
	
	private static final String POLISH_CHARACTERS = "ABCDEFGHIJKLMNOPRSTUVWYZĄĆĘŁŃÓŚŹŻ"
            + "abcdefghijklmnoprstuvwyząćęłńóśźż"
            + "1234567890.,:;_¡!¿?\"'+-*/()[]={}";
	
	private static final String EO_CHARACTERS = "ĉĝĥĵŝŭĈĜĤĴŜŬ";
	
	private static final String EXTRA_CHARACTERS = RUSSIAN_CHARACTERS + POLISH_CHARACTERS + EO_CHARACTERS;
	
	public enum LabelStyleAsset {
		MINILABEL,
		BIGMENUSTYLE,
		MISSIONNUMBER, HEADLINE;
		
		public LabelStyle style;
		static {
			MINILABEL.style = new LabelStyle();
			MINILABEL.style.font = Assets.FontAsset.FLINGER_03_B2_DIAG_MINIL.font;
			if (isNonLatinFontNeeded())
				MINILABEL.style.font = Assets.FontAsset.ZANTROKE_03_B2_DIAG_MINIL_CYRILLIC.font;
			MINILABEL.style.fontColor=Color.WHITE;

			BIGMENUSTYLE.style = new LabelStyle();
			BIGMENUSTYLE.style.font = Assets.FontAsset.FLINGER_05_B4_BIGMENU.font;
			if (isNonLatinFontNeeded())
				BIGMENUSTYLE.style.font = Assets.FontAsset.ZANTROKE_05_B4_BIGMENU.font;
			BIGMENUSTYLE.style.fontColor=Color.WHITE;
			BIGMENUSTYLE.style.fontColor=new Color(1,1,1,0.8f);
			
			MISSIONNUMBER.style = new LabelStyle();
			MISSIONNUMBER.style.font = Assets.FontAsset.BURNSTOWNDAM_75.font;
			MISSIONNUMBER.style.fontColor=Color.WHITE;

			HEADLINE.style = new LabelStyle();
			HEADLINE.style.font = Assets.FontAsset.BURNSTOWNDAM_216.font;
			HEADLINE.style.fontColor=Color.BLACK;

		}
	}
	
	public enum FontAsset {
		BURNSTOWNDAM_216, BURNSTOWNDAM_75, 
		FLINGER_09_B5_POINT_POP,
		FLINGER_05_B4_BIGMENU, 
		FLINGER_03_B2_DIAG_MINIL,
		ZANTROKE_05_B4_BIGMENU,
		ZANTROKE_03_B2_DIAG_MINIL_CYRILLIC;
		
		public BitmapFont font;
		// init
		static {
			float FONT_SIZE_LARGE_20 = 0.2f * GameScreen.SCREEN_HEIGHT;
			float FONT_SIZE_MEDIUM_09 = 0.09f * GameScreen.SCREEN_HEIGHT;
			float FONT_SIZE_MEDIUM_05 = 0.05f * GameScreen.SCREEN_HEIGHT;
			float FONT_SIZE_SMALL_03 = 0.03f * GameScreen.SCREEN_HEIGHT;
			FreeTypeFontGenerator generator;
			FreeTypeFontParameter parameter;
			generator = Assets.getFontGenerator(FontGeneratorAsset.BURNSTOWNDAM); // ***************
			parameter = defaultParameter((int)Math.ceil(FONT_SIZE_LARGE_20),0.1f);
			parameter.borderColor = Color.WHITE;
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_LARGE_20));

			BURNSTOWNDAM_216.font = generator.generateFont(parameter);
			BURNSTOWNDAM_216.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

			parameter.size = (int)Math.ceil(FONT_SIZE_MEDIUM_09);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_MEDIUM_09));
			BURNSTOWNDAM_75.font = generator.generateFont(parameter);
			BURNSTOWNDAM_75.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

			generator = Assets.getFontGenerator(FontGeneratorAsset.FLINGER); // ***************
			parameter = defaultParameter((int)Math.ceil(FONT_SIZE_MEDIUM_05),4f);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_MEDIUM_05));
			FLINGER_05_B4_BIGMENU.font = generator.generateFont(parameter);
			FLINGER_05_B4_BIGMENU.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			parameter = defaultParameter((int)Math.ceil(FONT_SIZE_MEDIUM_09),5f);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_MEDIUM_09));
			FLINGER_09_B5_POINT_POP.font = generator.generateFont(parameter);
			FLINGER_09_B5_POINT_POP.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

			parameter = defaultParameter((int)Math.ceil(FONT_SIZE_SMALL_03),2f);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_SMALL_03));
			FLINGER_03_B2_DIAG_MINIL.font = generator.generateFont(parameter);
			FLINGER_03_B2_DIAG_MINIL.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

			generator = Assets.getFontGenerator(FontGeneratorAsset.ZANTROKE); // ***************
			parameter = defaultParameter((int)Math.ceil(FONT_SIZE_MEDIUM_05*.81f),4f);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_MEDIUM_05*.81f));
			parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS+EXTRA_CHARACTERS;
			ZANTROKE_05_B4_BIGMENU.font = generator.generateFont(parameter);
			ZANTROKE_05_B4_BIGMENU.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

			parameter = defaultParameter((int)Math.ceil(FONT_SIZE_SMALL_03*.81f),2f);
			generator.scaleForPixelHeight((int)Math.ceil(FONT_SIZE_SMALL_03*.81f));
			parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS+EXTRA_CHARACTERS;
			ZANTROKE_03_B2_DIAG_MINIL_CYRILLIC.font = generator.generateFont(parameter);
			ZANTROKE_03_B2_DIAG_MINIL_CYRILLIC.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		}

		/* extracted method to save some lines, returns some default params for fonts */
		private static FreeTypeFontParameter defaultParameter(int size, float borderWidth) {
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.minFilter = Texture.TextureFilter.Nearest;
			parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;
			parameter.borderColor = Color.BLACK;
			parameter.borderStraight=false;
			parameter.borderWidth=borderWidth;
			parameter.size=size;
			return parameter;
		}
		
		@Override
		public String toString() {
			return "size" + super.toString().replaceAll("[^\\d.]", "") + ".ttf"; // "size72.ttf"    
		}
	}

	private enum FontGeneratorAsset {
		BURNSTOWNDAM, FLINGER, ZANTROKE;
		@Override
		public String toString() {
			return "" + super.toString().toLowerCase() + ".ttf"; // example "fliger.ttf"  
		}
	}

	public enum ParticleAsset {
		APPLE, WOOD_RECT;
		@Override
		public String toString() {
			return "" + super.toString().toLowerCase() + ".p";
		}
	}

	public enum TextureAsset {
		APPLE,SLINGSHOTB,SLINGSHOTF,STONE,BACKGR,BACKGR_WINTER,INVISIBLE,LARGE_DIALOG,VOLUME,WOODHANGER;
		@Override
		public String toString() {
			return "" + super.toString().toLowerCase() + ".png"; // "backgr.png"  
		}
	}

	public enum MusicAsset {
		BG;
		@Override
		public String toString() {
			return "sounds/" + super.toString().toLowerCase() + ".mp3"; // "sounds/bg.mp3"  
		}
	}

	/**
	 * Sound asset (for single sounds)
	 * 
	 */
	public enum SoundAsset {
		NOTIFICATION,
		BELL,
		
		EXPLOSION_0,EXPLOSION_1,
		
		WHIZZ_0,WHIZZ_1,WHIZZ_2,
		
		RUBBER_0, RUBBER_1, RUBBER_2, RUBBER_3, 
		
		DORK_HIT_0, DORK_HIT_1, DORK_HIT_2, DORK_HIT_3, DORK_HIT_4, DORK_HIT_5, DORK_HIT_6, DORK_HIT_7, DORK_HIT_8,
		
		DORK_HIT_9, DORK_HIT_10, DORK_HIT_11, DORK_HIT_12, DORK_HIT_13, DORK_HIT_14, DORK_HIT_15, DORK_HIT_16,

		WOOD_DEST_0, WOOD_DEST_1, WOOD_DMG_0, WOOD_DMG_1, WOOD_DMG_2, WOOD_HIT_0, WOOD_HIT_1, WOOD_HIT_2, WOOD_HIT_3, WOOD_HIT_4,

		APPLE_HIT_0, APPLE_HIT_1, APPLE_HIT_2, APPLE_HIT_3, APPLE_HIT_4, APPLE_DEST_0;

		@Override
		public String toString() {
			return "sounds/" + super.toString().toLowerCase() + ".mp3"; // "sounds/apple_hit_1.mp3"  
		}
	}
	
// 	misc_atlas = new TextureAtlas(Gdx.files.internal("misc.atlas"));
//	seyes_closed = misc_atlas.createSprite("eyes_closed");
	public enum AtlasAsset {
		UI, SCENE;
		@Override
		public String toString() {
			return "" + super.toString().toLowerCase() + ".atlas"; // "misc.atlas"  
		}
	}

	public enum SpriteAsset {
		PUFF_0,PUFF_1,PUFF_2,PUFF_3,PUFF_4,PUFF_5,PUFF_6,PUFF_7,PUFF_8,PUFF_9,
		
		DORK_0,DORK_1,DORK_2,DORK_3,

		PENG_0,
		EYES_CLOSED_PENG,
		
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
		FLAG_DE,
		FLAG_EN,
		FLAG_EO,
		FLAG_ES,
		FLAG_FR,
		FLAG_GL,
		FLAG_IT,
		FLAG_NB,
		FLAG_NL,
		FLAG_PL,
		FLAG_PT,
		FLAG_RU,
		FLAG_SV,
		FORK_ME,
		GPL3,
		SLIDERBACK,
		BTN_INFO,
		BTN_JOYPAD,
		BTN_LEADER,
		BTN_ACHI,
		BTN_SQ_EMPTY,
		BTN_FL_EMPTY,
		BTN_UNLOCKED,
		BTN_LOCKED,
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
		BTN_BLANK,
		BTN_WORLD;
		
		static {
			// init all sprites
			for (SpriteAsset e : SpriteAsset.values()) {
				// first the name and index must be set, so thString(works properly)
				final String lowername = e.name().toLowerCase();
				if (lowername.contains("_")) 
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
							throw new RuntimeException("found region "+ e.toString() + " but with wrong index");  
						break;
					}
				}
				if (foundAtlas == null)
					throw new RuntimeException("No atlas found for "+e.toString()); 
				foundAtlas.getTextures().first().setFilter(TextureFilter.Linear, TextureFilter.Linear);
				e.sprite= foundAtlas.createSprite(e.rname,e.rindex);
				if (e.sprite == null)
					throw new RuntimeException("Sprite not created for "+e.toString()); 
			}
		}

		private Sprite sprite;
		private String rname =""; 
		private int rindex=-1;
		public Sprite get()
		{
			return sprite;
		}
		
		@Override
		public String toString() {
			return "" + rname + (rindex ==-1 ? "" : "_"+rindex); // "eyes_closed"   
		}
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
					final String format = e.name() + "_%d"; 
					e.fillMembersByFormat(format, e.members);
				}
				if (e.members.size() == 0)
					throw new RuntimeException("Empty Asset Group created in " + e.toString()); 
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
				throw new RuntimeException("members can't be null"); 
			this.members = members;
			if (members.size() == 0)
				throw new RuntimeException("Empty Asset Group created"); 
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
		DORK,
		PENG;

		static {
			for (SpriteGroupAsset e : SpriteGroupAsset.values()) {
				if (e.members.size() == 0) {
					// apply default format - get format from own name
					final String format = e.name() + "_%d"; 
					e.fillMembersByFormat(format, e.members);
				}
				if (e.members.size() == 0)
					throw new RuntimeException("Empty Asset Group created in " + e.toString()); 
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
		 *            the members array
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
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver)); 
	}
	
	private static Logger log = new Logger("Assets", Application.LOG_NONE); 

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
			ParticleEffectParameter p = new ParticleEffectParameter();
			p.atlasFile = AtlasAsset.SCENE.toString();
			manager.load(path, ParticleEffect.class , p );
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
			log.error("sound was null"); 
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
//			case FLINGER_09_B5_POINT_POP:
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
	
	public static boolean isNonLatinFontNeeded()
	{
		final String userSelectedLingo = Pref.getLingo();
		if (userSelectedLingo.toLowerCase().startsWith("eo"))
			return true;
		if (userSelectedLingo.toLowerCase().startsWith("ru"))
			return true;
		if (userSelectedLingo.toLowerCase().startsWith("pl"))
			return true;
		
		if (userSelectedLingo.equals(""))
		{
			if (Locale.getDefault().getLanguage().equals(new Locale("eo").getLanguage()))
				return true;
			if (Locale.getDefault().getLanguage().equals(new Locale("ru").getLanguage()))
				return true;
			if (Locale.getDefault().getLanguage().equals(new Locale("pl").getLanguage()))
				return true;
		}
		
		return false;
	}
	

}
