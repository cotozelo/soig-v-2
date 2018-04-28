package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.to.DadoValorTO;
import br.com.nois.sa.rc.model.to.IndicadorValorTO;
import br.com.nois.sa.rc.util.Constantes;

public class PainelJSON {

	private String sigla;
	private String valor;
	private String unidade;
	private String tipoCalculo;
	private String mes;
	private String ano;
	private String agencia;
	private String municipio;
	private String prestadora;
	private String mes01;
	private String mes02;
	private String mes03;
	private String mes04;
	private String mes05;
	private String mes06;
	private String mes07;
	private String mes08;
	private String mes09;
	private String mes10;
	private String mes11;
	private String mes12;

	public void setMeses(DadoValorTO to) {

		if (to.getTotal() != null && !to.getTotal().isEmpty()) {
			this.mes = Constantes.PAINEL_TOTAL;
			this.valor = to.getTotal();
		}

		this.mes01 = to.getMes01();
		if (this.mes01 != null && !this.mes01.isEmpty()) {
			this.mes = Constantes.PAINEL_JANEIRO;
			this.valor = this.mes01;
		}
		this.mes02 = to.getMes02();
		if (this.mes02 != null && !this.mes02.isEmpty()) {
			this.mes = Constantes.PAINEL_FEVEREIRO;
			this.valor = this.mes02;
		}
		this.mes03 = to.getMes03();
		if (this.mes03 != null && !this.mes03.isEmpty()) {
			this.mes = Constantes.PAINEL_MARCO;
			this.valor = this.mes03;
		}
		this.mes04 = to.getMes04();
		if (this.mes04 != null && !this.mes04.isEmpty()) {
			this.mes = Constantes.PAINEL_ABRIL;
			this.valor = this.mes04;
		}
		this.mes05 = to.getMes05();
		if (this.mes05 != null && !this.mes05.isEmpty()) {
			this.mes = Constantes.PAINEL_MAIO;
			this.valor = this.mes05;
		}
		this.mes06 = to.getMes06();
		if (this.mes06 != null && !this.mes06.isEmpty()) {
			this.mes = Constantes.PAINEL_JUNHO;
			this.valor = this.mes06;
		}
		this.mes07 = to.getMes07();
		if (this.mes07 != null && !this.mes07.isEmpty()) {
			this.mes = Constantes.PAINEL_JULHO;
			this.valor = this.mes07;
		}
		this.mes08 = to.getMes08();
		if (this.mes08 != null && !this.mes08.isEmpty()) {
			this.mes = Constantes.PAINEL_AGOSTO;
			this.valor = this.mes08;
		}
		this.mes09 = to.getMes09();
		if (this.mes09 != null && !this.mes09.isEmpty()) {
			this.mes = Constantes.PAINEL_SETEMBRO;
			this.valor = this.mes09;
		}
		this.mes10 = to.getMes10();
		if (this.mes10 != null && !this.mes10.isEmpty()) {
			this.mes = Constantes.PAINEL_OUTUBRO;
			this.valor = this.mes10;
		}
		this.mes11 = to.getMes11();
		if (this.mes11 != null && !this.mes11.isEmpty()) {
			this.mes = Constantes.PAINEL_NOVEMBRO;
			this.valor = this.mes11;
		}
		this.mes12 = to.getMes12();
		if (this.mes12 != null && !this.mes12.isEmpty()) {
			this.mes = Constantes.PAINEL_DEZEMBRO;
			this.valor = this.mes12;
		}
	}

