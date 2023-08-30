package br.com;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.model.entity.Produtos;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.service.EstoqueService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EstoqueTeste {
	
	@Autowired
	EstoqueService estoqueService;
	
	Produtos produto = new Produtos(111L, "Cano", "PVC", BigDecimal.TEN, "Cano 25mm", 256L);
	ResponseRest response = new ResponseRest();
	
	@Test
	void initialize() {
		log.info("Iniciando testes classe EstoqueTeste");
		response.setMessage("Teste realizado");
		response.setType(messageType.SUCESSO);
	}
	
	@Test
	void baixaEstoque() {
		log.info("Dando baixa no estoque");
		estoqueService.baixaEstoque(111L, 1L, produto, response);
	}
	
	@Test
	void entradaEstoque() {
		log.info("Dando entrada no estoque");
		estoqueService.adicionaEstoque(111L, 1L, produto, response);
	}

}
