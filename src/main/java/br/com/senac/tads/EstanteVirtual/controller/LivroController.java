package br.com.senac.tads.EstanteVirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.senac.tads.EstanteVirtual.model.Livro;
import br.com.senac.tads.EstanteVirtual.repository.LivroRepository;
import org.springframework.ui.Model;

@Controller //Indicando que essa classe é um controller
@RequestMapping("livros")
public class LivroController {
    
    @Autowired
    LivroRepository repository;

    @GetMapping
    public String index (Model model) {
        var lista = repository.findAll();
        model.addAttribute("livros", lista);
        return "pages/adm/home-adm";
    }

    @GetMapping("form")
    public String form(Livro livro) {
        return "pages/adm/livroCadastro";
    }

}

