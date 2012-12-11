package net.coolgame.towergame;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button extends Sprite
{
	private String _filePath;
	private Vector2 _position;
	private int _width;
	private int _height;
	private Rectangle _boundingBox;
	
	
	public Button(Vector2 position,int width, int height, String filePath)
	{
		_position = position;
		_width = width;
		_height = height;
		_boundingBox = new Rectangle(position.x,position.y,width,height);
		_filePath = filePath;
	}
	
	public Boolean contains(int x, int y)
	{
		return _boundingBox.contains(x, y);
	}
	
	public void render(SpriteBatch spriteBatch) 
	{
		spriteBatch.draw(TextureRegistry.textures.get(_filePath), _position.x, _position.y, _width, _height);
	}
	
}
