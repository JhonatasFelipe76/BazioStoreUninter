package com.github.jhonatasfelipe76.baozistore.repository;

import com.github.jhonatasfelipe76.baozistore.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
