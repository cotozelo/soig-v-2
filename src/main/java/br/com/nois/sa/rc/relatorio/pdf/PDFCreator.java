package br.com.nois.sa.rc.relatorio.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import br.com.nois.sa.rc.model.json.ColunaJSON;
import br.com.nois.sa.rc.model.json.PosicaoValorJSON;
import br.com.nois.sa.rc.model.json.RelatorioJSON;

public class PDFCreator {

	public static void createPdf(RelatorioJSON relatorioJSON, HttpServletResponse response) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			document.add(new Paragraph("Agência: " + relatorioJSON.getAgenciaNome()));
			document.add(new Paragraph("Município: " + relatorioJSON.getMunicipioNome()));
			document.add(new Paragraph("Prestadora: " + relatorioJSON.getPrestadoraNome()));
			document.add(new Paragraph("Tela: " + relatorioJSON.getNomeTela()));
			document.add(new Paragraph("Título: " + relatorioJSON.getTituloDocumento()));
			document.add(new Paragraph("Usuário:" + relatorioJSON.getUsuario()));

			Map<Integer, List<String>> tabela = new HashMap<Integer, List<String>>();
			int maxII = 0;
			int maxJJ = 0;
			int countCell = 0;
			for (ColunaJSON colunaJSON : relatorioJSON.getTabela()) {
				if (!tabela.containsKey(0)) {
					tabela.put(0, new ArrayList<String>());
				}
				tabela.get(0).add(Integer.valueOf(colunaJSON.getPosicao()), colunaJSON.getValor());
				countCell++;

				if (colunaJSON.getLinhas() != null && !colunaJSON.getLinhas().isEmpty()) {
					for (PosicaoValorJSON posicaoValorJSON : colunaJSON.getLinhas()) {
						maxII = Math.max(maxII, Integer.valueOf(posicaoValorJSON.getPosicao()));
						if (!tabela.containsKey(Integer.valueOf(posicaoValorJSON.getPosicao()))) {
							tabela.put(Integer.valueOf(posicaoValorJSON.getPosicao()), new ArrayList<String>());
						}
						tabela.get(Integer.valueOf(posicaoValorJSON.getPosicao()))
								.add(Integer.valueOf(colunaJSON.getPosicao()), posicaoValorJSON.getValor());
						//countCell++;
						maxJJ = Math.max(maxJJ, Integer.valueOf(colunaJSON.getPosicao()));
					}
				}
			}

			PdfPTable table = new PdfPTable(countCell);
			for (Integer ii = 0; ii <= maxII; ii++) {
				if (tabela.containsKey(ii)) {
					for (Integer jj = 0; jj <= maxJJ; jj++) {
						try {
							table.addCell(tabela.get(ii).get(jj));
						} catch (IndexOutOfBoundsException ex) {

						}
					}
				}
			}

			document.add(table);

		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
	}

}
/*
 * { "nomeTela": "aline", "tituloDocumento": "titulo", "agenciaNome": "",
 * "municipioNome": "", "prestadoraNome": "", "usuario": "", "titulos": {
 * "quantidade": "4", "coluna1": "NomeNome", "coluna2": "Telefone", "coluna3":
 * "Endereço", "coluna4": "Número" }, "linhas": [ { "linhaNumero": "2",
 * "coluna1": "coluna 2 1", "coluna2": "coluna 2 2", "coluna3": "coluna 2 3",
 * "coluna4": "coluna 2 4" }, { "linhaNumero": "1", "coluna1": "coluna 1 1",
 * "coluna2": "coluna 1 2", "coluna3": "coluna 1 3", "coluna4": "coluna 1 4" } ]
 * }
 */
