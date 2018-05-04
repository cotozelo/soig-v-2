package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.nois.sa.rc.model.json.GraficoJSON;

public interface GraficoController {
	public ResponseEntity<Response<List<GraficoJSON>>> getAll(@PathVariable("username") String userName);

}
