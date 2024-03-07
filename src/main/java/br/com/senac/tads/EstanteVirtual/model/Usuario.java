package br.com.senac.tads.EstanteVirtual.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "usuario")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Email @Column(unique = true)
    private String email;

    @Size(min = 3, max = 20, message = "Usuário deve conter entre 3 a 20 caracteres" )
    private String nomeUsuario;
    
    private String senha;

    private String status = "Ativo";

    private String Departamento = "Usuario";

}
