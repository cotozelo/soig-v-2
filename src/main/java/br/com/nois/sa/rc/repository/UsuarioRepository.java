package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Usuario findById(String id);

	Usuario findByNome(String nome);

	@Query(fields="{ 'nome': 1, 'nomeDeUsuario': 1, 'telefone': 1, 'email': 1, 'admin': 1, 'usuarioAgencia': 1, 'usuarioFuncionalidade': 1}")
	Usuario findByNomeDeUsuario(String nomeDeUsuario);
}
