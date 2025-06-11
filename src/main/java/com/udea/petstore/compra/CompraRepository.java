package com.udea.petstore.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface CompraRepository extends JpaRepository<Compra,Long>, QueryByExampleExecutor<Compra> {
}
