package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entity.Produtos;
import br.com.response.ResponseRest;
import br.com.response.ResponseRest.messageType;
import br.com.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("produtos/controleEstoque")
@Api(tags = { "Controle de estoque" })
public class GerenciamentoEstoqueController {

	@Autowired
	ProdutoService service;

	@PatchMapping("baixaEstoque/{codigo}")
	@ApiOperation(value = "Baixa estoque.", notes = "Da baixa a um produto baseado no id cadastrado.")
	public ResponseEntity<ResponseRest> baixaEstoque(Long codigo, Long quantidade, @ApiIgnore @Valid Produtos produto, @ApiIgnore ResponseRest response) {

		if (verificaQuantidade(codigo) - (produto.getQuantidade()) < 0) {
			response.setMessage("Quantidade requsitada é maior que o estoque. Estoque:" + produto.getQuantidade());
			response.setType(messageType.ERRO);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		produto.setQuantidade(verificaQuantidade(codigo) - (produto.getQuantidade()));
		alteraQuantidade(produto, produto.getQuantidade(), codigo);
		response.setMessage("Baixa realizada com sucesso. estoque:" + produto.getQuantidade() );
		response.setType(messageType.SUCESSO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping("entradaEstoque/{codigo}")
	@ApiOperation(value = "Entrada estoque.", notes = "Da entrada a um produto baseado no id cadastrado.")
	public ResponseEntity<ResponseRest> adicionaEstoque(Long codigo, Long quantidade, @ApiIgnore @Valid Produtos produto, @ApiIgnore ResponseRest response) {
		produto.setQuantidade(verificaQuantidade(codigo) + (produto.getQuantidade()));
		alteraQuantidade(produto, produto.getQuantidade(), codigo);
		response.setMessage("Entrada realizada com sucesso. estoque:" + produto.getQuantidade());
		response.setType(messageType.SUCESSO);
		return new ResponseEntity<>(response, HttpStatus.OK);
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

}
