package br.com.algartelecom.mensageiro.pacotes.controllers;

import br.com.algartelecom.mensageiro.pacotes.entities.Usuario;
import br.com.algartelecom.mensageiro.pacotes.mensageria.UsuarioMensagemConfig;
import br.com.algartelecom.mensageiro.pacotes.repository.UsuarioRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cadastro")
public class interfaceCliente {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/novoCadastro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("Usuario", new Usuario());
        return "registrationUser";
    }

    @RequestMapping("/produzirCadastroUsuario")
    public String produzirCadastroUsuario(@RequestParam("nome") String nome, @RequestParam("telefone") String telefone,
                                                        @RequestParam("email") String email) {
        var usuario = new Usuario(nome, telefone, email);
        rabbitTemplate.convertAndSend(UsuarioMensagemConfig.NOME_EXCHANGE, UsuarioMensagemConfig.ROUTING_KEY,
                usuario);
        //retornar um status 200 para o LOG quando der certo.

        //salvar no banco de dados o usuario cadastrado e enviar para a fila de cadastro de usuario no rabbitmq
        usuarioRepository.save(usuario);
        return  "redirect:/cadastro/success";
    }

    @GetMapping("/success")
    public String showRegistrationSuccess() {
        return "registrationUsuarioSuccess";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Usuario> getAllUsers() {
        // This returns a JSON or XML with the users
        return usuarioRepository.findAll();
    }

}