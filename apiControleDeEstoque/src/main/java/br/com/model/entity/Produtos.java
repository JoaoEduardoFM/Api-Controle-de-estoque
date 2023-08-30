package br.com.model.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "quantidade"}, allowGetters = true)
public class Produtos {
	
	@Id
	@ApiModelProperty(value = "Código produto", required = true)
	Long codigo;
	
	@ApiModelProperty(value = "Nome produto", required = true)
	String nome;
	
	@ApiModelProperty(value = "Categoria vinculada a um produto", required = true)
	String categoria;
	
	@ApiModelProperty(value = "Valor do produto", required = true)
	BigDecimal valor;
	
	@ApiModelProperty(value = "Descrição de um produto", required = false)
	String descricao;
	
	@ApiModelProperty(value = "Quantidade cadastrada", required = false)
	Long quantidade;
}
