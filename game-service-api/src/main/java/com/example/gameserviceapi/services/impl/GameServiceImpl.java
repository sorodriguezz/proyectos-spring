package com.example.gameserviceapi.services.impl;

import com.example.gameserviceapi.commons.entities.Game;
import com.example.gameserviceapi.commons.exceptions.GameException;
import com.example.gameserviceapi.repositories.GameRepository;
import com.example.gameserviceapi.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game saveGame(Game game) {
        return this.gameRepository.save(game);
    }

    @Override
    public Game getGameById(String id) {
        return this.gameRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Game not found"));
    }

    @Override
    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    @Override
    public Game updateGame(String id, Game game) {
        this.getGameById(id);
        game.setId(Long.valueOf(id));
        game.setName(game.getName());
        return this.saveGame(game);
    }

    @Override
    public void deleteGame(String id) {
        this.gameRepository.deleteById(Long.valueOf(id));
    }
}
