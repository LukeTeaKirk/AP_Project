package com.mananaajaystudios.tankgame.test;

import com.mananaajaystudios.tankgame.main.GamesDatabase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GamesDatabaseSpec {
    @Test
    public void testGetGames() {
        assertEquals(0, GamesDatabase.getGames().size());
    }
    @Test
    public void testGetGameID() {
        assertEquals(1, GamesDatabase.getGameID());
    }
    @Test
    public void testSaveGame() {
        //assertEquals(1, GamesDatabase.saveGame());
    }

}
