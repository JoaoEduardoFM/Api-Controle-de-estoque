package br.com.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {
	
	@Id
	Long codigo;
	
	String nome;
	
	String descricao;
	
	String categoria;
	
	BigDecimal valor;
	
	Long quantidade;
	

}
