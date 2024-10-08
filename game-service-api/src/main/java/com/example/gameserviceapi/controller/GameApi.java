package com.example.gameserviceapi.controller;

import com.example.gameserviceapi.commons.constants.ApiPathVariables;
import com.example.gameserviceapi.commons.entities.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathVariables.VERSION_ROUTE + ApiPathVariables.GAME_ROUTE)
public interface GameApi {
    @PostMapping
    ResponseEntity<Game> saveGame (@RequestBody Game game);

    @GetMapping("/{id}")
    ResponseEntity<Game> getGameById(@PathVariable String id);

    @GetMapping()
    ResponseEntity<List<Game>> getAllGames();

    @PutMapping("/{id}")
    ResponseEntity<Game> updateGame(@PathVariable String id, @RequestBody Game game);

    @DeleteMapping("/{id}")
    void deleteGame(@PathVariable String id);
}
