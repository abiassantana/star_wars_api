package com.star_wars_resistence.star_wars_api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_REBELDE")
public class Rebelde {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Necessario preencher o campo nome.")
	private String nome;
	@NotNull(message = "Necessario preencher o campo idade.")
	private int idade;
	@NotNull(message = "Necessario preencher o campo genero.")
	private Genero genero;
	@NotNull(message = "Necessario preencher o campo localizacao.")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Localizacao localizacao;
	@NotNull(message = "Necessario preencher o campo iventario.")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Iventario iventario;
	private int countDenuncia;
	
	public void receberDenuncia() {
		this.countDenuncia++;
	}
	
	public boolean isTraidor() {
		if (this.countDenuncia >= 3) {
			return true;
		}
		return false;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	public Iventario getIventario() {
		return iventario;
	}
	public void setIventario(Iventario iventario) {
		this.iventario = iventario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
