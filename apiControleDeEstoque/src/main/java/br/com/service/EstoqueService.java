package br.com.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.model.entity.Produtos;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.repository.ProdutoRepository;
import springfox.documentation.annotations.ApiIgnore;

@Service
public class EstoqueService {
	
	@Autowired
	ProdutoRepository repository;
	
	public ResponseEntity<ResponseRest> baixaEstoque(Long codigo, Long quantidade, @ApiIgnore @Valid Produtos produto, @ApiIgnore ResponseRest response) {

		if(validaSeExisteId(codigo) == false) {
			response.setMessage("O código informado não existe");
        	response.setType(messageType.ATENCAO);    	
        	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (verificaQuantidade(codigo) - (produto.getQuantidade()) < 0) {
			response.setMessage("Quantidade requsitada é maior que o estoque. Estoque: " + verificaQuantidade(codigo));
			response.setType(messageType.ATENCAO);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(quantidade < 0) {
			response.setMessage("O valor da quantidade não pode ser negativa.");
        	response.setType(messageType.ATENCAO);    	
        	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		produto.setQuantidade(verificaQuantidade(codigo) - (produto.getQuantidade()));
		alteraQuantidade(produto, produto.getQuantidade(), codigo);
		response.setMessage("Baixa realizada com sucesso. estoque:" + produto.getQuantidade() );
		response.setType(messageType.SUCESSO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseRest> adicionaEstoque(Long codigo, Long quantidade, @ApiIgnore @Valid Produtos produto, @ApiIgnore ResponseRest response) {
		
		if(validaSeExisteId(codigo) == false) {
			response.setMessage("O código informado não existe");
        	response.setType(messageType.ATENCAO);    	
        	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(quantidade < 0) {
			response.setMessage("O valor da quantidade não pode ser negativa.");
        	response.setType(messageType.ATENCAO);    	
        	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		produto.setQuantidade(verificaQuantidade(codigo) + (produto.getQuantidade()));
		alteraQuantidade(produto, produto.getQuantidade(), codigo);
		response.setMessage("Entrada realizada com sucesso. estoque:" + produto.getQuantidade());
		response.setType(messageType.SUCESSO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public Long verificaQuantidade(Long id) {
		Optional<Produtos> produto = repository.findById(id);
		if (produto != null) {
			return produto.get().getQuantidade();
		}
		return null;
	}

	public Long verificaProduto(Long id) {
		Optional<Produtos> produto = repository.findById(id);
		if (produto != null) {
			return produto.get().getQuantidade();
		}
		return null;
	}
	
	public Boolean validaSeExisteId(Long id) {
		Optional<Produtos> produtos = repository.findById(id);
		try {
		if(produtos.get().getCodigo() != null) {
	     return true;
		}
		}catch(Exception e) {
		return false;
		}
		return false;
	}

	public Produtos alteraQuantidade(Produtos produto, Long quantidade, Long id) {
		Optional<Produtos> ProdutoCadastrado = repository.findById(id);
		produto.setCategoria(ProdutoCadastrado.get().getCategoria());
		produto.setCodigo(ProdutoCadastrado.get().getCodigo());
		produto.setDescricao(ProdutoCadastrado.get().getDescricao());
		produto.setNome(ProdutoCadastrado.get().getNome());
		produto.setQuantidade(quantidade);
		produto.setValor(ProdutoCadastrado.get().getValor());
		return repository.save(produto);
	}

}
