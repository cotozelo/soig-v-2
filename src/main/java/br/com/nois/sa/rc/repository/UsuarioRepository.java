package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.UsuarioTO;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioTO, String> {

	UsuarioTO findById(String id);

	UsuarioTO findByNome(String nome);

	@Query(fields = "{ 'nome': 1, 'nomeDeUsuario': 1, 'telefone': 1, 'email': 1, 'admin': 1, 'senha': 1, 'usuarioAgencias': 1, 'perfilId': 1, 'ativo' : 1}")
	UsuarioTO findByNomeDeUsuario(String nomeDeUsuario);

	@Query(fields = "{ 'nomeDeUsuario': 1, 'admin': 1, 'senha': 1 ,'perfilId': 1, 'ativo' : 1}")
	UsuarioTO findBySenhaAndNomeDeUsuarioAndAtivo(String senha, String nomeDeUsuario, boolean ativo);
}
