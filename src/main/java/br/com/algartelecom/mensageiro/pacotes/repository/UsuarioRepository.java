package br.com.algartelecom.mensageiro.pacotes.repository;


import br.com.algartelecom.mensageiro.pacotes.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    List<Usuario> findByNome(String Nome);

    Usuario findById(String id);

}

