package com.udea.petstore.producto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductoResolverTest {

    private ProductoRepository productoRepository;
    private ProductoResolver productoResolver;

    @BeforeEach
    void setUp() {
        productoRepository = mock(ProductoRepository.class);
        productoResolver = new ProductoResolver(productoRepository);
    }

    @Test
    void testProductos() {
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> result = productoResolver.productos();

        assertEquals(2, result.size());
        verify(productoRepository).findAll();
    }

    @Test
    void testProductoFound() {
        Producto producto = new Producto();
        producto.setNombre("Dog Toy");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productoResolver.producto(1L);

        assertNotNull(result);
        assertEquals("Dog Toy", result.getNombre());
        verify(productoRepository).findById(1L);
    }

    @Test
    void testProductoNotFound() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productoResolver.producto(99L);
        });

        assertEquals("producto no encontrada", exception.getMessage());
    }

    @Test
    void testInsertarProducto() {
        ProductoResolver.ProductoInput input = new ProductoResolver.ProductoInput(
                "Ball", "Rubber ball", "Juguete", 10.5f, true
        );

        Producto savedProducto = new Producto();
        savedProducto.setNombre("Ball");
        savedProducto.setDescripcion("Rubber ball");
        savedProducto.setCategoria("Juguete");
        savedProducto.setprecio(10.5f);
        savedProducto.setEstado(true);

        when(productoRepository.save(any(Producto.class))).thenReturn(savedProducto);

        Producto result = productoResolver.insertarProducto(input);

        assertEquals("Ball", result.getNombre());
        assertEquals("Rubber ball", result.getDescripcion());
        assertEquals("Juguete", result.getCategoria());
        assertEquals(10.5f, result.getprecio());
        assertTrue(result.getEstado());

        ArgumentCaptor<Producto> captor = ArgumentCaptor.forClass(Producto.class);
        verify(productoRepository).save(captor.capture());

        Producto captured = captor.getValue();
        assertEquals("Ball", captured.getNombre());
    }

    @Test
    void testDeleteProducto() {
        Boolean result = productoResolver.deleteProducto(5L);

        assertTrue(result);
        verify(productoRepository).deleteById(5L);
    }

    @Test
    void testUpdateProductoFound() {
        Producto existing = new Producto();
        existing.setNombre("Old");
        existing.setDescripcion("Old Desc");
        existing.setCategoria("Accesorio");
        existing.setprecio(15.0f);
        existing.setEstado(false);

        ProductoResolver.ProductoInput input = new ProductoResolver.ProductoInput(
                "New", "New Desc", "NuevoCat", 30.0f, true
        );

        when(productoRepository.findById(10L)).thenReturn(Optional.of(existing));
        when(productoRepository.save(any(Producto.class))).thenReturn(existing);

        Producto result = productoResolver.updateProducto(10L, input);

        assertEquals("New", result.getNombre());
        assertEquals("New Desc", result.getDescripcion());
        assertEquals("NuevoCat", result.getCategoria());
        assertEquals(30.0f, result.getprecio());
        assertTrue(result.getEstado());

        verify(productoRepository).save(existing);
    }

    @Test
    void testUpdateProductoNotFound() {
        ProductoResolver.ProductoInput input = new ProductoResolver.ProductoInput(
                "New", "Desc", "Cat", 20.0f, true
        );

        when(productoRepository.findById(100L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productoResolver.updateProducto(100L, input);
        });

        assertEquals("Venta no encontrada", exception.getMessage());
    }
}
