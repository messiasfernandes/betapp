package br.com.bepapp.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bepapp.domain.dao.DaoProdutoVariacao;
import br.com.bepapp.domain.entidade.ProdutoVariacoes;
import br.com.bepapp.query.ProdutoVariacaoSpec;

@Service
public class ServiceProdutoVaricacao extends ServiceFuncoes implements ServiceModel<ProdutoVariacoes> {
	@Autowired
	private DaoProdutoVariacao daoProdutoVariacao;

	@Override
	public Page<ProdutoVariacoes> buscar(String nome, Pageable pageable) {
		Page<ProdutoVariacoes> pagina = null;
		ProdutoVariacaoSpec produtoVariacaoSpec = new ProdutoVariacaoSpec();

		if ((ehnumero(nome) == true) && (qtdecaraceteres(nome) == 13)) {
			System.out.println("pasou aqui enmero codigo de barras");
			Long id = Sonumero(nome);
			pagina = daoProdutoVariacao.findAll(produtoVariacaoSpec.buscarEan(id), pageable);

		} else if ((ehnumero(nome) == true)) {
			System.out.println("pasou aqui enmero");
			Long id = Sonumero(nome);
			pagina = daoProdutoVariacao.buscaid(id, pageable);
					
					//daoProdutoVariacao.findAll(produtoVariacaoSpec.buscaid(id), pageable);
		}

		if ((!ehnumero(nome)) && (qtdecaraceteres(nome) >= 0)) {
			System.out.println("pasou aqui ");
			nome = maiuscula(nome);

		pagina = daoProdutoVariacao.buscar(nome, pageable);
	//pagina =daoProdutoVariacao.findAll(produtoVariacaoSpec.pesquisar(nome), pageable);
		}


		return pagina;
	}
	@Transactional
	@Override
	public void excluir(Long codigo) {
		System.err.println(codigo);
		daoProdutoVariacao.deleteById(codigo);

	}

	@Override
	public ProdutoVariacoes buccarporid(Long id) {
	
		return daoProdutoVariacao.findById(id).get();
	}

	@Transactional
	@Override
	public ProdutoVariacoes salvar(ProdutoVariacoes objeto) {
		
//		if (objeto.getComponenentes().size()>0){
//			objeto.getComponenentes().forEach(c -> c.setProdutoVariacoes(objeto));
//		}
      //  objeto.setCaracteristica(adicionarcareacetririca(objeto));
        
		return daoProdutoVariacao.save(objeto);
	}

//	private String adicionarcareacetririca(ProdutoVariacoes objeto) {
//		StringBuilder variacoes = new StringBuilder();
//		int j = 0;
//		for (int k = 0; k < objeto.getAtributos().size(); k++) {
//	
//		
//			j++;
//			if (j <= objeto.getAtributos().size()){
//				 variacoes.append(objeto.getAtributos().get(k).getTipo().concat(" "));
//				variacoes.append(objeto.getAtributos().get(k).getValor().concat(" | "));
//	System.err.println("passou");
//			
//
//		}else {
//			 variacoes.append(objeto.getAtributos().get(k).getTipo().concat(" "));
//			variacoes.append(objeto.getAtributos().get(k).getValor());
//		}
//		}
//	return	variacoes.toString();
//		
//	}

}
