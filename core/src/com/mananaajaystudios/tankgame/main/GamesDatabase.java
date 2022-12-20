package com.mananaajaystudios.tankgame.main;

import java.io.*;
import java.util.ArrayList;

public class GamesDatabase {
    static ArrayList<Game> games = new ArrayList<Game>();

    static public ArrayList<Game> getGames(){
        if(games.size() == 0){

            boolean exists = true;
            ArrayList<String> gameIds = new ArrayList<>();
            String gameId = "a" + 1;
            int n = 1;
            while(exists) {
                File file = new File("games/myGame" + gameId + ".txt");
                exists = file.exists();
                System.out.println(exists);
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
    static public int getGameID(){
        String gameId = "a" + 1;
        int n = 1;
        boolean exists = true;
        while(exists) {
            File file = new File("games/myGame" + gameId + ".txt");
            exists = file.exists();
            if(exists){
                n = n + 1;
            }
            gameId = "a" + n;
        }
        return n;

    }
    static public boolean saveGame(Game game) throws IOException {
        game.gameID = "a" + getGameID();
        try {
            FileOutputStream f = new FileOutputStream(new File("games/myGame" + game.gameID + ".txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(game);
            o.close();
            f.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    static public Game loadGame(String gameID){
        try {
            FileInputStream fi = new FileInputStream(new File("games/myGame" + gameID + ".txt"));
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

