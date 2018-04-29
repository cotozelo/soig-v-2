package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.BooleanJSON;
import br.com.nois.sa.rc.model.json.FavoritoJSON;
import br.com.nois.sa.rc.model.json.UsuarioJSON;

public interface UsuarioController {

	public ResponseEntity<Response<UsuarioJSON>> insert(@PathVariable("username") String username,
			@RequestBody UsuarioJSON usuarioJSON);

	public ResponseEntity<Response<UsuarioJSON>> atribuir(@PathVariable("username") String username,
			@RequestBody UsuarioJSON usuarioJSON);

	public ResponseEntity<Response<UsuarioJSON>> update(@PathVariable("username") String username,
			@RequestBody UsuarioJSON usuarioJSON);

	public ResponseEntity<Response<UsuarioJSON>> getInformacao(@PathVariable("username") String username);

	public ResponseEntity<Response<List<UsuarioJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<UsuarioJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("usuarioid") String usuarioId);

	public ResponseEntity<Response<UsuarioJSON>> favorito(@PathVariable("username") String userName,
			@RequestBody FavoritoJSON favoritoJSON);

	public ResponseEntity<Response<List<UsuarioJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaid") String agenciaId, @PathVariable("municipioid") String municipioId,
			@PathVariable("prestadoraid") String prestadoraId);

	public ResponseEntity<Response<List<UsuarioJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaid") String agenciaId, @PathVariable("municipioid") String municipioId);

	public BooleanJSON unicidadeEmail(@PathVariable("username") String userName, @PathVariable("email") String email);

	public BooleanJSON unicidadeNomeDeUsuario(@PathVariable("username") String userName,
			@PathVariable("nomedeusuario") String nomedeusuario);

}
