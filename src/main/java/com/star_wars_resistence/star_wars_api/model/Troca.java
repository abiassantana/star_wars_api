package com.star_wars_resistence.star_wars_api.model;

import java.util.List;

import javax.validation.constraints.NotNull;

public class Troca {

	@NotNull(message = "Necessario preencher o campo rebelde1.")
	private long rebelde1;
	@NotNull(message = "Necessario preencher o campo rebelde2.")
	private long rebelde2;
	@NotNull(message = "Necessario preencher o campo itensTroca1.")
	private List<Item> itensTroca1;
	@NotNull(message = "Necessario preencher o campo itensTroca2.")
	private List<Item> itensTroca2;
	
	public long getRebelde1() {
		return rebelde1;
	}
	public void setRebelde1(long rebelde1) {
		this.rebelde1 = rebelde1;
	}
	public long getRebelde2() {
		return rebelde2;
	}
	public void setRebelde2(long rebelde2) {
		this.rebelde2 = rebelde2;
	}
	public List<Item> getItensTroca1() {
		return itensTroca1;
	}
	public void setItensTroca1(List<Item> itensTroca1) {
		this.itensTroca1 = itensTroca1;
	}
	public List<Item> getItensTroca2() {
		return itensTroca2;
	}
	public void setItensTroca2(List<Item> itensTroca2) {
		this.itensTroca2 = itensTroca2;
	}	
	
	
	
}
