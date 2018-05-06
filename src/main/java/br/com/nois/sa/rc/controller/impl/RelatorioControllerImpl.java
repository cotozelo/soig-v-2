package br.com.nois.sa.rc.controller.impl;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nois.sa.rc.controller.RelatorioController;
import br.com.nois.sa.rc.model.json.RelatorioJSON;
import br.com.nois.sa.rc.relatorio.pdf.PDFCreator;
import br.com.nois.sa.rc.relatorio.xls.XLSCreator;

/**
 * @author dev
 *
 */
@RestController
@RequestMapping("/relatorio")
public class RelatorioControllerImpl implements RelatorioController {

	@Override
	@PostMapping("/xls/{username}")
	public void getXLS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RelatorioJSON relatorioJSON = new ObjectMapper().readValue(request.getInputStream(), RelatorioJSON.class);
		OutputStream out = null;

		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + relatorioJSON.getTituloDocumento() + ".xls");
			XLSCreator.createXls(relatorioJSON, response);

		} catch (Exception e) {
			throw new ServletException("Exception in Excel Sample Servlet", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@Override
	@PostMapping("/pdf/{username}")
	public void getPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RelatorioJSON relatorioJSON = new ObjectMapper().readValue(request.getInputStream(), RelatorioJSON.class);
		OutputStream out = null;

		try {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + relatorioJSON.getTituloDocumento() + ".pdf");
			PDFCreator.createPdf(relatorioJSON, response);

		} catch (Exception e) {
			throw new ServletException("Exception in Excel Sample Servlet", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
/*{
	"nomeTela": "dado",
	"tituloDocumento": "titulo",
	"agenciaNome": "Agência SP",
	"municipioNome": "Campinas",
	"prestadoraNome": "SANASA",
	"usuario": "rafael",
	"tabela":[
		{
			"posicao":"0", 
			"valor":"Nome",
			"linhas":[
				{"posicao":"1",	"valor" :"Manoel"},
				{"posicao":"2", "valor" :"Rafael"}
			]
		},
		{
			"posicao":"1", 
			"valor":"Endereco",
			"linhas":[
				{"posicao":"1",	"valor" :"Rua"},
				{"posicao":"2", "valor" :"Av."}
			]},
		{
			"posicao":"2", 
			"valor":"Telefone",
			"linhas":[
				{"posicao":"1",	"valor" :"84"},
				{"posicao":"2", "valor" :"19"}
			]},
		{
			"posicao":"3", 
			"valor":"Cidade",
			"linhas":[
				{"posicao":"1",	"valor" :"JP"},
				{"posicao":"2", "valor" :"CPS"}
			]
		}
	]
}*/
