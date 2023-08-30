package br.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.model.entity.Produtos;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.repository.ProdutoRepository;
import springfox.documentation.annotations.ApiIgnore;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

    public ResponseEntity<?> cadastraPorduto(@RequestBody @Valid Produtos produto, @ApiIgnore ResponseRest response) {
		
		if(validaSeExisteId(produto.getCodigo())){
			response.setMessage("Id já cadastrado.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}

    public ResponseEntity<?> atualizaProduto(@RequestBody @Valid Produtos produto, @ApiIgnore ResponseRest response) {
		
		if(!validaSeExisteId(produto.getCodigo())){
			response.setMessage("Registro não existente.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}

    public ResponseEntity<ResponseRest> deletaProduto(@PathVariable Long codigo, @ApiIgnore ResponseRest response) {
		if(!validaSeExisteId(codigo)){
			response.setMessage("Registro não existente.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		repository.deleteById(codigo);
		response.setMessage("Registro exclúido com sucesso.");
    	response.setType(messageType.SUCESSO);
    	return new ResponseEntity<ResponseRest>(response,HttpStatus.OK);
	}

    public List<Produtos> listaTodosProdutos() {
		return repository.findAll();
	}

    public ResponseEntity<?> buscaPorID(@PathVariable Long codigo, @ApiIgnore ResponseRest response) {
		
		if(!validaSeExisteId(codigo)){
			response.setMessage("Registro não existente.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(repository.findById(codigo));
	}

    public ResponseEntity<?> buscaPorCategoria(@PathVariable String categoria) {
    	List<Produtos> buscaCategoria = repository.findByCategoriaContainingIgnoreCase(categoria);
    	return ResponseEntity.status(HttpStatus.OK).body(buscaCategoria);
	}
    
    public ResponseEntity<?> buscaPorNome(@PathVariable String nome) {
    	List<Produtos> buscaNome = repository.findByNomeContainingIgnoreCase(nome);
    	return ResponseEntity.status(HttpStatus.OK).body(buscaNome);
	}
	
	public Boolean validaSeExisteId(Long id) {
		Optional<Produtos> buscaPorID = repository.findById(id);
		try {
		if(buscaPorID.get().getCodigo() != null) {
	     return true;
		}
		}catch(Exception e) {
		return false;
		}
		return false;
	}

}
