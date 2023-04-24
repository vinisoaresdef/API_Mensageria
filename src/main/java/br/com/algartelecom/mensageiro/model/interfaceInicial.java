package br.com.algartelecom.mensageiro.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/index")
public class interfaceInicial {

        @RequestMapping("/interfaceInicial")
        public String interfaceInicial() {
            return "interfaceInicial";
        }

        @RequestMapping("/interfaceCliente")
        public String interfaceCliente() {
            return "interfaceCliente";
        }

        @RequestMapping("/interfaceRecarga")
        public String interfaceRecarga() {
            return "interfaceRecarga";
        }

}
