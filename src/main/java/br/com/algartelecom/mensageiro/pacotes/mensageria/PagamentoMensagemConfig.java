package br.com.algartelecom.mensageiro.pacotes.mensageria;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

//Substituir por @component
//@Configuration
public class PagamentoMensagemConfig {

    public static final String NOME_FILA = "FilaPagamento";
    public static final String NOME_EXCHANGE = "ExchangePagamento";
    public static final String ROUTING_KEY = "criarPagamento";

    @Bean
    DirectExchange pagamentoExchange() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    @Bean
    Queue queuePagamento() {
        return QueueBuilder.durable(NOME_FILA).build();
    }

    @Bean
    Binding bindingPagamento() {
        return BindingBuilder.bind(queuePagamento()).to(pagamentoExchange()).with(ROUTING_KEY);
    }
}
