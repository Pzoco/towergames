package net.coolgame.towergame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Card
{
	private int _cost = 0;
	public int GetCost() {return _cost;}
	public int rounds = 0;
	private String _title = "";
	public String GetTitle(){return _title;}
	private String _description = "";
	public String GetDescription(){return _description;}
	public Vector2 position;
	private final int _width = 200;
	private final int _height = 285;
	public int GetWidth(){return _width;}
	public int GetHeight(){return _height;}
	
	public int ownerIndex = -10;
	public int targetIndex = -10;
	
	public float scale = 1f;
	public float focusedScale = 1.5f;
	
	private float _scaleTarget = -1f;
	private Boolean _scaleUp = false;
	
	
	public Card(int cost, int rounds, String title, String description)
	{
		_cost = cost;
		this.rounds = rounds;
		_title = title;
		_description = description;
	}
	
	public Boolean containsPoint(int x,int y)
	{
		if(position.x< x && position.y <y &&
		position.x+_width*scale>x && position.y+_height*scale>y)
		{
			return true;
		}
		return false;
	}
	public void scale(float targetScale,Boolean scaleUp)
	{
		_scaleTarget = targetScale;
		_scaleUp = scaleUp;
		
	}
	
	private void updateScaling()
	{
		if(scale >_scaleTarget && !_scaleUp)
		{
			scale-=0.1f;
		}
		else if(scale <_scaleTarget  && _scaleUp)
		{
			scale+=0.1f;
		}
		
		if((scale >=_scaleTarget && _scaleUp) || 
				(scale <=_scaleTarget  && !_scaleUp))
		{
			scale = _scaleTarget;
			_scaleTarget = -1;
		}
	}
	public void render(SpriteBatch spriteBatch, BitmapFont font,Vector2 position)
	{
		if(_scaleTarget != -1)
		{
			updateScaling();
		}
		if(position != null)
		{
			this.position = position;
		}
		font.setScale(scale);
		spriteBatch.draw(TextureRegistry.textures.get("cardtemplate"), 
				position.x, position.y,
				0,0,
				_width,_height,
				scale,scale,
				0,0,0,
				_width,_height,
				false,false);
		font.draw(spriteBatch, _title, position.x+16*scale, position.y+_height*scale-16*scale);
		font.draw(spriteBatch,_cost+"g"+"/"+rounds+"r",position.x+_width*scale-58*scale,position.y+_height*scale-16*scale);
		font.draw(spriteBatch, _description, position.x+20*scale,position.y+_height*scale/2+2*scale);
		font.setScale(1);
	}
}
