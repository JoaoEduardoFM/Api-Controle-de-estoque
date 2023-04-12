package br.com.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
