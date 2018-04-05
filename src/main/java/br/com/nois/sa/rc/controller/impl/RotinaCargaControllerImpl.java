package br.com.nois.sa.rc.controller.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.nois.sa.rc.controller.RotinaCargaController;
import br.com.nois.sa.rc.repository.FuncionalidadeRepository;
import br.com.nois.sa.rc.repository.GrupoRepository;
import br.com.nois.sa.rc.repository.InclinacaoRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.UnidadeRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;

public class RotinaCargaControllerImpl implements RotinaCargaController {

	private LogRepository logRepository;
	private VersaoRepository versaoRepository;
	private FuncionalidadeRepository funcionalidadeRepository;
	private GrupoRepository grupoRepository;
	private InclinacaoRepository inclinacaoRepository;
	private UnidadeRepository unidadeRepository;

	@Autowired
	FuncionalidadeControllerImpl funcionalidadeController;
	@Autowired
	GrupoControllerImpl grupoController;
	@Autowired
	InclinacaoControllerImpl inclinacaoController;
	@Autowired
	UnidadeControllerImpl unidadeController;

	public RotinaCargaControllerImpl(Map<String, Object> repositorios) {
		super();
		this.logRepository = (LogRepository) repositorios.get("log");
		this.funcionalidadeRepository = (FuncionalidadeRepository) repositorios.get("funcionalidade");
		this.grupoRepository = (GrupoRepository) repositorios.get("grupo");
		this.inclinacaoRepository = (InclinacaoRepository) repositorios.get("inclinacao");
		this.unidadeRepository = (UnidadeRepository) repositorios.get("unidade");
		this.versaoRepository = (VersaoRepository) repositorios.get("versaoRepository");
	}

	public RotinaCargaControllerImpl(LogRepository logRepository, FuncionalidadeRepository funcionalidadeRepository,
			GrupoRepository grupoRepository, InclinacaoRepository inclinacaoRepository,
			UnidadeRepository unidadeRepository) {
		super();
		this.logRepository = logRepository;
		this.funcionalidadeRepository = funcionalidadeRepository;
		this.grupoRepository = grupoRepository;
		this.inclinacaoRepository = inclinacaoRepository;
		this.unidadeRepository = unidadeRepository;
	}

	@Override
	public ArrayList<String> getFiles(String caminho) {
		try {
			FileReader arquivo = new FileReader(caminho);
			BufferedReader leitura = new BufferedReader(arquivo);
			ArrayList<String> itens = new ArrayList<>();

			String linha = leitura.readLine();
			itens.add(linha);
			while (linha != null) {
				itens.add(linha);
				linha = leitura.readLine();
			}
			arquivo.close();
			return itens;

		} catch (FileNotFoundException e) {
			System.err.printf("Erro: CxRCxFx00001 ", e.getMessage());
			return null;
		} catch (IOException e) {
			System.err.printf("Erro: CxRCxFx00002 ", e.getMessage());
			return null;
		}
	}

	@Override
	public void executaRotina(String caminho) {

		System.out.println("\n\n...INICIANDO A CARGA...");
		this.funcionalidadeController = new FuncionalidadeControllerImpl(this.funcionalidadeRepository,
				this.logRepository, this.versaoRepository);
		ArrayList<String> funcionalidades = this.getFiles(caminho + "funcionalidade.txt");
		if (funcionalidades != null) {
			int qtde_funcionalidade = this.funcionalidadeController.rotinaCarga(funcionalidades);
			System.out.println("Funcionalidades: " + qtde_funcionalidade + " insercoes novas.");
		} else {
			System.out.println("Funcionalidades:  Arquivo nao encontrado (" + caminho + "funcionalidade.txt)");
		}

		this.grupoController = new GrupoControllerImpl(this.grupoRepository, this.logRepository, this.versaoRepository);
		ArrayList<String> grupos = this.getFiles(caminho + "grupo.txt");
		if (grupos != null) {
			int qtde_grupo = this.grupoController.rotinaCarga(grupos);
			System.out.println("Grupos: " + qtde_grupo + " insercoes novas.");
		} else {
			System.out.println("Grupos:  Arquivo nao encontrado (" + caminho + "grupo.txt)");
		}

		this.inclinacaoController = new InclinacaoControllerImpl(this.inclinacaoRepository, this.logRepository,
				this.versaoRepository);
		ArrayList<String> inclinacaos = this.getFiles(caminho + "inclinacao.txt");
		if (inclinacaos != null) {
			int qtde_inclinacao = this.inclinacaoController.rotinaCarga(inclinacaos);
			System.out.println("Inclinacoes: " + qtde_inclinacao + " insercoes novas.");
		} else {
			System.out.println("Inclinacoes:  Arquivo nao encontrado (" + caminho + "inclinacao.txt)");
		}

		this.unidadeController = new UnidadeControllerImpl(this.unidadeRepository, this.logRepository,
				this.versaoRepository);
		ArrayList<String> unidades = this.getFiles(caminho + "unidade.txt");
		if (unidades != null) {
			int qtde_unidade = this.unidadeController.rotinaCarga(unidades);
			System.out.println("Unidades: " + qtde_unidade + " insercoes novas.");
		} else {
			System.out.println("Unidades:  Arquivo nao encontrado (" + caminho + "unidade.txt)");
		}

	}

}
