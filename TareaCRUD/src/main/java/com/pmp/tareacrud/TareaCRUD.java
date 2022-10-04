/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.pmp.tareacrud;

import java.util.Scanner;
import java.util.ArrayList;
import com.pmp.tareacrud.db.DatosUsuarioDb;

/**
 *
 * @author Naidelyn
 */
public class TareaCRUD {
    
    public static Scanner input;
    public static ArrayList<DatosUsuario> arrDatosUsuario;
    public static DatosUsuarioDb UsuarioDao;
    
    public static void main(String[] args) {
        input = new Scanner (System.in );
        arrDatosUsuario = new ArrayList();
        UsuarioDao = new DatosUsuarioDb();
        
        TareaUX.header("CRUD APP - Datos de Usuarios");        
        String opcionSelect = "";
        while(!opcionSelect.equalsIgnoreCase("S")){
                TareaUX.Separador();
                System.out.println(" ");
                System.out.println("Usuarios registrados en nuestra base de datos");
                System.out.println(" ");
                mostrarUsuarios();
                System.out.println(" ");
                TareaUX.Menu();
                opcionSelect = input.nextLine();
                Controles(opcionSelect);
        }
    }
    
    public static void Controles(String opcionSelect){
        arrDatosUsuario = UsuarioDao.obtenerDatos();
        switch (opcionSelect.toUpperCase()) {
        case "N": 
            TareaUX.Separador();
            System.out.println("Llena los datos para crear un nuevo Usuario");
            System.out.println(" ");
            nuevoUsuario();
            break;
        case "A":
            TareaUX.Separador();
            System.out.println("Llena todos los datos y cambia los valores que quieras \n "
                    + "si quieres dejar la misma información pon el mismo dato que se te muestra en pantalla ");
            System.out.println(" ");
            actualizarUsuario();
            break;
        case "E":
            TareaUX.Separador();
            System.out.println("Elimina un usuario");
            System.out.println(" ");
            eliminarUsuario();     
            break;
        case "S":
            System.out.println("\t \t Esperamos verte pronto :D");
            break;
        default:
            TareaUX.header("Opción no Válida");
        }
    }
    
    public static DatosUsuario inputForm(DatosUsuario baseDatos) {
        
        baseDatos.setNombreUsuario(
            TareaUX.getFieldInput(input, "Nombre de Usuario", baseDatos.getNombreUsuario())
        );
        
        baseDatos.setNombre(
            TareaUX.getFieldInput(input, "Primer Nombre", baseDatos.getNombre())
        );
        
        baseDatos.setApellido(
            TareaUX.getFieldInput(input, "Primer Apellido", baseDatos.getApellido())
        );
        
        baseDatos.setTelefono(
            TareaUX.getFieldInput(input, "Número de Teléfono", baseDatos.getTelefono())
        );
        
        baseDatos.setFechanacimiento(
            TareaUX.getFieldInput(input, "Fecha de Nacimiento", baseDatos.getFechanacimiento())
        );
        
        baseDatos.setEdad(
                Integer.parseInt(
            TareaUX.getFieldInput(input, "Edad", baseDatos.getEdad().toString())
                )
        );
        
        return baseDatos;
    } 
    
    private static DatosUsuario validarEntradaRegistro() {
        if (arrDatosUsuario.isEmpty()) {
            System.out.println("No Hay Datos!");
            return null;
        }
        int index = Integer.parseInt(
            TareaUX.getFieldInput(input, "Id de usuario: ", "1")
        );
        System.out.println(" ");
        for (int i = 0; i < arrDatosUsuario.size(); i++){
            if (index == arrDatosUsuario.get(i).getId()) {
                return arrDatosUsuario.get(i);
            }
        }
        return null;
    }
    
    public static void nuevoUsuario(){
        
        DatosUsuario newDatosUsuario = new DatosUsuario();
        
        newDatosUsuario.setNombreUsuario("Juan_Prz");
        newDatosUsuario.setNombre("Juan");
        newDatosUsuario.setApellido("Peréz");
        newDatosUsuario.setTelefono("9999-9999");
        newDatosUsuario.setFechanacimiento("01-01-2000");
        newDatosUsuario.setEdad(18);
        
        newDatosUsuario = inputForm(newDatosUsuario);
        UsuarioDao.insertUsuario(newDatosUsuario);
        arrDatosUsuario.add(newDatosUsuario);
    }
    
    private static void mostrarUsuarios() {
        arrDatosUsuario = UsuarioDao.obtenerDatos();
        if (arrDatosUsuario.size() > 0 ) {
            for ( int i = 0; i < arrDatosUsuario.size(); i++) {
                DatosUsuario usuario = arrDatosUsuario.get(i);
                System.out.println(
                        String.valueOf(i+ 1) + " -- " +
                        usuario.getNombreUsuario()
                );
            }
        } else {
            System.out.println("No hay datos que mostrar.");
        }
    }
    
    private static void actualizarUsuario() {
        DatosUsuario updateDatosUsuario = validarEntradaRegistro();
        if(updateDatosUsuario!=null){
            updateDatosUsuario = inputForm(updateDatosUsuario);
            UsuarioDao.updateUsuario(updateDatosUsuario);
            System.out.println("Datos Actualizados Correctamente");
        }
    }
    
    private static void eliminarUsuario(){
        DatosUsuario deleteDatosUsuario = validarEntradaRegistro();
        if (deleteDatosUsuario != null) {
            UsuarioDao.deleteUsuario(deleteDatosUsuario);
            System.out.println("Usuario Eliminado Correctamente");
        }
    }
    
    
}
