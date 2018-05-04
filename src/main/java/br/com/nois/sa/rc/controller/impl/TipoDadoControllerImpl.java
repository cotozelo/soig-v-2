package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.controller.TipoDadoController;
import br.com.nois.sa.rc.model.TipoDado;
import br.com.nois.sa.rc.model.json.TipoDadoJSON;

@RestController
@RequestMapping("/tipodado")
public class TipoDadoControllerImpl implements TipoDadoController{


	@Override
	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<TipoDadoJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<TipoDadoJSON>> response = new Response<List<TipoDadoJSON>>();

		
		List<TipoDadoJSON> tipoDadosJSON = new ArrayList<TipoDadoJSON>();
		tipoDadosJSON.add(new TipoDadoJSON(TipoDado.SOMATORIO.toString(),TipoDado.SOMATORIO.toString()));
		tipoDadosJSON.add(new TipoDadoJSON(TipoDado.ULTIMOVALOR.toString(),TipoDado.ULTIMOVALOR.toString()));

		response.setData(tipoDadosJSON);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
