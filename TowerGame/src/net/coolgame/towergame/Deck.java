package net.coolgame.towergame;

import java.util.ArrayList;
import java.util.Random;

public class Deck 
{
	private Random random = new Random();
	private String _name = "";
	private ArrayList<Card> _cards = new ArrayList<Card>();
	
	public void addCard(Card card)
	{
		_cards.add(card);
	}
	public void shuffle()
	{
		//Fisher-Yates shuffle
		int i = _cards.size();
		int j =0;
		while(i>1)
		{
			i--;
			j = random.nextInt(i);
			Card temp = _cards.get(j);
			_cards.set(j, _cards.get(i));
			_cards.set(i, temp);
		}
	}
	public int cardsLeft()
	{
		return _cards.size();
	}
	public Card drawCard()
	{
		return _cards.remove(0);
	}
}
