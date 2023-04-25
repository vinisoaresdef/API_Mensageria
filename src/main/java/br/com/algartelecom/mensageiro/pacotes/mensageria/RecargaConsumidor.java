package br.com.algartelecom.mensageiro.pacotes.mensageria;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecargaConsumidor
{

    @RabbitListener(bindings = @QueueBinding(value = @Queue(RecargaMensagemConfig.NOME_FILA),
            exchange = @Exchange(name = RecargaMensagemConfig.NOME_EXCHANGE),
            key = RecargaMensagemConfig.ROUTING_KEY))
    public void processarMensagem(final Message message, final Recarga recarga) {

        log.info("Prioridade {}", message.getMessageProperties().getPriority());
        log.info("Consumindo a Recarga {}", recarga);
    }
}
