package com.udea.petstore.Producto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductoResolver {
    private final ProductoRepository productoRepository;

    public ProductoResolver(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @QueryMapping
    public List<Producto> productos() {
        return productoRepository.findAll();
    }

    @QueryMapping
    public Producto producto(@Argument Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("producto no encontrada"));
    }

    public record ProductoInput(String nombre, String descripcion, String categoria, Float precio, Boolean estado){}

    @MutationMapping(name = "insertarProducto")
    public Producto insertarProducto(@Argument ProductoInput productoInput) {
        Producto producto = new Producto();
        producto.setNombre(productoInput.nombre);
        producto.setDescripcion(productoInput.descripcion);
        producto.setCategoria(productoInput.categoria);
        producto.setprecio(productoInput.precio);
        producto.setEstado(productoInput.estado);
        return productoRepository.save(producto);
    }

    @MutationMapping
    public Boolean deleteProducto(@Argument Long id) {
        productoRepository.deleteById(id);
        return true;
    }

    @MutationMapping
    public Producto updateProducto(@Argument Long id, @Argument ProductoInput productoInput) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        producto.setNombre(productoInput.nombre());
        producto.setDescripcion(productoInput.descripcion());
        producto.setCategoria(productoInput.categoria());
        producto.setprecio(productoInput.precio());
        producto.setEstado(productoInput.estado());
        return productoRepository.save(producto);
    }

}
