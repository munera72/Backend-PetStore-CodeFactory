package com.udea.petstore.venta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VentaResolverTest {

    private VentaRepository ventaRepository;
    private VentaResolver ventaResolver;

    @BeforeEach
    void setUp() {
        ventaRepository = mock(VentaRepository.class);
        ventaResolver = new VentaResolver(ventaRepository);
    }

    @Test
    void testVentas() {
        Venta v1 = new Venta();
        Venta v2 = new Venta();
        when(ventaRepository.findAll()).thenReturn(Arrays.asList(v1, v2));

        List<Venta> result = ventaResolver.ventas();

        assertEquals(2, result.size());
        verify(ventaRepository).findAll();
    }

    @Test
    void testVentaFound() {
        Venta venta = new Venta();
        venta.setUsuario("Carlos");
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));

        Venta result = ventaResolver.venta(1L);

        assertNotNull(result);
        assertEquals("Carlos", result.getUsuario());
    }

    @Test
    void testVentaNotFound() {
        when(ventaRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            ventaResolver.venta(99L);
        });

        assertEquals("venta no encontrado", ex.getMessage());
    }

    @Test
    void testVentaInsertarWithArguments() {
        Venta mockVenta = new Venta("Laura", 300.0, "Tarjeta", true, 4);
        when(ventaRepository.save(any(Venta.class))).thenReturn(mockVenta);

        Venta result = ventaResolver.ventaInsertar("Laura", 300.0, "Tarjeta", true, 4);

        assertEquals("Laura", result.getUsuario());
        assertEquals(300.0, result.getTotal());
        assertEquals("Tarjeta", result.getMediopago());
        assertTrue(result.getVentaespecial());
        assertEquals(4, result.getCantidadProductosVenta());

        verify(ventaRepository).save(any(Venta.class));
    }

    @Test
    void testInsertarVentaWithInput() {
        VentaResolver.VentaInput input = new VentaResolver.VentaInput("Ana", 150.0, "Efectivo", false, 2);
        Venta saved = new Venta();
        saved.setUsuario("Ana");
        saved.setTotal(150.0);
        saved.setMediopago("Efectivo");
        saved.setVentaespecial(false);
        saved.setCantidadProductosVenta(2);

        when(ventaRepository.save(any(Venta.class))).thenReturn(saved);

        Venta result = ventaResolver.insertarVenta(input);

        assertEquals("Ana", result.getUsuario());
        assertEquals(150.0, result.getTotal());
        assertEquals("Efectivo", result.getMediopago());
        assertFalse(result.getVentaespecial());
        assertEquals(2, result.getCantidadProductosVenta());

        ArgumentCaptor<Venta> captor = ArgumentCaptor.forClass(Venta.class);
        verify(ventaRepository).save(captor.capture());
        assertEquals("Ana", captor.getValue().getUsuario());
    }

    @Test
    void testDeleteVenta() {
        Boolean result = ventaResolver.deleteVenta(5L);
        assertTrue(result);
        verify(ventaRepository).deleteById(5L);
    }

    @Test
    void testUpdateVentaFound() {
        Venta existing = new Venta();
        existing.setUsuario("Pedro");
        existing.setTotal(200.0);
        existing.setMediopago("PSE");
        existing.setVentaespecial(false);
        existing.setCantidadProductosVenta(3);

        VentaResolver.VentaInput input = new VentaResolver.VentaInput("Pablo", 400.0, "Efectivo", true, 6);

        when(ventaRepository.findById(10L)).thenReturn(Optional.of(existing));
        when(ventaRepository.save(any(Venta.class))).thenReturn(existing);

        Venta result = ventaResolver.updateVenta(10L, input);

        assertEquals("Pablo", result.getUsuario());
        assertEquals(400.0, result.getTotal());
        assertEquals("Efectivo", result.getMediopago());
        assertTrue(result.getVentaespecial());
        assertEquals(6, result.getCantidadProductosVenta());

        verify(ventaRepository).save(existing);
    }

    @Test
    void testUpdateVentaNotFound() {
        VentaResolver.VentaInput input = new VentaResolver.VentaInput("User", 100.0, "Cash", false, 1);

        when(ventaRepository.findById(404L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            ventaResolver.updateVenta(404L, input);
        });

        assertEquals("Venta no encontrada", ex.getMessage());
    }
}
