package com.udea.petstore.venta;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VentaTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        Venta venta = new Venta();

        venta.setId(1L);
        venta.setUsuario("Mario");
        venta.setTotal(500.0);
        venta.setMediopago("Nequi");
        venta.setVentaespecial(true);
        venta.setCantidadProductosVenta(3);

        assertEquals(1L, venta.getId());
        assertEquals("Mario", venta.getUsuario());
        assertEquals(500.0, venta.getTotal());
        assertEquals("Nequi", venta.getMediopago());
        assertTrue(venta.getVentaespecial());
        assertEquals(3, venta.getCantidadProductosVenta());
    }

    @Test
    void testAllArgsConstructor() {
        Venta venta = new Venta("Luisa", 250.0, "Efectivo", false, 2);

        assertNull(venta.getId()); // ID should be null unless manually set
        assertEquals("Luisa", venta.getUsuario());
        assertEquals(250.0, venta.getTotal());
        assertEquals("Efectivo", venta.getMediopago());
        assertFalse(venta.getVentaespecial());
        assertEquals(2, venta.getCantidadProductosVenta());
    }

    @Test
    void testToString() {
        Venta venta = new Venta("Pedro", 99.99, "Tarjeta", true, 1);
        venta.setId(10L);

        String result = venta.toString();

        assertTrue(result.contains("usuario='Pedro'"));
        assertTrue(result.contains("total=99.99"));
        assertTrue(result.contains("mediopago='Tarjeta'"));
        assertTrue(result.contains("cantidadProductosVenta='1'")); // Note: stored as int but displayed as String
        assertTrue(result.contains("ventaespecial=true"));
    }
}
