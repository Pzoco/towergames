package net.coolgame.towergame.screens;

import java.util.ArrayList;
import java.util.Random;

import net.coolgame.towergame.Card;
import net.coolgame.towergame.Player;
import net.coolgame.towergame.TextureRegistry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen, InputProcessor
{

	//For drawing etc
	private SpriteBatch spriteBatch;
	private BitmapFont bitmapFont;
	private OrthographicCamera camera;
	
	//For getting random numbers
	private Random random = new Random();
	
	//Players in the game
	private ArrayList<Player> _players = new ArrayList<Player>();
	
	//Holds check of whose turn it is
	private int _currentPlayerIndex = 0;
	
	private int _startingPlayerIndex = 0;
	
	//The current round
	private int _currentRound = 1;
	
	public GameScreen(ArrayList<Player> players,int controllingPlayerIndex)
	{
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
		_startingPlayerIndex = random.nextInt(2);
		_currentPlayerIndex = _startingPlayerIndex;
		_players.get(_startingPlayerIndex).hasTurn = true;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.1f, 0.5f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glDisable(GL10.GL_CULL_FACE);
		
		
		spriteBatch.begin();
		//Rendering the players
		_players.get(0).render(spriteBatch, bitmapFont);
		_players.get(1).render(spriteBatch, bitmapFont);
		bitmapFont.draw(spriteBatch, "Round: "+_currentRound, Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()-10);
		spriteBatch.end();
		
		if(_players.get(_currentPlayerIndex).hasFinishedTurn)
		{
			_players.get(_currentPlayerIndex).hasTurn = false;
			if(_currentPlayerIndex == 0)
			{
				_currentPlayerIndex++;
			}
			else
			{
				_currentPlayerIndex = 0;
			}
			if(_players.get(_currentPlayerIndex).hasFinishedTurn)
			{
				startNewRound();
			}
			else
			{
				_players.get(_currentPlayerIndex).drawCards(1);
				_players.get(_currentPlayerIndex).hasTurn = true;
			}
		}
	}

	private void startNewRound()
	{
		_currentPlayerIndex=_startingPlayerIndex;
		_currentRound++;
		for(Player player : _players)
		{
			player.hasFinishedTurn = false;		
			player.updateWaitingCards();
		}
		_players.get(_currentPlayerIndex).drawCards(1);
		_players.get(_currentPlayerIndex).hasTurn = true;
	}
	
	static public void invokeCard(Card card)
	{
		
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
