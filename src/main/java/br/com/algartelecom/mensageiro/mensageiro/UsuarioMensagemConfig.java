package br.com.algartelecom.mensageiro.mensageiro;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioMensagemConfig {

    public static final String NOME_FILA = "FilaUsuario";
    public static final String NOME_EXCHANGE = "ExchangeUsuario";
    public static final String ROUTING_KEY = "criarUsuario";

    @Bean
    DirectExchange usuarioExchange() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    @Bean
    Queue queueUsuario() {
        return QueueBuilder.durable(NOME_FILA).build();
    }

    @Bean
    Binding bindingUsuario() {
        return BindingBuilder.bind(queueUsuario()).to(usuarioExchange()).with(ROUTING_KEY);
    }
}
