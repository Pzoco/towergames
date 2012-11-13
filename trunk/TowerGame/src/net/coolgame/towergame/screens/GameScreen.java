package net.coolgame.towergame.screens;

import java.util.ArrayList;
import java.util.Random;

import net.coolgame.towergame.Card;
import net.coolgame.towergame.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen, InputProcessor
{

	//For drawing etc
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	private BitmapFont bitmapFont;
	private Camera camera;
	
	//For getting random numbers
	private Random random = new Random();
	
	//Players in the game
	private ArrayList<Player> _players = new ArrayList<Player>();
	
	//Discarded cards
	private ArrayList<Card> _discardPile = new ArrayList<Card>();
	
	//Cards that are waiting for their timer to go down
	private ArrayList<Card> _waitingCards = new ArrayList<Card>();
	
	//Holds check of whose turn it is
	private int _currentPlayerIndex = 0;
	
	private int _startingPlayerIndex = 0;
	
	//The current turn
	private int _currentTurn = 1;
	
	public GameScreen(ArrayList<Player> players,int controllingPlayerIndex)
	{
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera(800,480);
		camera.position.set(800/2,400/2,0f);
		bitmapFont = new BitmapFont();
		_players = players;
		for(Player player : players)
		{
			player.deck.shuffle();
			player.drawCards(5);
		}
	}
	public void determineAndSetStartingPlayer()
	{
		_startingPlayerIndex = random.nextInt(2);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//Rendering the current player
		_players.get(_currentPlayerIndex).render(spriteBatch, shapeRenderer, bitmapFont);
		
		
		//Checking if the current play discarded a card or played one
		if(_players.get(_currentPlayerIndex).getDiscardedCard() != null)
		{
			_discardPile.add(_players.get(_currentPlayerIndex).getDiscardedCard());
			_currentPlayerIndex++;
			
		}
		else if(_players.get(_currentPlayerIndex).getPlayedCard() != null)
		{
			_discardPile.add(_players.get(_currentPlayerIndex).getPlayedCard());
			_currentPlayerIndex++;
			
		}
		
		//Both players have done their turns and a new turn begins
		if(_currentPlayerIndex>1)
		{
			startNewTurn();
		}
	}
	public void startNewTurn()
	{
		_currentPlayerIndex=0;
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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

	//Input processor methods:
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int x, int y, int pointer) 
	{
		_players.get(_currentPlayerIndex).touchDragged(x, y, pointer);
		return false;
	}
	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}