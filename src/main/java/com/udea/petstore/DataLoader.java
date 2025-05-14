package com.udea.petstore;

import com.udea.petstore.Producto.Producto;
import com.udea.petstore.Producto.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    public DataLoader(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productoRepository.save(new Producto("gaseosa","pepsi","Bebidas",3500f,true));
        productoRepository.save(new Producto("gaseosa","cuatro","Bebidas",7000f,true));
    }
}
