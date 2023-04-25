package br.com.algartelecom.mensageiro.pacotes.controllers;

import br.com.algartelecom.mensageiro.pacotes.entities.Recarga;
import br.com.algartelecom.mensageiro.pacotes.mensageria.RecargaMensagemConfig;
import br.com.algartelecom.mensageiro.pacotes.repository.RecargaRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recarga")
public class interfaceRecarga {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RecargaRepository recargaRepository;

    @GetMapping("/novaRecarga")
    public String showRechargeForm(Model model) {
        model.addAttribute("Recarga", new Recarga());
        return "rechargeRegistration";
    }

    @RequestMapping("/produzirRecargaUsuario")
    public String produzirRecargaUsuario(@RequestParam("telefone") String telefone, @RequestParam("operadora") String operadora,
                                           @RequestParam("tipoPagamento") String tipoPagamento, @RequestParam("valor") String valor) {
        var recarga = new Recarga(telefone, operadora, valor, tipoPagamento);
        rabbitTemplate.convertAndSend(RecargaMensagemConfig.NOME_EXCHANGE, RecargaMensagemConfig.ROUTING_KEY,
                recarga);
        //salvar no banco de dados o usuario cadastrado e enviar para a fila de cadastro de usuario no rabbitmq
        recargaRepository.save(recarga);

        return  "redirect:/recarga/success";
    }
    @GetMapping("/success")
    public String showRegistrationSuccess() {
        return "registrationRecargaSuccess";
    }


}
