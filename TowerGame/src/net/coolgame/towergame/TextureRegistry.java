package net.coolgame.towergame;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureRegistry 
{
	public static Map<String, Texture> textures = new HashMap<String,Texture>();
	public static void LoadTextures()
	{
		textures.put("cardtemplate",new Texture(Gdx.files.internal("cards-picture/cardtemplate.png")));
		textures.put("cardback",new Texture(Gdx.files.internal("cards-picture/cardback.png")));
		textures.put("playcardbutton", new Texture(Gdx.files.internal("buttons/playcard.png")));
		textures.put("discardcardbutton", new Texture(Gdx.files.internal("buttons/discardcard.png")));
		textures.put("towerpiece", new Texture(Gdx.files.internal("other/towerpiece.png")));
		textures.put("towertop", new Texture(Gdx.files.internal("other/towertop.png")));
	}

}
