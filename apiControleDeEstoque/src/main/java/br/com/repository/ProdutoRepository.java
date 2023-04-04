package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.entity.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos, Long>{
	
    public List<Produtos> findByNome(@Param("nome") String nome);
    
    public List<Produtos> findByCategoria(@Param("categoria") String categoria);
	
	//public List<Veiculo> findByMarcaAndModelo(String marca, String modelo);	

}
