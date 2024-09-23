package com.example.gameserviceapi.services;

import com.example.gameserviceapi.commons.entities.Game;

import java.util.List;

public interface GameService {
    Game saveGame(Game game);
    Game getGameById(String id);
    List<Game> getAllGames();
    Game updateGame(String gameId, Game game);
    void deleteGame(String id);
}
