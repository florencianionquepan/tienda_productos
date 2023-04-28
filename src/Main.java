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
        System.out.println("\nMENU:");
        System.out.println("1. Listar o crear productos");
        System.out.println("2. Listar o crear vendedor");
        System.out.println("3. Registrar una venta o listarlas");
        System.out.println("0. Salir");

        System.out.print("\nIngrese una opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine();
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

    //MENU PRODUCTOS
    static public void seccionProductos(){
        boolean volver=false;
        while(!volver) {
            menuProducto();
            volver=accionProducto();
        }
    }

    static public void menuProducto(){
            System.out.println("\nMENU PRODUCTO:");
            System.out.println("1. Crear un producto");
            System.out.println("2. Listar productos");
            System.out.println("0. Volver al menu principal");
            System.out.print("\nIngrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
    }

    static public boolean accionProducto(){
        boolean volver=false;
        switch (opcion){
            case 1 : ingresarProducto(); break;
            case 2 : listar(); break;
            case 0 : volver=true;break;
            default : System.out.println("Error en la opcion");break;
        }
        return volver;
    }

    //MENU VENDEDOR
    static public void seccionVendedor(){
        boolean volver=false;
        while(!volver) {
            menuVendedor();
            volver=accionVendedor();
        }
    }

    static public void menuVendedor(){
        System.out.println("\nMENU VENDEDOR:");
        System.out.println("1. Crear un vendedor");
        System.out.println("2. Listar vendedores");
        System.out.println("0. Volver al menu principal");
        System.out.print("\nIngrese una opción: ");
        opcionVende = scanner.nextInt();
        scanner.nextLine();
    }

    static public boolean accionVendedor(){
        boolean volver=false;
        switch (opcionVende){
            case 1 : crearVendedor(); break;
            case 2 : listarVendedores(); break;
            case 0 : volver=true;break;
            default : System.out.println("Error en la opcion");break;
        }
        return volver;
    }

    //MENU VENTAS
    static public void seccionVentas(){
        boolean volver=false;
        while(!volver) {
            menuVenta();
            volver=accionVenta();
        }
    }

    static public void menuVenta(){
        System.out.println("\nMENU VENTA:");
        System.out.println("1. Registrar venta");
        System.out.println("0. Volver al menu principal");
        System.out.print("\nIngrese una opción: ");
        opcionVenta = scanner.nextInt();
        scanner.nextLine();
    }

    static public boolean accionVenta(){
        boolean volver=false;
        switch (opcionVenta){
            case 1 : registrarVenta(); break;
            case 0 : volver=true;break;
            default : System.out.println("Error en la opcion");break;
        }
        return volver;
    }

/*
            case 2 : listarProductosByNombre(); break;
            case 3 : listarProductosByCate(); break;
            case 4 : listarProductosByPrecio(); break;*/

}