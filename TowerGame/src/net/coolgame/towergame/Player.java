package net.coolgame.towergame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;


public class Player 
{
	public Deck deck = new Deck();
	private ArrayList<Card> _hand = new ArrayList<Card>();
	private int _towerHealth = 0;
	public int getTowerHealth(){return _towerHealth;}
	private Card _playedCard;
	public Card getPlayedCard(){return _playedCard;}
	private Card _discardedCard;
	public Card getDiscardedCard(){return _discardedCard;}
	
	private Card _touchedCard = null;
	
	public Player(Deck deck,int towerHealth)
	{
		this.deck = deck;
		_towerHealth = towerHealth;
	}
	

	public void drawCards(int amount)
	{
		int drawCount = amount;
		if(deck.cardsLeft()<amount)
		{
			drawCount = deck.cardsLeft();
		}
		for (int i = 0; i < drawCount; i++) 
		{
			_hand.add(deck.drawCard());
		}
		if(_touchedCard == null)
		{
			_touchedCard = _hand.get(_hand.size()-1);
		}
	}
	
	
	public void render(SpriteBatch spriteBatch,ShapeRenderer shapeRenderer,BitmapFont font)
	{
		for(int i = 0;i<_hand.size();i++)
		{
			if((Card)_hand.get(i)!= _touchedCard)
			{
				_hand.get(i).render(spriteBatch, shapeRenderer, font, new Vector2(100+40*i,100));
			}
		}
		_touchedCard.render(spriteBatch, shapeRenderer, font, new Vector2(100+40*_hand.indexOf(_touchedCard),100));
	}
	
	

	public void touchDragged(int x, int y, int pointer) 
	{
		for(int i = 0;i<_hand.size();i++)
		{
			if(_hand.get(i).position.x< x && _hand.get(i).position.y <y &&
				_hand.get(i).position.x+40>x && _hand.get(i).position.y+100>y)
				{
					_touchedCard = _hand.get(i);
					break;
				}
		}
	}
}
