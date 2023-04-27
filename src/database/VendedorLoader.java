package database;

import entidad.Vendedor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendedorLoader {
    public static List<Vendedor> cargarVende(){
        return new ArrayList<Vendedor>(
                Arrays.asList(new Vendedor(1L, "V001", "Juan Perez", 15000.0f),
                            new Vendedor(2L, "V002", "Ana Gomez", 12000.0f)
                )
        );
    }

    public static Vendedor getVendedorUno(){
        return new Vendedor(1L, "V001", "Juan Perez", 15000.0f);
    }

    public static Vendedor getVendedorDos(){
        return new Vendedor(2L, "V002", "Ana Gomez", 12000.0f);
    }
}
