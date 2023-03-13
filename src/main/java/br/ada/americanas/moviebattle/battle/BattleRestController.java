package br.ada.americanas.moviebattle.battle;

import br.ada.americanas.moviebattle.movie.Movie;
import br.ada.americanas.moviebattle.player.Player;
import br.ada.americanas.moviebattle.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/battles")
public class BattleRestController {

    private BattleService battleService;
    private PlayerService playerService;

    @Autowired
    public BattleRestController(BattleService battleService, PlayerService playerService) {
        this.battleService = battleService;
        this.playerService = playerService;
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
