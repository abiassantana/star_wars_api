package com.star_wars_resistence.star_wars_api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_IVENTARIO")
public class Iventario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TB_IVENTARIO_ITENS", 
    	joinColumns = { @JoinColumn(name = "IVENTARIO_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "ITENS_ID") })
	@NotNull(message = "Necessario preencher o campo itens.")
	private List<Item> itens;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	

}
