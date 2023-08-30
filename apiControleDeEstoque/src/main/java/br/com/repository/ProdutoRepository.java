package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.model.entity.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos, Long>{
    
    public List<Produtos> findByCategoriaContainingIgnoreCase(@Param("categoria") String categoria);
    
    public List<Produtos> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
