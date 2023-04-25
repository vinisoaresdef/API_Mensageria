package br.com.algartelecom.mensageiro.pacotes.mensageria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TableRecarga")
public class Recarga {
    //atributos
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "operadora")
    private String operadora;
    @Column(name = "valor")
    private String valor;
    @Column(name = "tipoPagamento")
    private String tipoPagamento;

    public Recarga(String telefone, String operadora, String valor, String tipoPagamento) {
        //create id with UUID
        this.telefone = telefone;
        this.operadora = operadora;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
    }

}
