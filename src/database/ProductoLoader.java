package database;

import entidad.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductoLoader {
    public static List<Producto> cargarProductos(){
        return new ArrayList<Producto>(
                Arrays.asList(
                        new Producto(1L, "P001", "Laptop HP", 15000.0f, "Electronica"),
                        new Producto(2L, "P002", "Smartphone Samsung", 8000.0f, "Electronica"),
                        new Producto(3L, "P003", "Televisor LG", 12000.0f, "Electronica"),
                        new Producto(4L, "P004", "Zapatillas Nike", 2000.0f, "Calzado"),
                        new Producto(5L, "P005", "Jeans Levi's", 1500.0f, "Ropa"),
                        new Producto(6L, "P006", "Reloj Casio", 1000.0f, "Ropa"),
                        new Producto(7L, "P007", "Botella de vino tinto", 500.0f, "Bebidas"),
                        new Producto(8L, "P008", "Caja de chocolates", 200.0f, "Dulces")
                )
        );
    }
}
