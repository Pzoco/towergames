package net.coolgame.towergame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
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
	private Card _shownCard = null;
	
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
		if(_shownCard == null)
		{
			//Draws the hand - First the ones on the left side and right side of the touched card
			//and finally the touched card, so its the top card.
			for(int i = 0;i<_hand.indexOf(_touchedCard);i++)
			{
				if((Card)_hand.get(i)!= _touchedCard)
				{
					_hand.get(i).render(spriteBatch, shapeRenderer, font, new Vector2(Gdx.graphics.getWidth()/2-_hand.size()*40+40*i,50));
				}
			}
			for(int j = _hand.size()-1;j>_hand.indexOf(_touchedCard);j--)
			{
				if((Card)_hand.get(j)!= _touchedCard)
				{
					_hand.get(j).render(spriteBatch, shapeRenderer, font, new Vector2(Gdx.graphics.getWidth()/2-_hand.size()*40+40*j,50));
				}
			}
			
			_touchedCard.render(spriteBatch, shapeRenderer, font, new Vector2(Gdx.graphics.getWidth()/2-_hand.size()*40+40*_hand.indexOf(_touchedCard),50));
		}
		else	
		{
			//Only show the card that is selected right now, so the player won't get as confused.
			_shownCard.render(spriteBatch, shapeRenderer, font, new Vector2(Gdx.graphics.getWidth()/2-_shownCard.GetWidth()/2,50));
		}
	}
	

	public void touchDragged(int x, int y, int pointer) 
	{
		if(_shownCard != null)
		{
			return;
		}
		
		Card currentCard;
		for(int i = 0;i<_hand.size();i++)
		{
			currentCard = _hand.get(i);
			if(i<_hand.indexOf(_touchedCard))
			{
				if(currentCard.position.x< x && currentCard.position.y <y &&
						currentCard.position.x+40>x && 
						currentCard.position.y+currentCard.GetHeight()*currentCard.scale>y)
				{
					_touchedCard = currentCard;
					break;
				}
			}
			else if(i>_hand.indexOf(_touchedCard))
			{
				if(currentCard.position.x+currentCard.GetWidth()*currentCard.scale-40< x && currentCard.position.y <y &&
						currentCard.position.x+currentCard.GetWidth()*currentCard.scale>x && 
						currentCard.position.y+currentCard.GetHeight()*currentCard.scale>y)
				{
					_touchedCard = currentCard;
					break;
				}
			}
		}
	}


	public void touchDown(int x, int y, int pointer, int button) 
	{
		if(_shownCard == null)
		{
			Card currentCard;
			for(int i = 0;i<_hand.size();i++)
			{
				currentCard = _hand.get(i);
				if(i<_hand.indexOf(_touchedCard))
				{
					if(currentCard.position.x< x && currentCard.position.y <y &&
							currentCard.position.x+40>x && 
							currentCard.position.y+currentCard.GetHeight()*currentCard.scale>y)
					{
						_touchedCard = currentCard;
						_shownCard = currentCard;
						currentCard.scale = 1f;
						return;
					}
				}
				else if(i>_hand.indexOf(_touchedCard))
				{
					if(currentCard.position.x+currentCard.GetWidth()*currentCard.scale-40< x && currentCard.position.y <y &&
							currentCard.position.x+currentCard.GetWidth()*currentCard.scale>x && 
							currentCard.position.y+currentCard.GetHeight()*currentCard.scale>y)
					{
						_touchedCard = currentCard;
						_shownCard = currentCard;
						currentCard.scale = 1f;
						return;
					}
				}
			}
			if(_touchedCard.containsPoint(x, y))
			{
				_shownCard = _touchedCard;
				_shownCard.scale = 1f;
			}
		}
		else
		{
			if(!_shownCard.containsPoint(x, y))
			{
				_shownCard.scale = 0.5f;
				_shownCard = null;
			}
		}
	}
}
