package br.com.algartelecom.mensageiro.pacotes.controllers;

import br.com.algartelecom.mensageiro.pacotes.entities.Usuario;
import br.com.algartelecom.mensageiro.pacotes.mensageria.UsuarioMensagemConfig;
import br.com.algartelecom.mensageiro.pacotes.repository.UsuarioRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/success")
    public String showRegistrationSuccess() {
        return "registrationUsuarioSuccess";
    }


    @GetMapping("/meuPerfil")
    public String showMyProfile() {
        return "perfilUsuario";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Usuario> getAllUsers() {
        // This returns a JSON or XML with the users
        return usuarioRepository.findAll();
    }

    @PostMapping("/produzirCadastroUsuario")
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

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @RequestBody Usuario usuario, Model model) {
        Usuario usuario1 = usuarioRepository.findById((int) id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        usuario1.setNome(usuario.getNome());
        usuario1.setTelefone(usuario.getTelefone());
        usuario1.setEmail(usuario.getEmail());
        usuarioRepository.save(usuario1);
        model.addAttribute("users", usuarioRepository.findAll());
        return "updatedUserSuccess";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable("id") long id, Model model) {
        Usuario usuario = usuarioRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        usuarioRepository.delete(usuario);
        model.addAttribute("users", usuarioRepository.findAll());
        return "deletedUserSuccess";
    }



}
