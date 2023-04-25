package br.com.algartelecom.mensageiro.pacotes.repository;

import br.com.algartelecom.mensageiro.pacotes.entities.Recarga;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecargaRepository extends CrudRepository<Recarga, Integer> {
        Recarga findById(String id);

}
