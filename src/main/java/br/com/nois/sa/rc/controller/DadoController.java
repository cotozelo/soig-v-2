package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.to.DadoTO;

public interface DadoController {

	public List<DadoTO> getAll();

	public DadoTO getById(@PathVariable("id") String id);

	public DadoTO insert(@RequestBody DadoTO dado);

	public DadoTO update(@RequestBody DadoTO dado);

	public DadoTO deleteById(@PathVariable("id") String id);
}
