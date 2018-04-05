package br.com.nois.sa.rc.controller.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.VersaoController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;;

@RestController
@RequestMapping("/log")
public class LogControllerImpl implements LogController {
	private LogRepository logRepository;
	private VersaoRepository versaoRepository;

	@Autowired
	VersaoController versaoController;

	@Autowired
	public LogControllerImpl(LogRepository logRepository, VersaoRepository versaoRepository) {
		this.logRepository = logRepository;
		this.versaoRepository = versaoRepository;
		this.versaoController = new VersaoControllerImpl(this.versaoRepository);
	}

	@PutMapping("/insert")
	public Log insert(@RequestBody Log log) {
		try {
			log = this.preencheLog(log);
			this.logRepository.insert(log);
			return log;
		} catch (Exception e) {
			System.out.println("Erro: CxLxCx00010 " + e.getMessage());
			return null;
		}
	}

	private Log preencheLog(Log log) {
		if (log.getData() <= 0)
			log.setData(new Date().getTime());
		if (log.getId() == null)
			log.setId();
		if (log.getUsuario_id() == null || log.getUsuario_id().isEmpty()) {
			log.setUsuario_id("111111");
		}
		log.setVersaoGlobal(this.versaoController.save());
		return log;
	}

	@GetMapping("/versaoGlobal")
	public long getVersaoGlogal() {
		return this.versaoController.getVersaoGlogal() + 1;
	}
}
