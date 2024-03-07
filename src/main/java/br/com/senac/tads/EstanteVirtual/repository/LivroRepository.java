package br.com.senac.tads.EstanteVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.senac.tads.EstanteVirtual.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}
