package com.star_wars_resistence.star_wars_api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_LOCALIZACAO")
public class Localizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Necessario preencher o campo latitude.")
	private String latitude;
	@NotBlank(message = "Necessario preencher o campo longitude.")
	private String longitude;
	@NotBlank(message = "Necessario preencher o campo galaxia.")
	private String galaxia;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getGalaxia() {
		return galaxia;
	}
	public void setGalaxia(String galaxia) {
		this.galaxia = galaxia;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
