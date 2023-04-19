package br.com.algartelecom.mensageiro.mensageiro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PagamentoConsumidor
{

    @RabbitListener(bindings = @QueueBinding(value = @Queue(PagamentoMensagemConfig.NOME_FILA),
            exchange = @Exchange(name = PagamentoMensagemConfig.NOME_EXCHANGE),
            key = PagamentoMensagemConfig.ROUTING_KEY))
    public void processarMensagem(final Message message, final Pagamento Pagamento) {

        log.info("Prioridade {}", message.getMessageProperties().getPriority());
        log.info("Consumindo o pagamento {}", Pagamento);
    }
}
