package com.udea.petstore;
import com.udea.petstore.compra.CompraRepository;
import com.udea.petstore.producto.ProductoRepository;
import com.udea.petstore.venta.VentaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final CompraRepository compraRepository;
    private final VentaRepository ventaRepository;

    public DataLoader(ProductoRepository productoRepository, CompraRepository compraRepository, VentaRepository ventaRepository) {
        this.productoRepository = productoRepository;
        this.compraRepository = compraRepository;
        this.ventaRepository = ventaRepository;
    }

    /**
    @Override
    public void run(String... args) throws Exception {
        //throw new UnsupportedOperationException("Esta función no se ha implementado");
    }
    */


    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }

    public CompraRepository getCompraRepository() {
        return compraRepository;
    }

    public VentaRepository getVentaRepository() {
        return ventaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
