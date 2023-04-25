package br.com.algartelecom.mensageiro.pacotes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pagamento {
    //atributos
    private String telefone;
    private String operadora;
    private String valor;
    private String tipoPagamento;
    private String Usuario;
    private String Status;
}
