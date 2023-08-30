package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.entity.Produtos;
import br.com.model.response.ResponseRest;
import br.com.service.EstoqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("produtos/controleEstoque")
@Api(tags = { "Controle de estoque" })
public class EstoqueController {
	
	@Autowired
	EstoqueService estoqueService;

	@PatchMapping("baixaEstoque/{codigo}")
	@ApiOperation(value = "Baixa estoque.", notes = "Da baixa a um produto baseado no id cadastrado.")
	public ResponseEntity<ResponseRest> baixaEstoque(Long codigo, Long quantidade, @ApiIgnore @Valid Produtos produto, @ApiIgnore ResponseRest response) {
		return estoqueService.baixaEstoque(codigo, quantidade, produto, response);
	}

	@PatchMapping("entradaEstoque/{codigo}")
	@ApiOperation(value = "Entrada estoque.", notes = "Da entrada a um produto baseado no id cadastrado.")
	public ResponseEntity<ResponseRest> adicionaEstoque(Long codigo, Long quantidade, @ApiIgnore @Valid Produtos produto, @ApiIgnore ResponseRest response) {
		return estoqueService.adicionaEstoque(codigo, quantidade, produto, response);
	}
}
