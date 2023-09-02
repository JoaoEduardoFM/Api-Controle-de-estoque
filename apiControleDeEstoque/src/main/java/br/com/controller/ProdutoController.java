package br.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.entity.Produtos;
import br.com.model.response.ResponseRest;
import br.com.repository.ProdutoRepository;
import br.com.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("/produtos")
@Api(tags = { "Gerencia produto" }, description = " CRUD(Produtos).")
public class ProdutoController {

	@Autowired
	ProdutoService service;

	@Autowired
	ProdutoRepository repository;

	// cadastra um novo registro
	@PostMapping
	@ApiOperation(
			value = "Cadastra produto.", 
			notes = "Cadastra um produto ao estoque.")
	public ResponseEntity<?> cadastraPorduto(@RequestBody @Valid Produtos produto, @ApiIgnore ResponseRest response) {	
		return service.cadastraPorduto(produto, response);	
	}

	// atualiza registro pelo ID de um veiculo cadastrado.
	@PutMapping("atualizaPorId/{codigo}")
	@ApiOperation(
			value = "Atualiza produto.", 
			notes = "Atualiza registro cadastrado.")
	public ResponseEntity<?> atualizaProduto(@RequestBody @Valid Produtos produto, @ApiIgnore ResponseRest response) {
		return service.atualizaProduto(produto, response);	
	}

	// deleta registro do banco de dados
	@DeleteMapping("deletaPorId/{codigo}")
	@ApiOperation(
			value = "Deleta produto.", 
			notes = "Deleta registro baseado no código.")
	public ResponseEntity<ResponseRest> deletaProduto(@PathVariable Long codigo, @ApiIgnore ResponseRest response) {
		return service.deletaProduto(codigo, response);	
	}

	// lista todos produtos
	@GetMapping("listaProdutos")
	@ApiOperation(
			value = "Lista todos produtos.", 
			notes = "Lista todos produtos cadastrados.")
	public List<Produtos> listaTodosProdutos() {
		return service.listaTodosProdutos();
	}

	// busca um registro especifico pelo ID
	@GetMapping("buscaPorID/{codigo}")
	@ApiOperation(
			value = "Busca produto por ID.", 
			notes = "Busca produto pelo Id.")
	public ResponseEntity<?> buscaPorID(@PathVariable Long codigo, @ApiIgnore ResponseRest response) {
		return service.buscaPorID(codigo, response);
	}

	// busca um registro especifico pela categoria
	@GetMapping("listaCategoria/{categoria}")
	@ApiOperation(
			value = "Busca produto por categoria.", 
			notes = "Busca produto pela categoria.")
	public ResponseEntity<?> buscaPorCategoria(@PathVariable String categoria) {
		return service.buscaPorCategoria(categoria);
	}
	
	// busca um registro especifico pelo nome
	@GetMapping("listaNome/{nome}")
	@ApiOperation(
			value = "Busca produto pelo nome.", 
			notes = "Busca produto pelo nome.")
	public ResponseEntity<?> buscaPorNome(@PathVariable String nome) {
		return service.buscaPorNome(nome);
	}
}
