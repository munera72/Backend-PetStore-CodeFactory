package com.udea.petstore;

import com.udea.petstore.Compra.Compra;
import com.udea.petstore.Compra.CompraRepository;
import com.udea.petstore.Producto.Producto;
import com.udea.petstore.Producto.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final CompraRepository compraRepository;

    public DataLoader(ProductoRepository productoRepository, CompraRepository compraRepository) {
        this.productoRepository = productoRepository;
        this.compraRepository = compraRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productoRepository.save(new Producto("gaseosa","pepsi","Bebidas",3500f,true));
        productoRepository.save(new Producto("gaseosa","cuatro","Bebidas",7000f,true));
        compraRepository.save(new Compra("pan",3));
    }
}
