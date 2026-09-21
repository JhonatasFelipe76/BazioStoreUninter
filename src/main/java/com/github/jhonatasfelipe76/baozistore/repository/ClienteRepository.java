package com.github.jhonatasfelipe76.baozistore.repository;

import com.github.jhonatasfelipe76.baozistore.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
