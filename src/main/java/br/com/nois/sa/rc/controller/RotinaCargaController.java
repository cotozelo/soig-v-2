package br.com.nois.sa.rc.controller;

import java.util.ArrayList;


public interface RotinaCargaController {
	public ArrayList<String> getFiles(String caminho);
	public void executaRotina(String caminho);
}
