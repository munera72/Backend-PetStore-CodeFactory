package com.udea.petstore.compra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CompraResolverTest {

    private CompraRepository compraRepository;
    private CompraResolver compraResolver;

    @BeforeEach
    void setUp() {
        compraRepository = mock(CompraRepository.class);
        compraResolver = new CompraResolver(compraRepository);
    }

    @Test
    void testCompras() {
        Compra compra1 = new Compra();
        Compra compra2 = new Compra();
        when(compraRepository.findAll()).thenReturn(Arrays.asList(compra1, compra2));

        List<Compra> result = compraResolver.compras();

        assertEquals(2, result.size());
        verify(compraRepository, times(1)).findAll();
    }

    @Test
    void testCompraFound() {
        Compra compra = new Compra();
        compra.setNombreProducto("Dog Food");
        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));

        Compra result = compraResolver.compra(1L);

        assertNotNull(result);
        assertEquals("Dog Food", result.getNombreProducto());
        verify(compraRepository).findById(1L);
    }

    @Test
    void testCompraNotFound() {
        when(compraRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            compraResolver.compra(2L);
        });

        assertEquals("Compra no encontrada", exception.getMessage());
    }

    @Test
    void testInsertarCompra() {
        CompraResolver.CompraInput input = new CompraResolver.CompraInput("Cat Toy", 3);
        Compra savedCompra = new Compra();
        savedCompra.setNombreProducto("Cat Toy");
        savedCompra.setCantidadProductosCompra(3);

        when(compraRepository.save(any(Compra.class))).thenReturn(savedCompra);

        Compra result = compraResolver.insertarCompra(input);

        assertNotNull(result);
        assertEquals("Cat Toy", result.getNombreProducto());
        assertEquals(3, result.getCantidadProductosCompra());

        ArgumentCaptor<Compra> captor = ArgumentCaptor.forClass(Compra.class);
        verify(compraRepository).save(captor.capture());

        Compra captured = captor.getValue();
        assertEquals("Cat Toy", captured.getNombreProducto());
        assertEquals(3, captured.getCantidadProductosCompra());
    }

    @Test
    void testDeleteCompra() {
        Boolean result = compraResolver.deleteCompra(5L);

        assertTrue(result);
        verify(compraRepository, times(1)).deleteById(5L);
    }

    @Test
    void testUpdateCompraFound() {
        Compra existingCompra = new Compra();
        existingCompra.setNombreProducto("Old Name");
        existingCompra.setCantidadProductosCompra(1);

        CompraResolver.CompraInput input = new CompraResolver.CompraInput("New Name", 5);

        when(compraRepository.findById(10L)).thenReturn(Optional.of(existingCompra));
        when(compraRepository.save(any(Compra.class))).thenReturn(existingCompra);

        Compra updated = compraResolver.updateCompra(10L, input);

        assertEquals("New Name", updated.getNombreProducto());
        assertEquals(5, updated.getCantidadProductosCompra());
        verify(compraRepository).save(existingCompra);
    }

    @Test
    void testUpdateCompraNotFound() {
        when(compraRepository.findById(10L)).thenReturn(Optional.empty());

        CompraResolver.CompraInput input = new CompraResolver.CompraInput("New Name", 5);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            compraResolver.updateCompra(10L, input);
        });

        assertEquals("Venta no encontrada", exception.getMessage());
    }
}
