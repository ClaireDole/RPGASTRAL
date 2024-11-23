package fr.rpgastral.controler.texture;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TexturePackerHelper {

	public TexturePackerHelper() {
	}

	public TextureAtlas index() {
		TexturePacker.process("Texture", "packed","pack");
		return new TextureAtlas(Gdx.files.internal("packed/pack.atlas"));
	}

}
