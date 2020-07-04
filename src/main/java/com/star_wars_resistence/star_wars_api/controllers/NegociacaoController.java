package com.star_wars_resistence.star_wars_api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star_wars_resistence.star_wars_api.exceptions.IventarioException;
import com.star_wars_resistence.star_wars_api.exceptions.PontuacaoException;
import com.star_wars_resistence.star_wars_api.exceptions.TraidorException;
import com.star_wars_resistence.star_wars_api.model.Item;
import com.star_wars_resistence.star_wars_api.model.Iventario;
import com.star_wars_resistence.star_wars_api.model.Rebelde;
import com.star_wars_resistence.star_wars_api.model.Troca;
import com.star_wars_resistence.star_wars_api.repository.ItemRepository;
import com.star_wars_resistence.star_wars_api.repository.IventarioRepository;
import com.star_wars_resistence.star_wars_api.repository.RebeldeRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/negociar")
public class NegociacaoController {

	@Autowired
	IventarioRepository iventariorepository;
	
	@Autowired
	RebeldeRepository rebelderepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@PostMapping
	@Transactional
	@ApiOperation(value="Recebe o id de dois rebeldes e duas lista lista de itens, que"
			+ " correspondem aos pertences de cada rebelde respectivamente, caso a "
			+ "transação esteja de acordo com as politicas rebeldes os itens são trocados.")
	public String trocar(@Valid @RequestBody Troca troca) {
		Rebelde rebelde1 = rebelderepository.findById(troca.getRebelde1()).get();
		Rebelde rebelde2 = rebelderepository.findById(troca.getRebelde2()).get();
		this.validarNegociacao(rebelde1, rebelde2, 
				troca.getItensTroca1(), troca.getItensTroca2());
		this.moverItens(rebelde1.getIventario(), rebelde2.getIventario(), 
				troca.getItensTroca1(), troca.getItensTroca2());
		iventariorepository.save(rebelde1.getIventario());
		iventariorepository.save(rebelde2.getIventario());
		return "Negociação efetuada com sucesso.";
		
	}
	private void moverItens(Iventario iventario1, Iventario iventario2,
			List<Item> itensTroca1, List<Item> itensTroca2) {
		
		this.removerItens(iventario2, itensTroca2);
		this.removerItens(iventario1, itensTroca1);
		this.addIventario(iventario1, itensTroca2);
		this.addIventario(iventario2, itensTroca1);

	
			
	}
	private Iventario addIventario(Iventario iventario, List<Item> itensTroca) {
		for (int i=0; i<itensTroca.size(); i++) {
			if(!this.existInList(iventario.getItens(), itensTroca.get(i))) {
				Item item = new Item();
				item.setNome(itensTroca.get(i).getNome());
				item.setPonto(itensTroca.get(i).getPonto());
				item.setQuantidade(0);
				iventario.getItens().add(item);
			}
			
			for (int e=0; e<iventario.getItens().size(); e++) {
				if(iventario.getItens().get(e).getNome().equals(itensTroca.get(i).getNome())) {
					iventario.getItens().get(e).setQuantidade(iventario.getItens().get(e).getQuantidade()+
							itensTroca.get(i).getQuantidade());
				}
				
			}
			
		}return iventario;
	}
	
	private Iventario removerItens(Iventario iventario, List<Item> itensTroca){
			for (int i=0; i<itensTroca.size(); i++) {
				for (int e=0; e<iventario.getItens().size(); e++) {
					if(iventario.getItens().get(e).getId() == itensTroca.get(i).getId()) {
						iventario.getItens().get(e).setQuantidade(iventario.getItens().get(e).getQuantidade()-
								itensTroca.get(i).getQuantidade());
					}
				}
			}
		return iventario;
		
	}
	
	private boolean existInList(List<Item> lista, Item item) {
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getNome().equals(item.getNome())) {
				return true;
			}
		}
		return false;
	}
	private void haveItens(Iventario iventario1, Iventario iventario2,
			List<Item> itensTroca1, List<Item> itensTroca2) {
		for(int i=0;i<itensTroca1.size();i++) {
			if (!this.existInList(iventario1.getItens(), itensTroca1.get(i))){
				throw new IventarioException("O primeiro rebelde não possui todos os itens que pretende trocar.");
			}
			for(int e=0;e<iventario1.getItens().size();e++) {
				if (iventario1.getItens().get(e).getQuantidade() <= 0) {
					throw new IventarioException("O primeiro rebelde não possui todos os itens que pretende trocar.");
				}
			}
		}
		for(int i=0;i<itensTroca2.size();i++) {
			if (!this.existInList(iventario2.getItens(), itensTroca2.get(i))){
				throw new IventarioException("O segundo rebelde não possui todos os itens que pretende trocar.");
			}
			for(int e=0;e<iventario2.getItens().size();e++) {
				if (iventario2.getItens().get(e).getQuantidade() <= 0) {
					throw new IventarioException("O segundo rebelde não possui todos os itens que pretende trocar.");
				}
			}
		}
		
	}
	
	private void verifyTraidores(Rebelde rebelde1, Rebelde rebelde2) {
		if(rebelde1.isTraidor() || rebelde2.isTraidor()) {
			throw new TraidorException("Uma das partes da troca é um traidor.");
		}
	}
	
	private void verifyPontos(List<Item> itensTroca1, List<Item> itensTroca2) {
		int pontos1 = 0;
		int pontos2 = 0;
		for(int i=0;i<itensTroca1.size();i++) {
			pontos1 = pontos1+(itensTroca1.get(i).getPonto()*itensTroca1.get(i).getQuantidade());
		}
		for(int i=0;i<itensTroca2.size();i++) {
			pontos2 = pontos2+(itensTroca2.get(i).getPonto()*itensTroca2.get(i).getQuantidade());
		}
		if(pontos1 != pontos2) {
			throw new PontuacaoException("O total de bontos entre as duas partes devem ser iguais.");
		}
	}
	private void validarNegociacao(Rebelde rebelde1, Rebelde rebelde2,
			List<Item> itensTroca1, List<Item> itensTroca2) {
		this.verifyTraidores(rebelde1, rebelde2);
		this.haveItens(rebelde1.getIventario(), rebelde2.getIventario(), itensTroca1, itensTroca2);
		this.verifyPontos(itensTroca1, itensTroca2);
		
	}
	
	
}
