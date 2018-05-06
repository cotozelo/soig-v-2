package br.com.nois.sa.rc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RelatorioController {
	public void getXLS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public void getPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
