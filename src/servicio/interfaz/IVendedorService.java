package servicio.interfaz;

import entidad.Vendedor;

import java.util.List;

public interface IVendedorService {
    Vendedor crear(Vendedor nuevo);
    List<Vendedor> listar();
    Vendedor buscarByCodigo(String codigo);
}
