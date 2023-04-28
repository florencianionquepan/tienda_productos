import database.ProductoLoader;
import database.VendedorLoader;
import database.VentaLoader;
import entidad.Producto;
import entidad.Vendedor;
import entidad.Venta;
import exceptions.ProductoException;
import exceptions.VendedorException;
import repository.memoria.*;
import servicio.imple.ProductoService;
import servicio.imple.VendedorService;
import servicio.imple.VentaService;
import servicio.interfaz.IProductoService;
import servicio.interfaz.IVendedorService;
import servicio.interfaz.IVentaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Producto> productos= ProductoLoader.cargarProductos();
    static IProductoRepoMemo repoProducto=new ProductoRepoMemo(productos);
    static IProductoService serProducto=new ProductoService(repoProducto);

    static List<Vendedor> vendedores= VendedorLoader.cargarVende();
    static IVendedorRepoMemo repoVendedor=new VendedorRepoMemo(vendedores);
    static IVendedorService serVende=new VendedorService(repoVendedor);

    static List<Venta> ventas= VentaLoader.cargarVentas();
    static IVentaRepoMemo repoVenta=new VentaRepoMemo(ventas);
    static IVentaService serVenta=new VentaService(repoVenta);

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
        System.out.println("\nMENU GENERAL:");
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
            //case 1 : ingresarProducto(); break;
            //case 2 : listarProductos(); break;
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
            //case 1 : crearVendedor(); break;
            //case 2 : listarVendedores(); break;
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

    static public void registrarVenta() {
        boolean salirRegVenta = false;
        Vendedor vende = null;
        while (!salirRegVenta) {
            System.out.println("Registro de Venta");
            System.out.println("------------------");
            vende = insertarVendedor();
            System.out.println("Ha seleccionado al vendedor: "+vende);
            List<Producto> productos = insertarProductos();
            if (vende == null || productos == null) {
                salirRegVenta = true;
            }
        }
        Venta ventaNueva = new Venta(0L, vende, productos,0f);
        Venta creada=serVenta.crear(ventaNueva);
        System.out.println("Venta creada: ");
        System.out.println(creada.toString());
    }

    public static Vendedor insertarVendedor() {
        Vendedor vende = null;
        boolean salir=false;
        do{
            System.out.println("¿Desea buscar un vendedor existente o crear uno nuevo?");
            System.out.println("1. Buscar vendedor existente");
            serVende.listar().forEach(System.out::println);
            System.out.println("2. Crear vendedor nuevo");
            System.out.println("3. Cancelar registro de venta");
            System.out.print("Ingrese una opción: ");
            int opcionVendedor = scanner.nextInt();
            scanner.nextLine();
            switch (opcionVendedor){
                case 1:
                    System.out.println("Ingrese codigo del vendedor:");
                    String codigo = scanner.nextLine();
                    try{
                        vende= serVende.buscarByCodigo(codigo);
                        salir=true;
                    }catch(VendedorException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                //case 2: vende=crearVendedor();salir=true;break;
                case 3: salir=true;return null;
                default:System.out.println("\nOpción inválida.");break;
            }
        }while(!salir);
        return vende;
    }

    public static List<Producto> insertarProductos(){
        boolean salir=false;
        List<Producto> productos=new ArrayList<Producto>();
        do{
            System.out.println("¿Desea buscar un producto existente o crear uno nuevo?");
            System.out.println("1.Buscar producto por codigo");
            System.out.println("2.Buscar producto por nombre");
            System.out.println("3.Buscar producto por categoria");
            System.out.println("4.Crear producto nuevo");
            System.out.println("5.Ya tengo mis productos");
            System.out.println("6.Cancelar registro de venta");
            System.out.print("Ingrese una opción: ");
            int opcionProducto = scanner.nextInt();
            scanner.nextLine();
            switch (opcionProducto){
                case 1:
                    buscarProductoByCodigo(productos);
                    break;
                case 2:
                    System.out.println("Ingrese nombre del producto:");
                    String nombre = scanner.nextLine();
                    serProducto.buscarByNombre(nombre).forEach(System.out::println);
                    buscarProductoByCodigo(productos);
                    break;
                case 3:
                    System.out.println("Ingrese categoria del producto:");
                    String categoria = scanner.nextLine();
                    serProducto.buscarByCategoria(categoria).forEach(System.out::println);
                    buscarProductoByCodigo(productos);
                    break;
                //case 4: produ=crearProducto();break;
                case 5:salir=true;
                case 6:return null;
                default:System.out.println("\nOpción inválida.");break;
            }
        }while(!salir);
        return productos;
    }

    //ver si se actualiza la variable productos igual:
    private static void buscarProductoByCodigo(List<Producto> productos){
        System.out.println("Ingrese codigo del producto:");
        String codigo = scanner.nextLine();
        try{
            Producto produ=serProducto.buscarByCodigo(codigo);
            productos.add(produ);
            System.out.println("Producto añadido al carrito: "+produ);
            System.out.println("Puedes seguir añadiendo productos");
        }catch(ProductoException ex){
            System.out.println(ex.getMessage());
        }
    }

}