	public void setMeses(IndicadorValorTO to) {

		if (to.getTotal() != null && !to.getTotal().isEmpty()) {
			this.mes = Constantes.PAINEL_TOTAL;
			this.valor = to.getTotal();
		}

		this.mes01 = to.getMes01();
		if (this.mes01 != null && !this.mes01.isEmpty()) {
			this.mes = Constantes.PAINEL_JANEIRO;
			this.valor = this.mes01;
		}
		this.mes02 = to.getMes02();
		if (this.mes02 != null && !this.mes02.isEmpty()) {
			this.mes = Constantes.PAINEL_FEVEREIRO;
			this.valor = this.mes02;
		}
		this.mes03 = to.getMes03();
		if (this.mes03 != null && !this.mes03.isEmpty()) {
			this.mes = Constantes.PAINEL_MARCO;
			this.valor = this.mes03;
		}
		this.mes04 = to.getMes04();
		if (this.mes04 != null && !this.mes04.isEmpty()) {
			this.mes = Constantes.PAINEL_ABRIL;
			this.valor = this.mes04;
		}
		this.mes05 = to.getMes05();
		if (this.mes05 != null && !this.mes05.isEmpty()) {
			this.mes = Constantes.PAINEL_MAIO;
			this.valor = this.mes05;
		}
		this.mes06 = to.getMes06();
		if (this.mes06 != null && !this.mes06.isEmpty()) {
			this.mes = Constantes.PAINEL_JUNHO;
			this.valor = this.mes06;
		}
		this.mes07 = to.getMes07();
		if (this.mes07 != null && !this.mes07.isEmpty()) {
			this.mes = Constantes.PAINEL_JULHO;
			this.valor = this.mes07;
		}
		this.mes08 = to.getMes08();
		if (this.mes08 != null && !this.mes08.isEmpty()) {
			this.mes = Constantes.PAINEL_AGOSTO;
			this.valor = this.mes08;
		}
		this.mes09 = to.getMes09();
		if (this.mes09 != null && !this.mes09.isEmpty()) {
			this.mes = Constantes.PAINEL_SETEMBRO;
			this.valor = this.mes09;
		}
		this.mes10 = to.getMes10();
		if (this.mes10 != null && !this.mes10.isEmpty()) {
			this.mes = Constantes.PAINEL_OUTUBRO;
			this.valor = this.mes10;
		}
		this.mes11 = to.getMes11();
		if (this.mes11 != null && !this.mes11.isEmpty()) {
			this.mes = Constantes.PAINEL_NOVEMBRO;
			this.valor = this.mes11;
		}
		this.mes12 = to.getMes12();
		if (this.mes12 != null && !this.mes12.isEmpty()) {
			this.mes = Constantes.PAINEL_DEZEMBRO;
			this.valor = this.mes12;
		}
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getTipoCalculo() {
		return tipoCalculo;
	}

	public void setTipoCalculo(String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getPrestadora() {
		return prestadora;
	}

	public void setPrestadora(String prestadora) {
		this.prestadora = prestadora;
	}

	public String getMes01() {
		return mes01;
	}

	public void setMes01(String mes01) {
		this.mes01 = mes01;
	}

	public String getMes02() {
		return mes02;
	}

	public void setMes02(String mes02) {
		this.mes02 = mes02;
	}

	public String getMes03() {
		return mes03;
	}

	public void setMes03(String mes03) {
		this.mes03 = mes03;
	}

	public String getMes04() {
		return mes04;
	}

	public void setMes04(String mes04) {
		this.mes04 = mes04;
	}

	public String getMes05() {
		return mes05;
	}

	public void setMes05(String mes05) {
		this.mes05 = mes05;
	}

	public String getMes06() {
		return mes06;
	}

	public void setMes06(String mes06) {
		this.mes06 = mes06;
	}

	public String getMes07() {
		return mes07;
	}

	public void setMes07(String mes07) {
		this.mes07 = mes07;
	}

	public String getMes08() {
		return mes08;
	}

	public void setMes08(String mes08) {
		this.mes08 = mes08;
	}

	public String getMes09() {
		return mes09;
	}

	public void setMes09(String mes09) {
		this.mes09 = mes09;
	}

	public String getMes10() {
		return mes10;
	}

	public void setMes10(String mes10) {
		this.mes10 = mes10;
	}

	public String getMes11() {
		return mes11;
	}

	public void setMes11(String mes11) {
		this.mes11 = mes11;
	}

	public String getMes12() {
		return mes12;
	}

	public void setMes12(String mes12) {
		this.mes12 = mes12;
	}

	@Override
	public String toString() {
		return "PainelJSON [sigla=" + sigla + ", valor=" + valor + ", unidade=" + unidade + ", tipoCalculo="
				+ tipoCalculo + ", mes=" + mes + ", ano=" + ano + ", agencia=" + agencia + ", municipio=" + municipio
				+ ", prestadora=" + prestadora + ", mes01=" + mes01 + ", mes02=" + mes02 + ", mes03=" + mes03
				+ ", mes04=" + mes04 + ", mes05=" + mes05 + ", mes06=" + mes06 + ", mes07=" + mes07 + ", mes08=" + mes08
				+ ", mes09=" + mes09 + ", mes10=" + mes10 + ", mes11=" + mes11 + ", mes12=" + mes12 + "]";
	}
}
