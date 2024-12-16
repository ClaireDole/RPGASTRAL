package fr.rpgastral.controler.texture;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * gestion du packing des textures du jeu
 * le texturepacker crée un dossier packed dans asset avec les fichiers pack1,... qui regroupent les différentes images
 * il ajoute automatiquement un textureatlas permettant de naviguer dans ces fichiers pack
 */
public class TexturePackerHelper {

	public TexturePackerHelper() {
	}

	public TextureAtlas index() {
		TexturePacker.process("Texture", "packed","pack");
		return new TextureAtlas(Gdx.files.internal("packed/pack.atlas"));
	}

}
