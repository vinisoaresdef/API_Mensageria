package br.com.algartelecom.mensageiro.mensageiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    private String nome;
    private String telefone;
    private String email;
}

