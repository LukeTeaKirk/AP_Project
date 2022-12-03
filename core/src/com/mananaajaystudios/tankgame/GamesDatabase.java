package com.mananaajaystudios.tankgame;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GamesDatabase {
    static ArrayList<Game> games = new ArrayList<Game>();

    static public ArrayList<Game> getGames(){

        if(games == null){
            boolean exists = true;
            ArrayList<String> gameIds = new ArrayList<>();
            String gameId = "a" + 1;
            int n = 1;
            while(exists) {
                File file = new File("myGame" + gameId + ".txt");
                exists = file.exists();
                if(exists){
                    gameIds.add(gameId);
                }
                n = n + 1;
                gameId = "a" + n;
            }
            gameIds.forEach(gameID -> loadGame(gameID));
        }
        return games;
    }
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
            games.add(game);
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


