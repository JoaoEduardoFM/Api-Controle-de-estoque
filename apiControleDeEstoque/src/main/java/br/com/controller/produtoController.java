package br.com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entity.Produtos;
import br.com.repository.ProdutoRepository;
import br.com.response.ResponseRest;
import br.com.response.ResponseRest.messageType;
import br.com.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("/produtos")
@Api(tags = { "Gerencia produto" })
public class produtoController {

	@Autowired
	ProdutoService service;

	@Autowired
	ProdutoRepository repository;

	// cadastra um novo registro

	@PostMapping
	@ApiOperation(value = "Cadastra produto.", notes = "Cadastra um produto ao estoque.")
	public ResponseEntity<?> cadastraPorduto(@Valid Produtos produto, @ApiIgnore ResponseRest response) {
		
		if(validaSeExisteId(produto.getCodigo())){
			response.setMessage("Id já cadastrado.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}

	// atualiza registro pelo ID de um veiculo cadastrado.
	@PutMapping("atualizaPorId/{codigo}")
	@ApiOperation(value = "Atualiza produto.", notes = "Atualiza registro cadastrado.")
	public ResponseEntity<?> atualizaProduto(@Valid Produtos produto, @ApiIgnore ResponseRest response) {
		
		if(!validaSeExisteId(produto.getCodigo())){
			response.setMessage("Registro não existente.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.updatePorId(produto.getCodigo(), produto));
	}

	// deleta registro do banco de dados
	@DeleteMapping("deletaPorId/{codigo}")
	@ApiOperation(value = "Deleta produto.", notes = "Deleta registro baseado no código.")
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

	// lista todos produtos
	@GetMapping("listaProdutos")
	@ApiOperation(value = "Lista todos produtos.", notes = "Lista todos produtos cadastrados.")
	public List<Produtos> listaTodosProdutos() {
		return repository.findAll();
	}

	// busca um registro especifico pelo ID
	@GetMapping("buscaPorID/{codigo}")
	@ApiOperation(value = "Busca produto por ID.", notes = "Busca produto pelo Id.")
	public ResponseEntity<?> buscaPorID(@PathVariable Long codigo, @ApiIgnore ResponseRest response) {
		
		if(!validaSeExisteId(codigo)){
			response.setMessage("Registro não existente.");
	    	response.setType(messageType.ATENCAO);
	    	return new ResponseEntity<ResponseRest>(response,HttpStatus.BAD_REQUEST);
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(codigo));
	}

	// busca um registro especifico pelo ID
	@GetMapping("listaCtegoria/{categoria}")
	@ApiOperation(value = "Busca produto por categoria.", notes = "Busca produto pela categoria.")
	public ResponseEntity<List<Produtos>> buscaPorCategoria(@PathVariable String categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByCategoria(categoria));
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
