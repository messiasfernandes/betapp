package br.com.bepapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bepapp.domain.dao.DaoVariacoes;
import br.com.bepapp.domain.entidade.Variacoes;

@Service
public class ServiceVariacoes implements ServiceModel<Variacoes> {
	@Autowired
	private DaoVariacoes daoVariacoes;

	@Override
	public Page<Variacoes> buscar(String nome, Pageable pageable) {
		
		return daoVariacoes.buscar(nome, pageable);
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Variacoes buccarporid(Long id) {
	
		return daoVariacoes.findById(id).get();
	}

	@Override
	public Variacoes salvar(Variacoes objeto) {
		
	objeto.setDescricao(objeto.getGrade().getDescricao() + "| "+concatenar(objeto));
	objeto.getAtributo().forEach(c -> c.setVariacoes(objeto));
		return daoVariacoes.save(objeto);
	}

	private String concatenar(Variacoes objeto) {
		StringBuilder strBuilder = new StringBuilder();
        var tam =objeto.getAtributo().size()-1;
   
	    for (int i=0; i< objeto.getAtributo().size();i++) {
	    
	    if(i==tam) {
	    	strBuilder.append(objeto.getAtributo().get(i).getTipo().concat(" : "));
	    	strBuilder.append(objeto.getAtributo().get(i).getValor().concat(" "));
	    	
	    }else {
	    	
	    	strBuilder.append(objeto.getAtributo().get(i).getTipo().concat(" :"));
	    	strBuilder.append(objeto.getAtributo().get(i).getValor().concat(" | "));
	    }
	    	
	    }

	

		return strBuilder.toString();

	}

}
