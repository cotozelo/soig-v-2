package br.com.nois.sa.rc.controller;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Log;

public interface LogController {

	public Log insert(@RequestBody Log log);

	public long getVersaoGlogal();
}
