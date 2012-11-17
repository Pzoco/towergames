package net.coolgame.towergame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;


public class Card
{
	private int _cost = 0;
	public int GetCost() {return _cost;}
	private int _rounds = 0;
	public int GetRounds() {return _rounds;}
	private String _title = "";
	public String GetTitle(){return _title;}
	private String _description = "";
	public String GetDescription(){return _description;}
	public Vector2 position;
	private final int _width = 200;
	private final int _height = 285;
	public int GetWidth(){return _width;}
	public int GetHeight(){return _height;}
	
	public float scale = 1f;
	public float focusedScale = 1.5f;
	
	
	public Card(int cost, int rounds, String title, String description)
	{
		_cost = cost;
		_rounds = rounds;
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
	
	public void render(SpriteBatch spriteBatch,ShapeRenderer shapeRenderer, BitmapFont font,Vector2 position)
	{		
		if(position != null)
		{
			this.position = position;
		}
		font.setScale(scale);
		spriteBatch.begin();
		spriteBatch.draw(TextureRegistry.textures.get("cardtemplate"), 
				position.x, position.y,
				0,0,
				_width,_height,
				scale,scale,
				0,0,0,
				_width,_height,
				false,false);
		font.draw(spriteBatch, _title, position.x+16*scale, position.y+_height*scale-16*scale);
		font.draw(spriteBatch,String.valueOf(_cost)+"g",position.x+_width*scale-48*scale,position.y+_height*scale-16*scale);
		font.draw(spriteBatch, _description, position.x+20*scale,position.y+_height*scale/2+2*scale);
		spriteBatch.end();
		/*
		shapeRenderer.begin(ShapeType.FilledRectangle);
		shapeRenderer.setColor(Color.GRAY);
		shapeRenderer.filledRect(position.x, position.y, _width,_height);
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Rectangle);
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.rect(position.x, position.y, _width, _height);
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.FilledRectangle);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.filledRect(position.x+5, position.y+_height-_height/2, _width-10, _height/3);
		shapeRenderer.end();
		*/
	}
}
