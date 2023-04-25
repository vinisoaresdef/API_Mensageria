package br.com.algartelecom.mensageiro.pacotes.mensageria;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TableUsuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    public Usuario(String nome, String telefone, String email) {
        //create id with UUID
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }


}

