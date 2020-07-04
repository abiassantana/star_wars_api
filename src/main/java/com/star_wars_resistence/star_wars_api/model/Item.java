package com.star_wars_resistence.star_wars_api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="TB_ITEM")
public class Item implements Serializable{
		
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Necessario preencher o campo nome.")
	private String nome;
	@NotNull(message = "Necessario preencher o campo ponto.")
	private int ponto;
	@NotNull(message = "Necessario preencher o campo quantidade.")
	private int quantidade;
	

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getPonto() {
		return ponto;
	}
	public void setPonto(int ponto) {
		this.ponto = ponto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
