package br.com.nois.sa.rc.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {

	public final void printTest(String metodo, String acao, Object model) {
		System.out.println(new Date().toString() + "\tTESTE\t[" + metodo + "][" + acao + "]\n\t\t{"
				+ new Util().ObjectToString(model) + "}");
	}

	public final String ListColectionToString(List<Object> colections) {
		String ret = "";
		if (colections != null) {
			for (Object item : colections) {
				ret += new Util().ObjectToString(item);
			}
		} else {
			ret = "null";
		}

		return ret;
	}

	public String ObjectToString(Object model) {
		String modelToString = "[ERRO]";
		try {
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(model.getClass().getName());
			@SuppressWarnings("unchecked")
			Method metodoDoSeuObjeto = clazz.getMethod("toString");
			Object retornoDoMetodo = metodoDoSeuObjeto.invoke(model);

			modelToString = retornoDoMetodo.toString();

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return modelToString;
	}

	public ArrayList<String> fileToString(String caminho) {
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
}
