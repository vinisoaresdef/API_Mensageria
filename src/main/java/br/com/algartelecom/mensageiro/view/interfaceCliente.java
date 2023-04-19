package br.com.algartelecom.mensageiro.view;

import br.com.algartelecom.mensageiro.mensageiro.Usuario;
import br.com.algartelecom.mensageiro.mensageiro.UsuarioMensagemConfig;
import br.com.algartelecom.mensageiro.repository.UsuarioRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cadastro")
public class interfaceCliente {

    @Autowired
    private AmqpTemplate rabbitTemplate;


//    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastroUsuario")
    public String exemplo(Model model) {
        model.addAttribute("titulo", "Título da Página");
        return "exemplo";
    }

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
        //salvar no banco de dados o usuario cadastrado e enviar para a fila de cadastro de usuario no rabbitmq
      //  usuarioRepository.save(usuario);

        return  "redirect:/cadastro/success";
    }

    @PostMapping
    public String processRegistration(Usuario usuario) {
        // TODO: save user to database
        return "redirect:/cadastro/success";
    }

    @GetMapping("/success")
    public String showRegistrationSuccess() {
        return "registrationUsuarioSuccess";
    }
}
