package br.com.nois.sa.rc.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.VersaoController;
import br.com.nois.sa.rc.model.Versao;
import br.com.nois.sa.rc.repository.VersaoRepository;

@RestController
@RequestMapping("/versao")
public class VersaoControllerImpl implements VersaoController {
	private VersaoRepository versaoRepository;

	public VersaoControllerImpl(VersaoRepository versaoRepository) {
		this.versaoRepository = versaoRepository;
	}

	@GetMapping("/versao")
	public long getVersaoGlogal() {

		return this.lastVersao().getVersao();
	}

	@PostMapping("/save")
	public long save() {
		Versao maxVersao = this.lastVersao();
		Versao versao = new Versao(maxVersao.getId(), maxVersao.getVersao() + 1);

		versao = this.versaoRepository.save(versao);
		return Long.valueOf(versao.getVersao());
	}

	private Versao lastVersao() {
		Versao maxVersao = new Versao(0l);

		List<Versao> versoes = this.versaoRepository.findAll();
		if (versoes == null) {
			return maxVersao;
		}
		if (versoes.size() > 1) {
			for (Versao versao : versoes) {
				if (versao.getVersao() > maxVersao.getVersao()) {
					maxVersao = versao;
				}
			}
		} else if (versoes.size() == 1) {
			maxVersao = versoes.get(0);
		}

		return maxVersao;

	}

}
