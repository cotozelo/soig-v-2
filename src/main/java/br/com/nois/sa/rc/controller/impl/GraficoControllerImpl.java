package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.GraficoController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Grafico;
import br.com.nois.sa.rc.model.json.GraficoJSON;

@RestController
@RequestMapping("/grafico")
public class GraficoControllerImpl implements GraficoController{

	@Override
	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<GraficoJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<GraficoJSON>> response = new Response<List<GraficoJSON>>();

		
		List<GraficoJSON> graficosJSON = new ArrayList<GraficoJSON>();
		graficosJSON.add(new GraficoJSON(Grafico.BARRA.toString(),  Grafico.BARRA.toString()));

		response.setData(graficosJSON);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
