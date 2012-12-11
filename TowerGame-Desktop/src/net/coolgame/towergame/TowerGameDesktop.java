package net.coolgame.towergame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import net.coolgame.towergame.TowerGame;

public class TowerGameDesktop
{
	public static void main(String[] args) 
	{
		
		new LwjglApplication(new TowerGame(), "Tower Game",800,600,true);
	}
}
