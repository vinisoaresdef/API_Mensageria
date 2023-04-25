package br.com.algartelecom.mensageiro;

import br.com.algartelecom.mensageiro.mensageria.*;
import br.com.algartelecom.mensageiro.pacotes.entities.Pagamento;
import br.com.algartelecom.mensageiro.pacotes.entities.Recarga;
import br.com.algartelecom.mensageiro.pacotes.entities.Usuario;
import br.com.algartelecom.mensageiro.pacotes.mensageria.*;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MensageiroApplicationTest {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void produzirMensagemUsuario() {
        var usuario = new Usuario("Paulo da Silva", "2242-33441", "paulo@novalua.com.br");
        rabbitTemplate.convertAndSend(UsuarioMensagemConfig.NOME_EXCHANGE, UsuarioMensagemConfig.ROUTING_KEY,
                usuario);
    }

    @Test
    public void produzirMensagemRecarga() {
        var recarga = new Recarga("33335555", "AlgarTelecom", "50", "Cartão de Crédito");
        rabbitTemplate.convertAndSend(RecargaMensagemConfig.NOME_EXCHANGE, RecargaMensagemConfig.ROUTING_KEY,
                recarga);
    }

    @Test
    public void produzirMensagemPagamento() {
        var pagamento = new Pagamento("33335555", "AlgarTelecom", "50", "Cartão de Crédito","Paulo da Silva" , "Aguardando");
        rabbitTemplate.convertAndSend(PagamentoMensagemConfig.NOME_EXCHANGE, PagamentoMensagemConfig.ROUTING_KEY,
                pagamento);
    }

}
