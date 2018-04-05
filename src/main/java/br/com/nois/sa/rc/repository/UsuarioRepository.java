package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Usuario findById(String id);

	Usuario findByNome(String nome);

	Usuario findByNomeDeUsuario(String nomeDeUsuario);
}
