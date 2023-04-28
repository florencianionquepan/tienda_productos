package database;

import entidad.Producto;
import entidad.Vendedor;
import entidad.Venta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VentaLoader {

    static Vendedor vendeUno=VendedorLoader.getVendedorUno();
    static Vendedor vendeDos=VendedorLoader.getVendedorDos();

    static Producto prodUno=ProductoLoader.getProductoUno();
    static Producto prodDos=ProductoLoader.getProductoDos();
    static Producto prodTres=ProductoLoader.getProductoTres();
    static Producto prodCuatro=ProductoLoader.getProductoCuatro();

    static List<Producto> getProductosVentaUno=new ArrayList<Producto>(
            Arrays.asList(
                prodUno, prodDos
            )
    );

    static List<Producto> getProductosVentaDos=new ArrayList<Producto>(
            Arrays.asList(
                    prodUno,prodDos,prodTres,prodCuatro
            )
    );

    public static List<Venta> cargarVentas(){
        return new ArrayList<Venta>(
                Arrays.asList(
                    new Venta(1L,vendeUno,getProductosVentaUno,0.05f),
                    new Venta(2L,vendeDos,getProductosVentaDos,0.1f)
                )
        );
    }

}
