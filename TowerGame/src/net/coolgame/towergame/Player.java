package net.coolgame.towergame;

import java.util.ArrayList;

import net.coolgame.towergame.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Player 
{
	//
	// FIELDS
	//
	public final int playerIndex;
	private Vector2 _towerPosition;
	private Vector2 _discardpilePosition;
	private Vector2 _facedownCardsPosition;
	
	private int _gold = 100;
	public Color towerColor;
	
	public Deck deck = new Deck();
	private ArrayList<Card> _hand = new ArrayList<Card>();
	private ArrayList<Card> _facedownCards = new ArrayList<Card>();
	private ArrayList<Card> _discardPile = new ArrayList<Card>();
	
	private int _towerHealth = 0;
	public int getTowerHealth(){return _towerHealth;}
	
	private Card _touchedCard = null;
	private Card _shownCard = null;
	private final int drawCardGap = 50;
	
	public Boolean hasTurn = false;
	public Boolean hasFinishedTurn = false;
	
	Button _discardCardButton = new Button(new Vector2(Gdx.graphics.getWidth()/2-2*164,Gdx.graphics.getHeight()/2),164,48,"discardcardbutton");
	Button _playCardButton = new Button(new Vector2(Gdx.graphics.getWidth()/2+164,Gdx.graphics.getHeight()/2),164,48,"playcardbutton");
	
	private Card _moveToFacedown;
	private Card _moveToDiscard;
	
	//
	// CONSTRUCTOR
	//
	public Player(Deck deck,int towerHealth,int playerIndex)
	{
		this.deck = deck;
		_towerHealth = towerHealth;
		this.playerIndex = playerIndex;
		if(playerIndex == 0)
		{
			_towerPosition = new Vector2(6+44,10);
			_facedownCardsPosition = new Vector2(10,Gdx.graphics.getHeight()-100);
			_discardpilePosition = new Vector2(10,Gdx.graphics.getHeight()-250);
			towerColor = Color.RED;
		}
		else	
		{
			_towerPosition = new Vector2(Gdx.graphics.getWidth()-38-44,10);
			_facedownCardsPosition = new Vector2(Gdx.graphics.getWidth()-50*3,Gdx.graphics.getHeight()-100);
			_discardpilePosition = new Vector2(Gdx.graphics.getWidth()-10-100,Gdx.graphics.getHeight()-250);
			towerColor = Color.BLUE;
		}
	}
	
	//
	// GAME ACTIONS
	//
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
	public void updateWaitingCards() 
	{
		ArrayList<Card> invokedCards = new ArrayList<Card>();
		for(Card card : _facedownCards)
		{
			card.rounds--;
			if(card.rounds <=0)
			{
				GameScreen.invokeCard(card);
				invokedCards.add(card);
			}
		}
		for(Card card : invokedCards)
		{
			card.scale(0.4f,true);
			_facedownCards.remove(card);
			_discardPile.add(card);
			card.position = _discardpilePosition;
		}
		
	}
	
	//
	// CARD ANIMATION
	//
	private void drawMovingToFacedown()
	{
		if(_moveToFacedown.position.x >_facedownCardsPosition.x)
		{
			_moveToFacedown.position.x-=5;
		}
		else if(_moveToFacedown.position.x <_facedownCardsPosition.x)
		{
			_moveToFacedown.position.x+=10;
		}
		if(_moveToFacedown.position.y <_facedownCardsPosition.y)
		{
			_moveToFacedown.position.y+=10;
		}
		else if(_moveToFacedown.position.y <_facedownCardsPosition.y)
		{
			_moveToFacedown.position.y-=10;
		}

		if(_moveToFacedown.position.x ==_facedownCardsPosition.x && _moveToFacedown.position.y ==_facedownCardsPosition.y)
		{
			_moveToFacedown.position.x = _facedownCardsPosition.x;
			_moveToFacedown.position.y = _facedownCardsPosition.y;
			_facedownCards.add(_moveToFacedown);
			_moveToFacedown = null;
			hasFinishedTurn = true;
		}
	}
	private void drawMovingToDiscard()
	{
		if(_moveToDiscard.position.x >_discardpilePosition.x)
		{
			_moveToDiscard.position.x-=10;
		}
		else if(_moveToDiscard.position.x <_discardpilePosition.x)
		{
			_moveToDiscard.position.x+=15;
		}
		if(_moveToDiscard.position.y <_discardpilePosition.y)
		{
			_moveToDiscard.position.y+=10;
		}
		else if(_moveToDiscard.position.y >_discardpilePosition.y)
		{
			_moveToDiscard.position.y-=10;
		}
		
		if(_moveToDiscard.position.x ==_discardpilePosition.x && _moveToDiscard.position.y ==_discardpilePosition.y)
		{
			_moveToDiscard.position.x = _discardpilePosition.x;
			_moveToDiscard.position.y = _discardpilePosition.y;
			_discardPile.add(_moveToDiscard);
			hasFinishedTurn = true;
			_moveToDiscard = null;
		}
	}
	
	//
	// RENDERING
	//
	public void render(SpriteBatch spriteBatch,BitmapFont font)
	{
		font.draw(spriteBatch, "Gold: "+_gold, _facedownCardsPosition.x, _facedownCardsPosition.y+80);
		renderTower(spriteBatch,font);
		renderDiscardPile(spriteBatch, font);
		renderFacedownCards(spriteBatch);
		if(hasTurn && _shownCard == null)
		{
			renderHand(spriteBatch,font);
		}
		else if(_shownCard != null)
		{
			renderCardInFocus(spriteBatch,font);
		}
		if(_moveToFacedown != null)
		{
			_moveToFacedown.render(spriteBatch, font, _moveToFacedown.position);
			drawMovingToFacedown();		
		}
		else if(_moveToDiscard != null)
		{
			_moveToDiscard.render(spriteBatch, font, _moveToDiscard.position);
			drawMovingToDiscard();
		}
	}
	private void renderHand(SpriteBatch spriteBatch,BitmapFont font)
	{
		
			//Draws the hand - First the ones on the left side and right side of the touched card
			//and finally the touched card, so its the top card.
			for(int i = 0;i<_hand.indexOf(_touchedCard);i++)
			{
				if((Card)_hand.get(i)!= _touchedCard)
				{
					_hand.get(i).render(spriteBatch, font, new Vector2(Gdx.graphics.getWidth()/2-_hand.size()*drawCardGap+drawCardGap*i,50));
				}
			}
			for(int j = _hand.size()-1;j>_hand.indexOf(_touchedCard);j--)
			{
				if((Card)_hand.get(j)!= _touchedCard)
				{
					_hand.get(j).render(spriteBatch, font, new Vector2(Gdx.graphics.getWidth()/2-_hand.size()*drawCardGap+drawCardGap*j+_hand.get(j).GetWidth()/2,50));
				}
			}
			
			_touchedCard.render(spriteBatch, font, new Vector2(Gdx.graphics.getWidth()/2-_hand.size()*drawCardGap+drawCardGap*_hand.indexOf(_touchedCard),50));
	}
	private void renderCardInFocus(SpriteBatch spriteBatch,BitmapFont font)
	{
		//Only show the card that is selected right now, so the player won't get as confused.
		_shownCard.render(spriteBatch, font, new Vector2(Gdx.graphics.getWidth()/2-(_shownCard.GetWidth()*_shownCard.scale)/2,50));
		if(_shownCard.GetCost()<=_gold && _facedownCards.size() <3)
		{
			_playCardButton.render(spriteBatch);
		}
		_discardCardButton.render(spriteBatch);
	}
	private void renderTower(SpriteBatch spriteBatch,BitmapFont font)
	{
		
		for(int i = 0;i<_towerHealth;i++)
		{
			spriteBatch.draw(TextureRegistry.textures.get("towerpiece"), _towerPosition.x, _towerPosition.y+i*8);
		}
		spriteBatch.setColor(towerColor);
		spriteBatch.draw(TextureRegistry.textures.get("towertop"),_towerPosition.x-6,  _towerPosition.y+_towerHealth*8);
		font.draw(spriteBatch, "Health: "+_towerHealth,_towerPosition.x-20,  _towerPosition.y+_towerHealth*8+40);
		spriteBatch.setColor(Color.WHITE);
	}
	private void renderDiscardPile(SpriteBatch spriteBatch, BitmapFont font)
	{
		font.draw(spriteBatch, "Discard Pile ", _discardpilePosition.x, _discardpilePosition.y+140);
		if(_discardPile.size()>0)
		{
			_discardPile.get(_discardPile.size()-1).render(spriteBatch, font, _discardpilePosition);
		}
	}
	private void renderFacedownCards(SpriteBatch spriteBatch)
	{
		for(int i = 0;i<_facedownCards.size();i++)
		{

				spriteBatch.draw(TextureRegistry.textures.get("cardback"), 
				_facedownCardsPosition.x+50*i,  _facedownCardsPosition.y,
				0,0,
				200,285,
				0.2f,0.2f,
				0,0,0,
				200,285,
				false,false);
		}
	}
	
	//
	// INPUT
	//
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
						currentCard.position.x+drawCardGap>x && 
						currentCard.position.y+currentCard.GetHeight()*currentCard.scale>y)
				{
					_touchedCard = currentCard;
					break;
				}
			}
			else if(i>_hand.indexOf(_touchedCard))
			{
				if(currentCard.position.x+currentCard.GetWidth()*currentCard.scale-drawCardGap< x && currentCard.position.y <y &&
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
							currentCard.position.x+drawCardGap>x && 
							currentCard.position.y+currentCard.GetHeight()*currentCard.scale>y)
					{
						_touchedCard = currentCard;
						_shownCard = currentCard;
						currentCard.scale = currentCard.focusedScale;
						return;
					}
				}
				else if(i>_hand.indexOf(_touchedCard))
				{
					if(currentCard.position.x+currentCard.GetWidth()*currentCard.scale-drawCardGap< x && currentCard.position.y <y &&
							currentCard.position.x+currentCard.GetWidth()*currentCard.scale>x && 
							currentCard.position.y+currentCard.GetHeight()*currentCard.scale>y)
					{
						_touchedCard = currentCard;
						_shownCard = currentCard;
						currentCard.scale = currentCard.focusedScale;
						return;
					}
				}
			}
			if(_touchedCard.containsPoint(x, y))
			{
				_shownCard = _touchedCard;
				_shownCard.scale = _shownCard.focusedScale;
			}
		}
		else
		{
			if(_discardCardButton.contains(x, y))
			{	
				_moveToDiscard = _shownCard;
				_moveToDiscard.position = _shownCard.position;				
				_moveToDiscard.scale(0.4f, false);		
				
				_hand.remove(_shownCard);
				_shownCard = null;
				if(_hand.size()>0)
				{
					_touchedCard = _hand.get(0);
				}
			}
			else if(_playCardButton.contains(x,y) && _facedownCards.size() <3 && _gold >= _shownCard.GetCost())
			{
				_moveToFacedown = _shownCard;
				_moveToFacedown.position = _shownCard.position;				
				_moveToFacedown.scale(0.2f, false);				
				
				_hand.remove(_shownCard);
				_gold -=_shownCard.GetCost();
				_shownCard = null;
				if(_hand.size()>0)
				{
					_touchedCard = _hand.get(0);
				}
			}
			else if(!_shownCard.containsPoint(x, y))
			{
				_shownCard.scale = 1f;
				_shownCard = null;
			}
		}
	}

	
}
