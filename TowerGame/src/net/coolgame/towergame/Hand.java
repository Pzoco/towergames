package net.coolgame.towergame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Hand
{
	private ArrayList<Card> _cards = new ArrayList<Card>();
	
	public void addCard(Card card)
	{
		_cards.add(card);
	}
	
	public void render(SpriteBatch spriteBatch,ShapeRenderer shapeRenderer,BitmapFont font)
	{
		for(int i = 0;i<_cards.size();i++)
		{
			_cards.get(i).render(spriteBatch, shapeRenderer, font,new Vector2(50+i*150,50));
		}
	}
}
