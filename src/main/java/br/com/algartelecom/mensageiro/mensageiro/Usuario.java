package br.com.algartelecom.mensageiro.mensageiro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity
//@Table(name = "Usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    //@Column(name = "nome")
    private String nome;

    //@Column(name = "telefone")
    private String telefone;

    //@Column(name = "email")
    private String email;

    public Usuario(String nome, String telefone, String email) {
        //create id with UUID
        this.id = java.util.UUID.randomUUID().toString();
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }


}

