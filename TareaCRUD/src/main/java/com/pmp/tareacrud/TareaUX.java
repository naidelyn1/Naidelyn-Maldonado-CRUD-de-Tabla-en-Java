/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.tareacrud;

import java.util.Scanner;

/**
 *
 * @author Naidelyn
 */
public class TareaUX {
    
    public static void Separador(){
        System.out.println("***************************************************************");
    }
    
    public static void header(String titulo) {
        Separador();
        System.out.println(titulo);
    }
    
    public static void Menu (){
        header("Menú de Opciones");
        Separador();
        System.out.println("( N ) \t Crear nuevo usuario.");
        System.out.println("( A ) \t Actualizar datos de un usuario.");
        System.out.println("( E ) \t Eliminar un usuario.");
        Separador();
        System.out.println("( S ) \t Salir del programa.");
        Separador();
        System.out.println("Escriba una opción y presione enter para continuar:");
    }
    
    public static String getFieldInput(Scanner input, String label, String defaultValue) {
        System.out.println(label + " (" + defaultValue + "): \t");
        String inputValue = input.nextLine();
        if (inputValue.isBlank()) {
            return defaultValue;
        }
        return inputValue.strip();
    }
}
