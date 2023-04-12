package br.com.bepapp;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bepapp.domain.dao.DaoCategoria;
import br.com.bepapp.domain.dao.DaoInSumo;
import br.com.bepapp.domain.dao.DaoProdutoVariacao;
import br.com.bepapp.domain.entidade.Atributo;
import br.com.bepapp.domain.entidade.Componente;
import br.com.bepapp.domain.entidade.Produto;
import br.com.bepapp.domain.entidade.ProdutoVariacoes;
import br.com.bepapp.domain.service.ServiceProduto;


@SpringBootTest
class BetAppApiApplicationTests {
//	@Autowired
//	private DaoProduto daoProduto;

///	private DaoCareacteristica daoCareacteristica;
//	Set<Atributo> lista = new HashSet<>();
	///Set<Pessoa> setPessoas = new HashSet<>();
///	Careteriticas careteriticas = new Careteriticas();
	@Autowired
	private DaoProdutoVariacao daoProdutoVariacao;
//	@Autowired
//	private DaoComponte daoComponte;
	@Autowired
	private DaoInSumo daoInSumo;
	@Autowired
	private ServiceProduto serviceProduto;
//	@Autowired
//	private ServiceVariacoes serviceVariacoes;
	@Autowired
	private DaoCategoria daoCategoria;

	void contextLoads() {
		ProdutoVariacoes produtoV = daoProdutoVariacao.findById(2L).get();
	//	ProdutoVaricaoes produtovar= daoProdutoVariacao.findById(2l).get();
		var insumo = daoInSumo.findById(1l).get();
		var componente = new Componente();
	componente.setProdutoVariacoes(produtoV);
	componente.setInsumo(insumo)
	;
	
	componente.setQte(new BigDecimal(12));
	System.out.println(insumo.getProdutoVariacoes().getQtde());
	produtoV.setQtde(insumo.getProdutoVariacoes().getQtde().divide(componente.getQte()));

  //  produtoV.getComponenentes().add(componente);
//	    Atributo atributo = new Atributo();
//	    atributo.setTipo("LITRO");
//	    atributo.setValor("350ml");
//	    atributo.setProdutoVaricaoes(produtoV);
//	    produtoV.getAtributos().add(atributo);
//	    Atributo atributo1 = new Atributo();
//	    atributo1.setTipo("MARCA");
//	    atributo1.setValor("SKOL");
//	    atributo1.setProdutoVaricaoes(produtoV);
//	    produtoV.getAtributos().add(atributo1);
     daoProdutoVariacao.save(produtoV);
	

	
	}

	public void adicionar(Atributo e) {

//		//if (!careteriticas.getAtributo().contains(e)) {
//			careteriticas.getAtributo().add(e);
//			 
//			
//			System.out.println( "Estado adicionado." + careteriticas.getAtributo().size());
//
//		//} else {
//			// lista.remove(e);
//			System.out.println("Esse estado já foi adicionado ao país anteriormente." + careteriticas.getAtributo().size());
//		
//	//	}

		

	}
	@Test
	void salvarproduto(){
		var produto = new Produto();
		var produtov = new ProdutoVariacoes();
		produto.setNomeproduto("camiseta bob esponja");
		var categoria = daoCategoria.findById(1l).get();
		produto.setCategoria(categoria);
		produto.setMarca("hering");
		produto.setUnidade("pc"); 
	//var variacao = serviceVariacoes.buccarporid(1l);
	//	produtov.setVariacoes(variacao);
		produtov.setCodigoEan13("1234555");
		produtov.setCustomedio(new BigDecimal(100));
		produtov.setControlarestoque(true);
		produtov.setPrecocusto(new BigDecimal(100));
		produtov.setPrecovenda(new BigDecimal(200));
		produtov.setSku("cami-12333");
		produto.getProdutovariacoes().add(produtov);
		serviceProduto.salvar(produto);
	}


}
