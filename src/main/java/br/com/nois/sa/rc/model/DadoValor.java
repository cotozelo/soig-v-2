package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DadoValor")
public class DadoValor {

	@Id
	private String id;
	private String sigla;
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
	private int versaoGlobal;
	private String pai_id;

	public DadoValor() {
		super();
	}

	public DadoValor(String sigla) {
		super();
		this.id = ObjectId.get().toString();
		this.sigla = sigla;
	}

	public DadoValor(String sigla, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06,
			String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, String mesTotal,
			String mes01Justificativa, String mes02Justificativa, String mes03Justificativa, String mes04Justificativa,
			String mes05Justificativa, String mes06Justificativa, String mes07Justificativa, String mes08Justificativa,
			String mes09Justificativa, String mes10Justificativa, String mes11Justificativa, String mes12Justificativa,
			String mesTotalJustificativa, String pai_id) {
		super();
		this.id = ObjectId.get().toString();
		this.sigla = sigla;
		this.mes01 = mes01;
		this.mes02 = mes02;
		this.mes03 = mes03;
		this.mes04 = mes04;
		this.mes05 = mes05;
		this.mes06 = mes06;
		this.mes07 = mes07;
		this.mes08 = mes08;
		this.mes09 = mes09;
		this.mes10 = mes10;
		this.mes11 = mes11;
		this.mes12 = mes12;
		this.total = mesTotal;
		this.mes01Justificativa = mes01Justificativa;
		this.mes02Justificativa = mes02Justificativa;
		this.mes03Justificativa = mes03Justificativa;
		this.mes04Justificativa = mes04Justificativa;
		this.mes05Justificativa = mes05Justificativa;
		this.mes06Justificativa = mes06Justificativa;
		this.mes07Justificativa = mes07Justificativa;
		this.mes08Justificativa = mes08Justificativa;
		this.mes09Justificativa = mes09Justificativa;
		this.mes10Justificativa = mes10Justificativa;
		this.mes11Justificativa = mes11Justificativa;
		this.mes12Justificativa = mes12Justificativa;
		this.totalJustificativa = mesTotalJustificativa;
		this.pai_id = pai_id;
	}

	public DadoValor(String id, String sigla, String mes01, String mes02, String mes03, String mes04, String mes05,
			String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12,
			String mesTotal, String mes01Justificativa, String mes02Justificativa, String mes03Justificativa,
			String mes04Justificativa, String mes05Justificativa, String mes06Justificativa, String mes07Justificativa,
			String mes08Justificativa, String mes09Justificativa, String mes10Justificativa, String mes11Justificativa,
			String mes12Justificativa, String mesTotalJustificativa, int versaoGlobal, String pai_id) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.mes01 = mes01;
		this.mes02 = mes02;
		this.mes03 = mes03;
		this.mes04 = mes04;
		this.mes05 = mes05;
		this.mes06 = mes06;
		this.mes07 = mes07;
		this.mes08 = mes08;
		this.mes09 = mes09;
		this.mes10 = mes10;
		this.mes11 = mes11;
		this.mes12 = mes12;
		this.total = mesTotal;
		this.mes01Justificativa = mes01Justificativa;
		this.mes02Justificativa = mes02Justificativa;
		this.mes03Justificativa = mes03Justificativa;
		this.mes04Justificativa = mes04Justificativa;
		this.mes05Justificativa = mes05Justificativa;
		this.mes06Justificativa = mes06Justificativa;
		this.mes07Justificativa = mes07Justificativa;
		this.mes08Justificativa = mes08Justificativa;
		this.mes09Justificativa = mes09Justificativa;
		this.mes10Justificativa = mes10Justificativa;
		this.mes11Justificativa = mes11Justificativa;
		this.mes12Justificativa = mes12Justificativa;
		this.totalJustificativa = mesTotalJustificativa;
		this.versaoGlobal = versaoGlobal;
		this.pai_id = pai_id;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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

	public void setTotal(String mesTotal) {
		this.total = mesTotal;
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

	public void setMes12Justificativa(String mesTotalJustificativa) {
		this.mes12Justificativa = mesTotalJustificativa;
	}

	public void setTotalJustificativa(String totalJustificativa) {
		this.totalJustificativa = totalJustificativa;
	}

	public String getTotalJustificativa() {
		return totalJustificativa;
	}

	public int getVersaoGlobal() {
		return versaoGlobal;
	}

	public void setVersaoGlobal(int versaoGlobal) {
		this.versaoGlobal = versaoGlobal;
	}

	public String getPai_id() {
		return pai_id;
	}

	public void setPai_id(String pai_id) {
		this.pai_id = pai_id;
	}

	@Override
	public String toString() {
		return "DadoValor [id=" + id + ", dado_id=" + sigla + ", mes01=" + mes01 + ", mes02=" + mes02 + ", mes03="
				+ mes03 + ", mes04=" + mes04 + ", mes05=" + mes05 + ", mes06=" + mes06 + ", mes07=" + mes07 + ", mes08="
				+ mes08 + ", mes09=" + mes09 + ", mes10=" + mes10 + ", mes11=" + mes11 + ", mes12=" + mes12 + ", total="
				+ total + ", mes01Justificativa=" + mes01Justificativa + ", mes02Justificativa=" + mes02Justificativa
				+ ", mes03Justificativa=" + mes03Justificativa + ", mes04Justificativa=" + mes04Justificativa
				+ ", mes05Justificativa=" + mes05Justificativa + ", mes06Justificativa=" + mes06Justificativa
				+ ", mes07Justificativa=" + mes07Justificativa + ", mes08Justificativa=" + mes08Justificativa
				+ ", mes09Justificativa=" + mes09Justificativa + ", mes10Justificativa=" + mes10Justificativa
				+ ", mes11Justificativa=" + mes11Justificativa + ", mes12Justificativa=" + mes12Justificativa
				+ ", mesTotalJustificativa=" + totalJustificativa + ", versaoGlobal=" + versaoGlobal + ", pai_id="
				+ pai_id + "]";
	}

}
