package com.Home_pocket.repository;

import com.Home_pocket.model.ListaMercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaMercadoRepository extends JpaRepository<ListaMercado, Long> {
}
