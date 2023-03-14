package br.ada.americanas.moviebattle.battle;

import br.ada.americanas.moviebattle.movie.Movie;
import br.ada.americanas.moviebattle.movie.MovieService;
import br.ada.americanas.moviebattle.player.Player;
import br.ada.americanas.moviebattle.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/battles")
public class BattleRestController {

    private BattleService battleService;
    private PlayerService playerService;
    private MovieService movieService;

    @Autowired
    public BattleRestController(BattleService battleService, PlayerService playerService, MovieService movieService) {
        this.battleService = battleService;
        this.playerService = playerService;
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Battle get(
            @PathVariable("id") Long id
    ) {
        return battleService.find(id).get();
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Battle put(
            @PathVariable("id") Long id,
            @RequestBody Movie movie
    ) {



        Optional<Battle> battle = battleService.find(id);
        return battleService.answer(battle.get(), movieService.findById(movie.getId()).get());
    }


    @GetMapping(value = "/list",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Battle> list() {
        return battleService.list();
    }

    @PostMapping
            (value = "/create",
                    consumes = {MediaType.APPLICATION_JSON_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    public Battle create(@RequestBody Player player) {


        return this.battleService.create(this.playerService.findById(player.getId()).get());
    }


}
