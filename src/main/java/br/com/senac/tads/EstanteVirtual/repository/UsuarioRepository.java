package br.com.senac.tads.EstanteVirtual.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.senac.tads.EstanteVirtual.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Optional<Usuario> findByEmail(String email);

}
