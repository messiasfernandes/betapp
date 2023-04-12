package br.com.bepapp.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bepapp.domain.dao.DaoProduto;
import br.com.bepapp.domain.entidade.Produto;
import br.com.bepapp.domain.service.exeption.EntidadeEmUsoExeption;
import br.com.bepapp.domain.service.exeption.RegistroNaoEncontrado;

@Service
public class ServiceProduto extends ServiceFuncoes implements ServiceModel<Produto> {
	@Autowired
	private DaoProduto daoProduto;

	@Override
	public Page<Produto> buscar(String nome, Pageable pageable) {

		return daoProduto.buscar(nome.toUpperCase(), pageable);
	}

	@Transactional()
	@Override
	public void excluir(Long codigo) {
		try {
			daoProduto.deleteById(codigo);
			daoProduto.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontrado("Produto não encotrado ");
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					"Operação não permitida!! Este registro pode estar asssociado a outra tabela");
		}

	}

	@Override
	public Produto buccarporid(Long id) {

		return daoProduto.findById(id).get();
	}

	@Transactional
	@Override
	public Produto salvar(Produto objeto) {
	
			
//		System.out.println(	objeto.getProdutovariacoes().size());
//		System.out.println("paasou aruin no service");
//			//objeto.getProdutovariacoes().forEach(p -> p.setProduto(objeto));
//			for(ProdutoVariacoes p : objeto.getProdutovariacoes()) {
//				//System.out.println(p.getVariacoes().getDescricao());
//				p.setProduto(objeto);
//				daoProdutoVariacao.save(p);
//			}
			objeto.getProdutovariacoes().forEach(p-> p.setProduto(objeto));
		
		
		return daoProduto.save(objeto);
	}

}
