package br.com.algartelecom.mensageiro.pacotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/index")
public class interfaceInicial {

        @GetMapping("/interfaceInicial")
        public String interfaceInicial() {
            return "interfaceInicial";
        }

        @GetMapping("/interfaceCliente")
        public String interfaceCliente() {
            return "interfaceCliente";
        }

        @GetMapping("/interfaceRecarga")
        public String interfaceRecarga() {
            return "interfaceRecarga";
        }

}
