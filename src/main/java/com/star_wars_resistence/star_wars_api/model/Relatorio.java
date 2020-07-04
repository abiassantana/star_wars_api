package com.star_wars_resistence.star_wars_api.model;

import java.util.HashMap;

public class Relatorio {
	
	private double porcentagemTraidores;
	private double porcentagemRebeldes;
	private HashMap<String, Double> mediaRecursos;
	private double pontosPerdido;
	
	public double getPorcentagemTraidores() {
		return porcentagemTraidores;
	}
	public void setPorcentagemTraidores(double porcentagemTraidores) {
		this.porcentagemTraidores = porcentagemTraidores;
	}
	public double getPorcentagemRebeldes() {
		return porcentagemRebeldes;
	}
	public void setPorcentagemRebeldes(double porcentagemRebeldes) {
		this.porcentagemRebeldes = porcentagemRebeldes;
	}
	public HashMap<String, Double> getMediaRecursos() {
		return mediaRecursos;
	}
	public void setMediaRecursos(HashMap<String, Double> mediaRecursos) {
		this.mediaRecursos = mediaRecursos;
	}
	public double getPontosPerdido() {
		return pontosPerdido;
	}
	public void setPontosPerdido(double pontosPerdido) {
		this.pontosPerdido = pontosPerdido;
	}
	
	
	

}
