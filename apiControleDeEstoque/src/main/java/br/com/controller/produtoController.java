package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entity.Produtos;
import br.com.repository.ProdutoRepository;
import br.com.response.ResponseRest;
import br.com.response.ResponseRest.messageType;
import br.com.service.ProdutoService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/produtos")
public class produtoController {

	@Autowired
	ProdutoService service;

	@Autowired
	ProdutoRepository repository;

	// cadastra um novo registro
	@PostMapping
	public ResponseEntity<Produtos> cadastraPorduto(@RequestBody Produtos produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	// atualiza registro pelo ID de um veiculo cadastrado.
	@PutMapping("atualizaPorId/{id}")
	public ResponseEntity<Produtos> atualizaProduto(@PathVariable Long id, @RequestBody Produtos produto) {		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.updatePorId(id, produto));
	}

	// deleta registro do banco de dados
	@DeleteMapping("deletaPorId/{id}")
	public void deletaProduto(@PathVariable Long id) {
		repository.deleteById(id);
	}

	// lista todos produtos
	@GetMapping("listaProdutos")
	public List<Produtos> listaTodosCarros() {
		return repository.findAll();
	}

	// busca um registro especifico pelo ID
	@GetMapping("buscaPorID/{id}")
	public ResponseEntity<?> buscaPorID(@PathVariable Long id) {		
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}

	// busca um registro especifico pelo ID
	@GetMapping("listaCtegoria/{categoria}")
	public ResponseEntity<List<Produtos>> buscaPorCategoria(@PathVariable String categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByCategoria(categoria));
	}

	@PatchMapping("baixaEstoque/{id}")
	public ResponseEntity<ResponseRest> baixaEstoque(@PathVariable Long id, @RequestBody Produtos produto,
			ResponseRest response) {

		if (verificaQuantidade(id) - (produto.getQuantidade()) < 0) {
			response.setMessage("Quantidade requsitada é maior que o estoque. Estoque:" + produto.getQuantidade());
			response.setType(messageType.ERRO);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		produto.setQuantidade(verificaQuantidade(id) - (produto.getQuantidade()));
		alteraQuantidade(produto, produto.getQuantidade(), id);
		response.setMessage("Baixa realizada com sucesso.");
		response.setType(messageType.SUCESSO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping("entradaEstoque/{id}")
	public ResponseEntity<ResponseRest> adicionaEstoque(@PathVariable Long id, @RequestBody Produtos produto,
			ResponseRest response) {
		produto.setQuantidade(verificaQuantidade(id) + (produto.getQuantidade()));
		alteraQuantidade(produto, produto.getQuantidade(), id);
		response.setMessage("Entrada realizada com sucesso.");
		response.setType(messageType.SUCESSO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<Produtos> alteraQuantidade(Produtos produto, Long quantidade, Long id) {
		Produtos ProdutoCadastrado = service.findById(id);
		produto.setCategoria(ProdutoCadastrado.getCategoria());
		produto.setCodigo(ProdutoCadastrado.getCodigo());
		produto.setDescricao(ProdutoCadastrado.getDescricao());
		produto.setNome(ProdutoCadastrado.getNome());
		produto.setQuantidade(quantidade);
		produto.setValor(ProdutoCadastrado.getValor());
		return service.create(produto);
	}

	public Long verificaQuantidade(Long id) {
		Produtos produto = service.findById(id);
		if (produto != null) {
			return produto.getQuantidade();
		}
		return null;
	}
	
	public Long verificaProduto(Long id) {
		Produtos produto = service.findById(id);
		if (produto != null) {
			return produto.getQuantidade();
		}
		return null;
	}

}
