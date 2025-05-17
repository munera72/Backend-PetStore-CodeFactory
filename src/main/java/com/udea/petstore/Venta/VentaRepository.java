package com.udea.petstore.Venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface VentaRepository extends JpaRepository<Venta, Long>, QueryByExampleExecutor<Venta> {
}
