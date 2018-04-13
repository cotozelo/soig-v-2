package br.com.nois.sa.rc;
//http://blog.algaworks.com/spring-boot/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nois.sa.rc.carga.Dado;
import br.com.nois.sa.rc.carga.Indicador;
import br.com.nois.sa.rc.controller.RotinaCargaController;
import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.repository.AgenciaRepository;
import br.com.nois.sa.rc.repository.DadoRepository;
import br.com.nois.sa.rc.repository.FuncionalidadeRepository;
import br.com.nois.sa.rc.repository.GrupoRepository;
import br.com.nois.sa.rc.repository.InclinacaoRepository;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.UnidadeRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	LogRepository logRepository;
	@Autowired
	FuncionalidadeRepository funcionalidadeRepository;
	@Autowired
	GrupoRepository grupoRepository;
	@Autowired
	InclinacaoRepository inclinacaoRepository;
	@Autowired
	UnidadeRepository unidadeRepository;
	@Autowired
	AgenciaRepository agenciaRepository;
	@Autowired
	DadoRepository dadoRepository;
	@Autowired
	IndicadorRepository indicadorRepository;
	@Autowired
	MunicipioRepository municipioRepository;
	@Autowired
	VersaoRepository versaoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	RotinaCargaController rotinaCargaController;

	// private Scanner ler;
	private String PATH_CARGA = "/home/dev/Work/SOIG_V2/src/main/java/br/com/nois/sa/rc/carga/arquivos/";

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("\n\nAPLICACAO NO AR...\n\n");

		/*
		 * Map<String, Object> repositorios = new HashMap<String, Object>();
		 * repositorios.put("log", this.logRepository);
		 * repositorios.put("funcionalidade", this.funcionalidadeRepository);
		 * repositorios.put("grupo", this.grupoRepository);
		 * repositorios.put("inclinacao", this.inclinacaoRepository);
		 * repositorios.put("unidade", this.unidadeRepository);
		 * repositorios.put("agenciaRepository", this.agenciaRepository);
		 * repositorios.put("dadoRepository", this.dadoRepository);
		 * repositorios.put("municipioRepository", this.municipioRepository);
		 * repositorios.put("indicadorRepository", this.indicadorRepository);
		 * repositorios.put("versaoRepository", this.versaoRepository);
		 */

		Indicador indicador = null;
		Dado dado = null;

		// System.out.println("fazer carga dos municipios:\n");
		// MunicipioPrestadora municipioPrestadora = new
		// MunicipioPrestadora(repositorios);
		// municipioPrestadora.run(PATH_CARGA + "MunicipiosPrestadoras.csv");
		/*
		 * System.out.println("fazer carga dos dados:\n"); dado = new
		 * Dado(repositorios); dado.run(PATH_CARGA + "DADOS_2016.csv", "2016");
		 * 
		 * System.out.println("fazer carga dos dados:\n"); indicador = new
		 * Indicador(repositorios); indicador.run(PATH_CARGA +
		 * "INDICADORES_2016.csv", "2016");
		 * 
		 * System.out.println("fazer carga dos dados:\n"); dado = new
		 * Dado(repositorios); dado.run(PATH_CARGA + "DADOS_2015.csv", "2015");
		 * 
		 * System.out.println("fazer carga dos dados:\n"); indicador = new
		 * Indicador(repositorios); indicador.run(PATH_CARGA +
		 * "INDICADORES_2015.csv", "2015");
		 * 
		 * System.out.println("fazer carga dos dados:\n"); dado = new
		 * Dado(repositorios); dado.run(PATH_CARGA + "DADOS_2014.csv", "2014");
		 * 
		 * System.out.println("fazer carga dos dados:\n"); indicador = new
		 * Indicador(repositorios); indicador.run(PATH_CARGA +
		 * "INDICADORES_2014.csv", "2014");
		 * 
		 * System.out.println("fazer carga dos dados:\n"); Equacao equacao = new
		 * Equacao(repositorios); equacao.run(PATH_CARGA + "Equacao.csv");
		 * 
		 * System.out.println("Fim da Carga de Dados"); /* ler = new
		 * Scanner(System.in); System.out.
		 * println("Informe o caminho dos arquivos para carga seguido da barra:\n"
		 * ); System.out.
		 * println("EXEMPLO: C:\\Users\\aline.silva\\Desktop\\carga\\ \n");
		 * System.out.println("TESTE"); String caminho = ler.nextLine();
		 * System.out.println("caminho" + caminho);
		 * 
		 * this.rotinaCargaController = new
		 * RotinaCargaControllerImpl(repositorios);
		 * this.rotinaCargaController.executaRotina(caminho);
		 *
		 * 
		 * Map<String, Double> example = new HashMap<String, Double>();
		 * DadoControllerImpl dados = new DadoControllerImpl(dadoRepository,
		 * logRepository, versaoRepository); List<Dado> listaDados =
		 * dados.getAll(); for (Dado teste : listaDados) {
		 * example.put(teste.getSigla(), teste.getValor()); }
		 * 
		 * EquacaoControllerImpl equacao = new
		 * EquacaoControllerImpl(indicadorRepository, logRepository,
		 * versaoRepository); Double resultado = equacao.result(example,
		 * "IN001 = (( AG003 + AG004 ) / (AG005 - AG006) ) * 100");
		 * System.out.println("Resultado: " + resultado.toString());
		 */
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
