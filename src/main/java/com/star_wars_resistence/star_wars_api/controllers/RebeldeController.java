package com.star_wars_resistence.star_wars_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star_wars_resistence.star_wars_api.repository.LocalizacaoRepository;
import com.star_wars_resistence.star_wars_api.repository.RebeldeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.star_wars_resistence.star_wars_api.exceptions.IventarioException;
import com.star_wars_resistence.star_wars_api.model.Iventario;
import com.star_wars_resistence.star_wars_api.model.Localizacao;
import com.star_wars_resistence.star_wars_api.model.Rebelde;

import java.util.List;

import javax.validation.Valid;



@RestController
@RequestMapping("/api/rebelde")
@Api(value = "API Rebeldes")
@CrossOrigin(origins = "*")
public class RebeldeController {
	
	@Autowired
	RebeldeRepository rebeldeRepository;
	
	@Autowired
	LocalizacaoRepository localizacaoRepository;
	
	@GetMapping
	public List<Rebelde> listaRebelde(){
		return rebeldeRepository.findAll();
	}
	
	@PostMapping
	@ApiOperation(value="Recebe um rebelde, salva na api e retorna o rebelde salvo.")
	public Rebelde saveRebelde(@Valid @RequestBody Rebelde rebelde) {
		this.validarRebelde(rebelde);
		return rebeldeRepository.save(rebelde);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Recebe uma localização, atualiza a localização do rebelde dono do id passado na URL e retorna a utima localização.")
	public Localizacao atualizarLocalizacao(@Valid @RequestBody Localizacao localizacao,
			@PathVariable(value="id") long id) {
		Localizacao local = rebeldeRepository.findById(id).get().getLocalizacao();
		Localizacao ultimoLocal = new Localizacao();
		ultimoLocal.setGalaxia(local.getGalaxia());
		ultimoLocal.setLatitude(local.getLatitude());
		ultimoLocal.setLongitude(local.getLongitude());
		localizacao.setId(local.getId());
		localizacaoRepository.save(localizacao);
		return ultimoLocal;
	}
	
	@PatchMapping("/{id}")
	@ApiOperation(value="Adiciona uma denuncia ao rebelde referente ao id passado na URL")
	public String denunciar(@PathVariable(value = "id") long id) {
		Rebelde rebelde = rebeldeRepository.findById(id).get();
		rebelde.receberDenuncia();
		rebeldeRepository.save(rebelde);
		return "denuncia aceita.";
	}
	
	public void itemRepitido(Iventario iventario) {
		
		for(int i=0;i<iventario.getItens().size();i++) {
			int count = 0;
			for(int e=0;e<iventario.getItens().size();e++) {
				if(iventario.getItens().get(i).getNome().equals(
						iventario.getItens().get(e).getNome())) {
					count++;
				}
				if(count >= 2) {
					throw new IventarioException("Existem itens repitidos em seu iventario,"
							+ " cada tipo de item pode ocupar apenas um espaço no iventario.");
				}
			}
		}
	}
	
	public void validarRebelde(Rebelde rebelde) {
		this.itemRepitido(rebelde.getIventario());
	}
}
