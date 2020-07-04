package com.star_wars_resistence.star_wars_api.controllers;

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
import com.star_wars_resistence.star_wars_api.model.Troca;
import com.star_wars_resistence.star_wars_api.repository.RebeldeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NegociacaoControllerTest {

	@Autowired
	RebeldeRepository rebeldeRepository;
	
	@Autowired
	NegociacaoController negociacaoController;
	

		
		
	
}
