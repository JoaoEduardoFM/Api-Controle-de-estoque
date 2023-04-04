package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.entity.Produtos;
import br.com.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	public ResponseEntity<Produtos> create(Produtos produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	public List<Produtos> findAll() {
		return repository.findAll();
	}

	public List<Produtos> findByNome(String nome) {
		List<Produtos> buscaNome = repository.findByNome(nome);
		return buscaNome;
	}

	public List<Produtos> findByCategoria(String categoria) {
		List<Produtos> buscaCategoria = repository.findByCategoria(categoria);
		return buscaCategoria;
	}

	public Produtos findById(Long id) {
		Produtos buscaPorId = repository.findById(id).orElseThrow(() -> new RuntimeException());
		return buscaPorId;
	}

	public void delete(Long id) {
		repository.delete(repository.findById(id).orElseThrow(() -> new RuntimeException()));
	}

	public Produtos updatePorId(Long id, Produtos produto) {
		Produtos produtoSalvo = findById(id);
		return repository.save(produtoSalvo);
	}

}
