package com.udea.petstore.producto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        Producto producto = new Producto();

        producto.setId(100L);
        producto.setNombre("Croquetas");
        producto.setDescripcion("Croquetas para perro");
        producto.setCategoria("Alimentos");
        producto.setprecio(59.99f);
        producto.setEstado(true);

        assertEquals(100L, producto.getId());
        assertEquals("Croquetas", producto.getNombre());
        assertEquals("Croquetas para perro", producto.getDescripcion());
        assertEquals("Alimentos", producto.getCategoria());
        assertEquals(59.99f, producto.getprecio());
        assertTrue(producto.getEstado());
    }

    @Test
    void testAllArgsConstructor() {
        Producto producto = new Producto("Juguete", "Pelota con sonido", "Accesorios", 15.5f, false);

        assertNull(producto.getId()); // ID is not set by constructor
        assertEquals("Juguete", producto.getNombre());
        assertEquals("Pelota con sonido", producto.getDescripcion());
        assertEquals("Accesorios", producto.getCategoria());
        assertEquals(15.5f, producto.getprecio());
        assertFalse(producto.getEstado());
    }

    @Test
    void testToString() {
        Producto producto = new Producto("Arena", "Arena para gatos", "Higiene", 12.75f, true);
        producto.setId(5L);

        String result = producto.toString();

        assertTrue(result.contains("id=5"));
        assertTrue(result.contains("nombre='Arena'"));
        assertTrue(result.contains("descripcion='Arena para gatos'"));
        assertTrue(result.contains("categoria='Higiene'"));
        assertTrue(result.contains("precio=12.75"));
        assertTrue(result.contains("estado=true"));
    }
}
