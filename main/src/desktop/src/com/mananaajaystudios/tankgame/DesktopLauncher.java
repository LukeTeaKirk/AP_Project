package com.mananaajaystudios.tankgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mananaajaystudios.tankgame.MyTankGame;
import com.mananaajaystudios.tankgame.screens.MainPage;
import jdk.tools.jmod.Main;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("TankGame");
		new Lwjgl3Application(new TopDog(), config);
	}
}
