package br.com.algartelecom.mensageiro.mensageiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recarga {
    //atributos
    private String telefone;
    private String operadora;
    private String valor;
    private String tipoPagamento;

}
