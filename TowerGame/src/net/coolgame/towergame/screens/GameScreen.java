package net.coolgame.towergame.screens;

import java.util.ArrayList;
import java.util.Random;

import net.coolgame.towergame.Card;
import net.coolgame.towergame.Player;
import net.coolgame.towergame.TextureRegistry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
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
	private OrthographicCamera camera;
	
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
		TextureRegistry.LoadTextures();
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0,0,0);
		camera.update();
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
		Gdx.gl.glClearColor(0.1f, 0.5f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glDisable(GL10.GL_CULL_FACE);
		//Rendering the current player
		_players.get(_currentPlayerIndex).render(spriteBatch, shapeRenderer, bitmapFont);
		renderStats();
		
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
		
		spriteBatch.begin();
		bitmapFont.setScale(2);
		bitmapFont.draw(spriteBatch, "HELLO", 0,0);
		spriteBatch.end();
	}
	public void startNewTurn()
	{
		_currentPlayerIndex=0;
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	private void renderStats()
	{
		spriteBatch.begin();
		for(int index = 0;index<2;index++)
		{
			for(int i = 0;i<_players.get(index).getTowerHealth();i++)
			{
				spriteBatch.draw(TextureRegistry.textures.get("towerpiece"), _players.get(index).towerX, _players.get(index).towerY+i*8);
			}
			spriteBatch.setColor(_players.get(index).towerColor);
			spriteBatch.draw(TextureRegistry.textures.get("towertop"),_players.get(index).towerX-6,_players.get(index).towerY+_players.get(index).getTowerHealth()*8);
			spriteBatch.setColor(Color.WHITE);
		}
		spriteBatch.end();
	}
	
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
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
		_players.get(_currentPlayerIndex).touchDown(x,Gdx.graphics.getHeight()-y,pointer,button);
		return true;
	}
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int x, int y, int pointer) 
	{
		_players.get(_currentPlayerIndex).touchDragged(x, Gdx.graphics.getHeight()-y, pointer);
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
