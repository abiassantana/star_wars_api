package com.star_wars_resistence.star_wars_api.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star_wars_resistence.star_wars_api.model.Item;
import com.star_wars_resistence.star_wars_api.model.Rebelde;
import com.star_wars_resistence.star_wars_api.model.Relatorio;
import com.star_wars_resistence.star_wars_api.repository.RebeldeRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {
	
	@Autowired
	RebeldeRepository rebeldeRepository;
	
	@GetMapping
	@ApiOperation(value="Gera um relatorio com: Porcentagem de traidores." + 
			"Porcentagem de rebeldes." + 
			"Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde)." + 
			"Pontos perdidos devido a traidores.")
	public Relatorio gerarRelatorio() {
		Relatorio relatorio = new Relatorio();
		relatorio.setPorcentagemTraidores(this.calcProcentagemTraidores());
		relatorio.setPorcentagemRebeldes(this.calcPorcentagemRebeldes());
		relatorio.setPontosPerdido(this.pontosPerdidos());
		relatorio.setMediaRecursos(this.mediaItens());
		return relatorio;
	}
	public double calcProcentagemTraidores() {
		double totalTraidores = 0;	
		List<Rebelde> rebeldes = rebeldeRepository.findAll();
		for(int i=0;i<rebeldes.size();i++) {
			if(rebeldes.get(i).isTraidor()) {
				totalTraidores++;
			}

		}if(totalTraidores == 0 || rebeldes.size() == 0) {
			return 0;
		}return totalTraidores/((double)rebeldes.size()/100);
	}
	
	public double calcPorcentagemRebeldes() {
		double totalRebeldes = 0;	
		List<Rebelde> rebeldes = rebeldeRepository.findAll();
		for(int i=0;i<rebeldes.size();i++) {
			if(!rebeldes.get(i).isTraidor()) {
				totalRebeldes++;
			}

		}if(totalRebeldes == 0 || rebeldes.size() == 0) {
			return 0;
		}return totalRebeldes/((double) rebeldes.size()/100);
	}
	
	public double pontosPerdidos() {
		int pontos = 0;
		List<Rebelde> rebeldes = rebeldeRepository.findAll();
		for(int i=0;i<rebeldes.size();i++) {
			List<Item> itens = rebeldes.get(i).getIventario().getItens();
			if (rebeldes.get(i).isTraidor()){
				for(int e=0;e<itens.size();e++) {
					pontos = pontos+(itens.get(e).getPonto()*itens.get(e).getQuantidade());
				}
			}
			
		}return pontos;
	}
	
	public HashMap<String, Double> mediaItens(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		HashMap<String, Double> mapmedia = new HashMap<String, Double>();
		List<Rebelde> rebeldes = rebeldeRepository.findAll();
		for(int i=0;i<rebeldes.size();i++) {
			List<Item> itens = rebeldes.get(i).getIventario().getItens();
			for(int e=0;e<itens.size();e++) {
				if(map.containsKey(itens.get(e).getNome())) {
					map.put(itens.get(e).getNome(), map.get(itens.get(e).getNome())+
							itens.get(e).getQuantidade());
				}else {
					map.put(itens.get(e).getNome(), itens.get(e).getQuantidade());
				}
			}
			
		}
		int total = rebeldes.size();
		for (String key : map.keySet()) {
            double media = map.get(key)/total;
            mapmedia.put(key, media);
     }
	   return mapmedia;
	}
	
}
