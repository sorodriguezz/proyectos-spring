package com.example.gameserviceapi.controller.impl;

import com.example.gameserviceapi.commons.entities.Game;
import com.example.gameserviceapi.controller.GameApi;
import com.example.gameserviceapi.services.impl.GameServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController implements GameApi {

    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<Game> saveGame (@RequestBody Game game) {
        Game savedGame = this.gameService.saveGame(game);
        return ResponseEntity.ok(savedGame);
    }

    @Override
    public ResponseEntity<Game> getGameById(String id) {
        return ResponseEntity.ok(this.gameService.getGameById(id));
    }

    @Override
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(this.gameService.getAllGames());
    }

    @Override
    public ResponseEntity<Game> updateGame(String id, Game game) {
        return ResponseEntity.ok(this.gameService.updateGame(id, game));
    }

    @Override
    public void deleteGame(String id) {
        this.gameService.deleteGame(id);
    }
}
