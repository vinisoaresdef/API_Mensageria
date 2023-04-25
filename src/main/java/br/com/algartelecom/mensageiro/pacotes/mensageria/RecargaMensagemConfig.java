package br.com.algartelecom.mensageiro.pacotes.mensageria;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RecargaMensagemConfig {

    public static final String NOME_FILA = "FilaRecarga";
    public static final String NOME_EXCHANGE = "ExchangeRecarga";
    public static final String ROUTING_KEY = "criarRecarga";

    @Bean
    DirectExchange recargaExchange() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    @Bean
    Queue queueRecarga() {
        return QueueBuilder.durable(NOME_FILA).build();
    }

    @Bean
    Binding bindingRecarga() {
        return BindingBuilder.bind(queueRecarga()).to(recargaExchange()).with(ROUTING_KEY);
    }
}
