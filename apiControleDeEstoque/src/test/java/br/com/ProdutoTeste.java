package br.com;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.model.entity.Produtos;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ProdutoTeste {

	@Autowired
	ProdutoService produtoService;

	Produtos produto = new Produtos(111L, "Cano", "PVC", BigDecimal.TEN, "Cano 25mm", 256L);
	ResponseRest response = new ResponseRest();

	@Test
	@DisplayName("A")
	void initialize() {
		log.info("Iniciando testes classe ProdutoTeste");
		response.setMessage("Teste realizado");
		response.setType(messageType.SUCESSO);
	}

	@Test
	@DisplayName("B")
	void cadastraProduto() {
		log.info("Cadatrando produto");
		produtoService.cadastraPorduto(produto, response);
	}

	@Test
	@DisplayName("C")
	void atualizaProduto() {
		log.info("Atualizando produto");
		produtoService.cadastraPorduto(produto, response);
	}

	@Test
	@DisplayName("D")
	void listaProdutos() {
		log.info("Listando Produtos");
		produtoService.listaTodosProdutos();
	}

	@Test
	@DisplayName("E")
	void buscaPorId() {
		log.info("Buscando por id");
		produtoService.buscaPorID(111L, response);
	}

	@Test
	@DisplayName("F")
	void buscaPorCategoria() {
		log.info("Buscando por categoria");
		produtoService.buscaPorCategoria("PVC");
	}

	@Test
	@DisplayName("G")
	void buscaPorNome() {
		log.info("Buscando por nome");
		produtoService.buscaPorNome("Cano");
	}

	@Test
	@DisplayName("H")
	void deletaProduto() {
		log.info("Deletando produto");
		produtoService.cadastraPorduto(produto, response);
	}
}
