package com.mananaajaystudios.tankgame.test;

import com.mananaajaystudios.tankgame.main.Game;
import com.mananaajaystudios.tankgame.main.GamesDatabase;
import com.mananaajaystudios.tankgame.main.player;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@RunWith(MockitoJUnitRunner.class)
public class GamesDatabaseSpec {
    @Mock
    player player1;
    @Mock
    player player2;
    @Mock
    Game game = new Game(player1, player2);
    @InjectMocks
    GamesDatabase gameDatabaseTester;
    public static void main(String[] args) throws IOException {
        GamesDatabaseSpec gamesDatabaseSpec = new GamesDatabaseSpec();
        gamesDatabaseSpec.testGetGames();
        gamesDatabaseSpec.testSaveGame();
        gamesDatabaseSpec.testLoadGame();
    }
    @Test
    public void testGetGames() {
        assertEquals(0, gameDatabaseTester.getGames().size());
    }
    @Test
    public void testLoadGame() {
        assertEquals("a1", gameDatabaseTester.loadGame("a1").gameID);
    }
    @Test
    public void testSaveGame() throws IOException {
        assertTrue(gameDatabaseTester.saveGame(game));
        assertEquals(1, gameDatabaseTester.getGames().size());
    }

}
