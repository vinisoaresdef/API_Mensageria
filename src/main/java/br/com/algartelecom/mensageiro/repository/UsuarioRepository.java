package br.com.algartelecom.mensageiro.repository;

import br.com.algartelecom.mensageiro.mensageiro.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository {
    //aqui salva o usuario no banco de dados
    void save(Usuario usuario);

    //aqui busca o usuario no banco de dados
    Usuario findByEmail(String email);

    //aqui deleta o usuario no banco de dados
    void delete(Usuario usuario);


}
