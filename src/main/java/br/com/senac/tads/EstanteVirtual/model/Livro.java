package br.com.senac.tads.EstanteVirtual.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

// ORM - Mapeamento de Objeto Relacional (JPA - Java Persist )

@Table(name ="livros")
@Entity(name = "livros")
@Getter
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String image;

    int price;

}
