package com.mananaajaystudios.tankgame;

import java.io.*;
import java.util.ArrayList;

public class GamesDatabase {
    ArrayList<Game> games = new ArrayList<Game>();


    static public void saveGame(Game game){
        try {
            FileOutputStream f = new FileOutputStream(new File("myGame" + game.gameID + ".txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(game);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    static public Game loadGame(String gameID){
        try {
            FileInputStream fi = new FileInputStream(new File("myGame" + gameID + ".txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            Game game = (Game) oi.readObject();
            System.out.println(game.toString());
            oi.close();
            fi.close();
            return game;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}


