package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.MunicipioJSON;

public interface MunicipioController {
	public ResponseEntity<Response<List<MunicipioJSON>>> getByAgenciaId(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId);

	public ResponseEntity<Response<MunicipioJSON>> insert(@PathVariable("username") String userName,
			@RequestBody MunicipioJSON municipio);

	public ResponseEntity<Response<MunicipioJSON>> update(@PathVariable("username") String userName,
			@RequestBody MunicipioJSON municipio);

	public ResponseEntity<Response<MunicipioJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("idMunicipio") String idMunicipio);
}
