package com.udea.petstore.Venta;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import java.util.List;

@Controller
public class VentaResolver {
    private final VentaRepository ventaRepository;

    public VentaResolver(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @QueryMapping
    public List<Venta> ventas() {
        return ventaRepository.findAll();
    }

    @QueryMapping
    public Venta venta(@Argument Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("venta no encontrado"));
    }

    @QueryMapping
    public Venta ventaInsertar(@Argument String usuario, @Argument Double total, @Argument String mediopago,@Argument boolean ventaespecial) {
        return ventaRepository.save(new Venta(usuario,total,mediopago,ventaespecial));

    }

    public record VentaInput(String usuario, Double total, String mediopago, Boolean ventaespecial) {}

    @MutationMapping(name = "insertarVenta")
    public Venta insertarVenta(@Argument VentaInput ventaInput) {
        Venta venta = new Venta();
        venta.setUsuario(ventaInput.usuario());
        venta.setTotal(ventaInput.total());
        venta.setMediopago(ventaInput.mediopago());
        venta.setVentaespecial(ventaInput.ventaespecial());
        return ventaRepository.save(venta);
    }

    @MutationMapping
    public Boolean deleteVenta(@Argument Long id) {
        ventaRepository.deleteById(id);
        return true;
    }

    @MutationMapping
    public Venta updateVenta(@Argument Long id, @Argument VentaInput ventaInput) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        venta.setUsuario(ventaInput.usuario());
        venta.setTotal(ventaInput.total());
        venta.setMediopago(ventaInput.mediopago());
        venta.setVentaespecial(ventaInput.ventaespecial());

        return ventaRepository.save(venta);
    }
}
