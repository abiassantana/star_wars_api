package com.star_wars_resistence.star_wars_api.controllers;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.star_wars_resistence.star_wars_api.model.Genero;
import com.star_wars_resistence.star_wars_api.model.Item;
import com.star_wars_resistence.star_wars_api.model.Iventario;
import com.star_wars_resistence.star_wars_api.model.Localizacao;
import com.star_wars_resistence.star_wars_api.model.Rebelde;
import com.star_wars_resistence.star_wars_api.repository.RebeldeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RebeldeControllerTest {
	
	@Autowired
	RebeldeRepository rebeldeRepository;
	
	@Autowired
	RebeldeController rebeldeController;
	
	@Test
	public void testeSalvarRebelde() {
		Rebelde rebelde = new Rebelde();
		Localizacao localizacao = new Localizacao();
		localizacao.setGalaxia("Via Lactia");
		localizacao.setLatitude("aaaaaaaa");
		localizacao.setLongitude("bbbbbbb");
		Item item = new Item();
		item.setNome("comida");
		item.setPonto(1);
		item.setQuantidade(5);
		Iventario iventario = new Iventario();
		iventario.setItens(new ArrayList<Item>());
		iventario.getItens().add(item);
		rebelde.setNome("abias");
		rebelde.setLocalizacao(localizacao);
		rebelde.setIventario(iventario);
		rebelde.setGenero(Genero.MASCULINO);
		rebelde.setIdade(25);
		Rebelde rebeldeSalvo = rebeldeRepository.save(rebelde);
		assertEquals(rebelde, rebeldeSalvo);
		
	}
	
	@Test
	public void testeAtualizarLocalizacao() {
		Localizacao localizacao = new Localizacao();
		localizacao.setGalaxia("Adromeda");
		localizacao.setLatitude("aaaaaaaa");
		localizacao.setLongitude("bbbbbbb");
		rebeldeController.atualizarLocalizacao(localizacao, 1);
		Rebelde rebelde = rebeldeRepository.findById((long) 1).get();
		localizacao.setId(rebelde.getLocalizacao().getId());
		assertEquals(rebelde.getLocalizacao().getGalaxia(), localizacao.getGalaxia());
	}
	
	
	public void testenegociacao() {
		Rebelde rebelde = new Rebelde();
		Localizacao localizacao = new Localizacao();
		localizacao.setGalaxia("Via Lactia");
		localizacao.setLatitude("aaaaaaaa");
		localizacao.setLongitude("bbbbbbb");
		Item item = new Item();
		item.setNome("faca");
		item.setPonto(1);
		item.setQuantidade(5);
		Iventario iventario = new Iventario();
		iventario.setItens(new ArrayList<Item>());
		iventario.getItens().add(item);
		rebelde.setNome("danilo");
		rebelde.setLocalizacao(localizacao);
		rebelde.setIventario(iventario);
		rebelde.setGenero(Genero.MASCULINO);
		rebelde.setIdade(25);
		Rebelde rebeldeSalvo = rebeldeRepository.save(rebelde);
		List<Rebelde> rebeldes = rebeldeRepository.findAll();
		
		
	}

	
	
}
