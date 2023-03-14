package br.ada.americanas.moviebattle.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/app")
public class App {

    @GetMapping
    public String get(){
        return "/app/index";
    }

}
