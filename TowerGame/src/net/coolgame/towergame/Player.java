package net.coolgame.towergame;


public class Player 
{
	public Deck deck = new Deck();
	public Hand hand = new Hand();
	private int _towerHealth = 0;
	public int getTowerHealth(){return _towerHealth;}
	private Card _playedCard;
	public Card getPlayedCard(){return _playedCard;}
	private Card _discardedCard;
	public Card getDiscardedCard(){return _discardedCard;}
	
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
			hand.addCard(deck.drawCard());
		}
	}
}
