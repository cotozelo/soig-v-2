package br.com.nois.sa.rc.relatorio.xls;
//http://www.java2s.com/Code/Jar/j/Downloadjxl2612jar.htm

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.nois.sa.rc.model.json.ColunaJSON;
import br.com.nois.sa.rc.model.json.PosicaoValorJSON;
import br.com.nois.sa.rc.model.json.RelatorioJSON;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class XLSCreator {

	public static void createXls(RelatorioJSON relatorioJSON, HttpServletResponse response) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = workbook.createSheet(relatorioJSON.getNomeTela(), 0);

			for (ColunaJSON colunaJSON : relatorioJSON.getTabela()) {
				sheet.addCell(new Label(Integer.valueOf(colunaJSON.getPosicao()), 0, colunaJSON.getValor()));
				if (colunaJSON.getLinhas() != null && !colunaJSON.getLinhas().isEmpty()) {
					for (PosicaoValorJSON posicaoValorJSON : colunaJSON.getLinhas()) {
						sheet.addCell(new Label(Integer.valueOf(colunaJSON.getPosicao()),
								Integer.valueOf(posicaoValorJSON.getPosicao()), posicaoValorJSON.getValor()));
					}
				}
			}

			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}
