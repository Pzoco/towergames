package net.coolgame.towergame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
	private final int _width = 128;
	private final int _height = 181;
	
	
	public Card(int cost, int rounds, String title, String description)
	{
		_cost = cost;
		_rounds = rounds;
		_title = title;
		_description = description;
	}
	
	
	public void render(SpriteBatch spriteBatch,ShapeRenderer shapeRenderer, BitmapFont font,Vector2 position)
	{
		if(position != null)
		{
			this.position = position;
		}
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
		spriteBatch.begin();
		font.draw(spriteBatch, _title, position.x+5, position.y+_height-1);
		font.draw(spriteBatch,String.valueOf(_cost)+"g",position.x+_width-30,position.y+_height-1);
		font.draw(spriteBatch, _description, position.x+5,position.y+_height/2);
		spriteBatch.end();
	}
}
