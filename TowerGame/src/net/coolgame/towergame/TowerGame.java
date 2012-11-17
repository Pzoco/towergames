package net.coolgame.towergame;

import java.util.ArrayList;

import net.coolgame.towergame.screens.GameScreen;

import com.badlogic.gdx.Game;

public class TowerGame extends Game
{

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		setScreen(InitializeTestGameScreen());
	}
	private GameScreen InitializeTestGameScreen()
	{

		Deck deck1 = new Deck();
		deck1.addCard(new Card(10,2,"Win game","Not really lol"));
		deck1.addCard(new Card(4,3,"Man","attacks"));
		deck1.addCard(new Card(10,2,"Win LOL","Not really lol"));
		deck1.addCard(new Card(5,1,"Man","attacks"));
		deck1.addCard(new Card(10,1,"SC2 game","Not really lol"));
		deck1.addCard(new Card(4,2,"ManBro","is cool"));
		Deck deck2 = new Deck();
		deck2.addCard(new Card(10,3,"Win game","Not really lol"));
		deck2.addCard(new Card(4,4,"Man","attacks"));
		deck2.addCard(new Card(10,2,"Win game","Not really lol"));
		deck2.addCard(new Card(4,4,"Man","attacks"));
		deck2.addCard(new Card(10,1,"Win game","Not really lol"));
		deck2.addCard(new Card(1,0,"Man","attacks"));
		Player player1 = new Player(deck1, 20,0);
		Player player2 = new Player(deck2,20,1);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		return new GameScreen(players,0);
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		getScreen().render(0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
