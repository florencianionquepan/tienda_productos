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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Producto> productos= ProductoLoader.cargarProductos();
    static IProductoRepoMemo repoProducto=new ProductoRepoMemo(productos);
    //static IProductoService serProducto=new ProductoService(repoProducto);

    static List<Vendedor> vendedores= VendedorLoader.cargarVende();
    static IVendedorRepoMemo repoVendedor=new VendedorRepoMemo(vendedores);
    static IVendedorService serVende=new VendedorService(repoVendedor);

    static List<Venta> ventas= VentaLoader.cargarVentas();
    static IVentaRepoMemo repoVenta=new VentaRepoMemo(ventas);
    static IVentaService serVenta=new VentaService(repoVenta, repoProducto);

    static Venta ventaUno=VentaLoader.getVentaUno();
    static Venta ventaDos=VentaLoader.getVentaDos();

    static IProductoService actualizarVentasProductos(){
        List<Producto> productos=new ArrayList<>();
        //Crear nueva lista de productos actualizados:
        List<Producto> productosActualizados = ProductoLoader.cargarProductos();
        productosActualizados.get(0).setVentas(new ArrayList<>(Arrays.asList(ventaUno)));
        productosActualizados.get(1).setVentas(new ArrayList<>(Arrays.asList(ventaUno,ventaDos)));
        productosActualizados.get(2).setVentas(new ArrayList<>(Arrays.asList(ventaDos)));
        productosActualizados.get(3).setVentas(new ArrayList<>(Arrays.asList(ventaDos)));
        //System.out.println(productosActualizados);
        IProductoRepoMemo repoProductoActualizado = new ProductoRepoMemo(productosActualizados);
        return new ProductoService(repoProductoActualizado);
    }

    static IProductoService serProducto=actualizarVentasProductos();
    static int opcion;
    static int opcionPro;
    static int opcionVende;
    static int opcionVenta;
    static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {
        do{
            menu();
            accion();
        }while(opcion!=0);
    }

    //MENU Y ACCION GENERAL
    static public void menu(){
        System.out.println("\nMENU GENERAL:");
        System.out.println("1. Ir a menu productos");
        System.out.println("2. Ir a menu vendedor");
        System.out.println("3. Ir a menu ventas");
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
            opcionPro = scanner.nextInt();
            scanner.nextLine();
    }

    static public boolean accionProducto(){
        boolean volver=false;
        switch (opcionPro){
            case 1 : ingresarProducto(); break;
            case 2 : serProducto.listar().forEach(System.out::println);; break;
            case 0 : volver=true;break;
            default : System.out.println("Error en la opcion");break;
        }
        return volver;
    }

    static public void ingresarProducto(){
        System.out.println("\nCREAR NUEVO PRODUCTO:");
        System.out.print("Ingrese el código: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio: ");
        float precio = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Ingrese la categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();

        Producto nuevoProducto = new Producto(0L,codigo, nombre,
                precio, categoria, cantidad,new ArrayList<Venta>());
        Producto creado=serProducto.crear(nuevoProducto);
        System.out.println("\nProducto creado con éxito.\n"+creado);
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
            case 2 : serVende.listar().forEach(System.out::println);; break;
            case 0 : volver=true;break;
            default : System.out.println("Error en la opcion");break;
        }
        return volver;
    }

    static public void crearVendedor(){
        System.out.println("\nCREAR NUEVO VENDEDOR:");
        System.out.print("Ingrese el codigo: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el sueldo: ");
        float sueldo = scanner.nextFloat();

        Vendedor nuevoVendedor = new Vendedor(0L,codigo,nombre,sueldo);
        Vendedor vendeCreado=serVende.crear(nuevoVendedor);
        System.out.println("\nVendedor creado con éxito.\n" + vendeCreado);
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
        System.out.println("2. Listar ventas");
        System.out.println("0. Volver al menu principal");
        System.out.print("\nIngrese una opción: ");
        opcionVenta = scanner.nextInt();
        scanner.nextLine();
    }

    static public boolean accionVenta(){
        boolean volver=false;
        switch (opcionVenta){
            case 1 : registrarVenta(); break;
            case 2: serVenta.listar().forEach(System.out::println);
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
            System.out.println("4.Buscar producto por rango de precios");
            System.out.println("5.Crear producto nuevo");
            System.out.println("6.Ya tengo mis productos");
            System.out.println("7.Cancelar registro de venta");
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
                    try{
                        serProducto.buscarByNombre(nombre).forEach(System.out::println);
                        buscarProductoByCodigo(productos);
                    }catch(ProductoException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Ingrese categoria del producto:");
                    String categoria = scanner.nextLine();
                    try{
                        serProducto.buscarByCategoria(categoria).forEach(System.out::println);
                        buscarProductoByCodigo(productos);
                    }catch(ProductoException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Ingrese precio minimo: ");
                    float desde= scanner.nextFloat();
                    System.out.println("Ingrese precio maximo: ");
                    float hasta= scanner.nextFloat();
                    try{
                        serProducto.buscarByRangoPrecio(desde,hasta).forEach(System.out::println);
                        buscarProductoByCodigo(productos);
                    }catch(ProductoException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5: ingresarProducto();
                        System.out.println("Si desea ahora puede ingresar al carrito.");
                        ;break;
                case 6:salir=true;
                case 7:return null;
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