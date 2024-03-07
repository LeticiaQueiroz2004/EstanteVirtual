package br.com.senac.tads.EstanteVirtual.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.tads.EstanteVirtual.model.Usuario;
import br.com.senac.tads.EstanteVirtual.repository.UsuarioRepository;

@RestController
@RequestMapping("/adm")
public class UsuarioController {

    @Autowired
    private final UsuarioRepository repository;

    @Autowired
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/adm/login");
        return mv;
    }

    @GetMapping("/listar")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("pages/adm/Usuarios");
        List<Usuario> usuarios = repository.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }
    

    @GetMapping("/cadastro")
    public ModelAndView cadastrarForm() {
        ModelAndView mv = new ModelAndView("pages/adm/CadastroUsuario");
        mv.addObject("usuario", new Usuario());
        return mv;
    }
    
    @PostMapping("/salvarUsuario")
    public ModelAndView cadastrar(@ModelAttribute Usuario usuario) {
        ModelAndView mv = new ModelAndView("redirect:/adm/listar");
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return mv;
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String email,  @RequestParam String senha) {
       
        Optional<Usuario> optUsuario = repository.findByEmail(email);
        if(optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Usuario usuario = optUsuario.get();
        boolean valid = encoder.matches(senha, usuario.getSenha());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }    
}
