import database.ProductoLoader;
import database.VendedorLoader;
import database.VentaLoader;
import entidad.Producto;
import entidad.Vendedor;
import entidad.Venta;
import repository.memoria.*;
import servicio.imple.ProductoService;
import servicio.imple.VendedorService;
import servicio.imple.VentaService;
import servicio.interfaz.IProductoService;
import servicio.interfaz.IVendedorService;
import servicio.interfaz.IVentaService;

import java.util.List;
import java.util.Scanner;

public class Main {
    List<Producto> productos= ProductoLoader.cargarProductos();
    IProductoRepoMemo repoProducto=new ProductoRepoMemo(productos);
    IProductoService serProducto=new ProductoService(repoProducto);

    List<Vendedor> vendedores= VendedorLoader.cargarVende();
    IVendedorRepoMemo repoVendedor=new VendedorRepoMemo(vendedores);
    IVendedorService serVende=new VendedorService(repoVendedor);

    List<Venta> ventas= VentaLoader.cargarVentas();
    IVentaRepoMemo repoVenta=new VentaRepoMemo(ventas);
    IVentaService serVenta=new VentaService(repoVenta);

    static int opcion;
    static int opcionPro;
    static int opcionVende;
    static int opcionVenta;
    static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {
/*
        List<Venta> listaVentas=serVenta.listar();
        for(Venta venta:listaVentas){
            System.out.println(venta.toString());
        }*/

        do{
            menu();
            accion();
        }while(opcion!=0);
    }

    //MENU Y ACCION GENERAL
    static public void menu(){
        do{
            System.out.println("\nMENU:");
            System.out.println("1. Listar o crear productos");
            System.out.println("2. Listar o crear vendedor");
            System.out.println("3. Registrar una venta o listarlas");
            System.out.println("0. Salir");

            System.out.print("\nIngrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

        }while(opcion!=0);
    }

    static public void accion(){
        switch (opcion){
            case 1 : seccionProductos(); break;
            case 2 : seccionVendedor(); break;
            case 3 : seccionVentas(); break;
            case 0 :System.out.println("Adios!\n\n");break;
            default : System.out.println("Error en la opcion");break;
        }
    }
}