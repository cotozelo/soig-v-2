package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class IndicadorValor {

	@Id
	private String id;
	private TipoCalculo tipo;
	private String sigla;
	private String confiabilidade;
	private String exatidao;
	private String nota;
	private int versaoGlobal;
	private String paiId;
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
	private String total;
	private String mes01Justificativa;
	private String mes02Justificativa;
	private String mes03Justificativa;
	private String mes04Justificativa;
	private String mes05Justificativa;
	private String mes06Justificativa;
	private String mes07Justificativa;
	private String mes08Justificativa;
	private String mes09Justificativa;
	private String mes10Justificativa;
	private String mes11Justificativa;
	private String mes12Justificativa;
	private String totalJustificativa;

	public IndicadorValor() {
		super();
	}

	public IndicadorValor(IndicadorValor indicadorValor) {
		super();
		this.setId(indicadorValor.getId());
		this.setTipo(indicadorValor.getTipo());
		this.setSigla(indicadorValor.getSigla());
		this.setConfiabilidade(indicadorValor.getConfiabilidade());
		this.setExatidao(indicadorValor.getExatidao());
		this.setNota(indicadorValor.getNota());
		this.setVersaoGlobal(indicadorValor.getVersaoGlobal());
		this.setPaiId(indicadorValor.getPaiId());
		this.setMes01(indicadorValor.getMes01());
		this.setMes02(indicadorValor.getMes02());
		this.setMes03(indicadorValor.getMes03());
		this.setMes04(indicadorValor.getMes04());
		this.setMes05(indicadorValor.getMes05());
		this.setMes06(indicadorValor.getMes06());
		this.setMes07(indicadorValor.getMes07());
		this.setMes08(indicadorValor.getMes08());
		this.setMes09(indicadorValor.getMes09());
		this.setMes10(indicadorValor.getMes10());
		this.setMes11(indicadorValor.getMes11());
		this.setMes12(indicadorValor.getMes12());
		this.setTotal(indicadorValor.getTotal());
		this.setMes01Justificativa(indicadorValor.getMes01Justificativa());
		this.setMes02Justificativa(indicadorValor.getMes02Justificativa());
		this.setMes03Justificativa(indicadorValor.getMes03Justificativa());
		this.setMes04Justificativa(indicadorValor.getMes04Justificativa());
		this.setMes05Justificativa(indicadorValor.getMes05Justificativa());
		this.setMes06Justificativa(indicadorValor.getMes06Justificativa());
		this.setMes07Justificativa(indicadorValor.getMes07Justificativa());
		this.setMes08Justificativa(indicadorValor.getMes08Justificativa());
		this.setMes09Justificativa(indicadorValor.getMes09Justificativa());
		this.setMes10Justificativa(indicadorValor.getMes10Justificativa());
		this.setMes11Justificativa(indicadorValor.getMes11Justificativa());
		this.setMes12Justificativa(indicadorValor.getMes12Justificativa());
		this.setMes12Justificativa(indicadorValor.getTotalJustificativa());
	}

	public String getId() {
		if (this.id == null || this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return this.id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMes01Justificativa() {
		return mes01Justificativa;
	}

	public void setMes01Justificativa(String mes01Justificativa) {
		this.mes01Justificativa = mes01Justificativa;
	}

	public String getMes02Justificativa() {
		return mes02Justificativa;
	}

	public void setMes02Justificativa(String mes02Justificativa) {
		this.mes02Justificativa = mes02Justificativa;
	}

	public String getMes03Justificativa() {
		return mes03Justificativa;
	}

	public void setMes03Justificativa(String mes03Justificativa) {
		this.mes03Justificativa = mes03Justificativa;
	}

	public String getMes04Justificativa() {
		return mes04Justificativa;
	}

	public void setMes04Justificativa(String mes04Justificativa) {
		this.mes04Justificativa = mes04Justificativa;
	}

	public String getMes05Justificativa() {
		return mes05Justificativa;
	}

	public void setMes05Justificativa(String mes05Justificativa) {
		this.mes05Justificativa = mes05Justificativa;
	}

	public String getMes06Justificativa() {
		return mes06Justificativa;
	}

	public void setMes06Justificativa(String mes06Justificativa) {
		this.mes06Justificativa = mes06Justificativa;
	}

	public String getMes07Justificativa() {
		return mes07Justificativa;
	}

	public void setMes07Justificativa(String mes07Justificativa) {
		this.mes07Justificativa = mes07Justificativa;
	}

	public String getMes08Justificativa() {
		return mes08Justificativa;
	}

	public void setMes08Justificativa(String mes08Justificativa) {
		this.mes08Justificativa = mes08Justificativa;
	}

	public String getMes09Justificativa() {
		return mes09Justificativa;
	}

	public void setMes09Justificativa(String mes09Justificativa) {
		this.mes09Justificativa = mes09Justificativa;
	}

	public String getMes10Justificativa() {
		return mes10Justificativa;
	}

	public void setMes10Justificativa(String mes10Justificativa) {
		this.mes10Justificativa = mes10Justificativa;
	}

	public String getMes11Justificativa() {
		return mes11Justificativa;
	}

	public void setMes11Justificativa(String mes11Justificativa) {
		this.mes11Justificativa = mes11Justificativa;
	}

	public String getMes12Justificativa() {
		return mes12Justificativa;
	}

	public void setMes12Justificativa(String mes12Justificativa) {
		this.mes12Justificativa = mes12Justificativa;
	}

	public String getTotalJustificativa() {
		return totalJustificativa;
	}

	public void setTotalJustificativa(String totalJustificativa) {
		this.totalJustificativa = totalJustificativa;
	}

	public TipoCalculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoCalculo tipo) {
		this.tipo = tipo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getVersaoGlobal() {
		return versaoGlobal;
	}

	public void setVersaoGlobal(int versaoGlobal) {
		this.versaoGlobal = versaoGlobal;
	}

	public String getPaiId() {
		return paiId;
	}

	public void setPaiId(String paiId) {
		this.paiId = paiId;
	}

	public String getConfiabilidade() {
		return confiabilidade;
	}

	public void setConfiabilidade(String confiabilidade) {
		this.confiabilidade = confiabilidade;
	}

	public String getExatidao() {
		return exatidao;
	}

	public void setExatidao(String exatidao) {
		this.exatidao = exatidao;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "IndicadorValor [id=" + id + ", tipo=" + tipo + ", sigla=" + sigla + ", confiabilidade=" + confiabilidade
				+ ", exatidao=" + exatidao + ", nota=" + nota + ", versaoGlobal=" + versaoGlobal + ", pai_id=" + paiId
				+ ", mes01=" + mes01 + ", mes02=" + mes02 + ", mes03=" + mes03 + ", mes04=" + mes04 + ", mes05=" + mes05
				+ ", mes06=" + mes06 + ", mes07=" + mes07 + ", mes08=" + mes08 + ", mes09=" + mes09 + ", mes10=" + mes10
				+ ", mes11=" + mes11 + ", mes12=" + mes12 + ", total=" + total + ", mes01Justificativa="
				+ mes01Justificativa + ", mes02Justificativa=" + mes02Justificativa + ", mes03Justificativa="
				+ mes03Justificativa + ", mes04Justificativa=" + mes04Justificativa + ", mes05Justificativa="
				+ mes05Justificativa + ", mes06Justificativa=" + mes06Justificativa + ", mes07Justificativa="
				+ mes07Justificativa + ", mes08Justificativa=" + mes08Justificativa + ", mes09Justificativa="
				+ mes09Justificativa + ", mes10Justificativa=" + mes10Justificativa + ", mes11Justificativa="
				+ mes11Justificativa + ", mes12Justificativa=" + mes12Justificativa + ", totalJustificativa="
				+ totalJustificativa + "]";
	}
	
	public void setMes(String mes, String valor) {
		switch (mes) {
		case "01":
			setMes01(valor);
			break;
		case "02":
			setMes02(valor);
			break;
		case "03":
			setMes03(valor);
			break;
		case "04":
			setMes04(valor);
			break;
		case "05":
			setMes05(valor);
			break;
		case "06":
			setMes06(valor);
			break;
		case "07":
			setMes07(valor);
			break;
		case "08":
			setMes08(valor);
			break;
		case "09":
			setMes09(valor);
			break;
		case "10":
			setMes10(valor);
			break;
		case "11":
			setMes11(valor);
			break;
		default:
			setMes12(valor);
			break;
		}
	}

}
