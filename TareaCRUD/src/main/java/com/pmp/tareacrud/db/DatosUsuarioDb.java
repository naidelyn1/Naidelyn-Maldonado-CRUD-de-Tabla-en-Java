/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.tareacrud.db;

import java.sql.Connection;
import java.sql.Statement;
import java.lang.Exception;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import com.pmp.tareacrud.DatosUsuario;

/**
 *
 * @author Naidelyn
 */
public class DatosUsuarioDb {
    
    /*
    
    id;
    nombreUsuario;
    nombre;
    apellido;
    telefono;
    fechanacimiento;
    edad;
    
    */
    
    private Connection conn = null;
    
    public DatosUsuarioDb(){
        try {
        conn = Conexion.getConnection();
        String sqlCrearTabla = "CREATE TABLE  IF NOT EXISTS DATOSUSUARIO(" +
        "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
        "    nombreUsuario TEXT," +
        "    nombre TEXT," +
        "    apellido TEXT," +
        "    telefono TEXT," +
        "    fechanacimiento DATE," +
        "    edad INTEGER);";  
        Statement comandoSQLCREATE = conn.createStatement();
        comandoSQLCREATE.executeUpdate(sqlCrearTabla);
        comandoSQLCREATE.close();
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    public void insertUsuario(DatosUsuario newUsuario){
        try{
            String sqlInsert = "INSERT INTO DATOSUSUARIO (nombreUsuario, " +
                    "nombre, apellido, telefono, fechanacimiento, edad)" +
                    "values ( ?, ?, ?, ?, ?, ? )";
            PreparedStatement comandoInsert = conn.prepareStatement(sqlInsert);
            
            comandoInsert.setString(1, newUsuario.getNombreUsuario());
            comandoInsert.setString(2, newUsuario.getNombre());
            comandoInsert.setString(3, newUsuario.getApellido());
            comandoInsert.setString(4, newUsuario.getTelefono());
            comandoInsert.setString(5, newUsuario.getFechanacimiento());
            comandoInsert.setInt(6, newUsuario.getEdad());

            int registroAgregados = comandoInsert.executeUpdate();
            System.out.println(registroAgregados);
            System.out.println(" ");
            System.out.println("Usuario Creado Exitosamente");
        
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public void updateUsuario(DatosUsuario updateUsuario) {
        try {
            String sqlStrUpdate = "UPDATE DATOSUSUARIO set nombreUsuario = ? ," +
                " nombre = ?," +
                " apellido = ?," +
                " telefono = ?," +
                " fechanacimiento = ?," +
                " edad = ?," +
                "where id = ?;";
            PreparedStatement comando = this.conn.prepareStatement(sqlStrUpdate);
            comando.setString(1, updateUsuario.getNombreUsuario());
            comando.setString(2, updateUsuario.getNombre());
            comando.setString(3, updateUsuario.getApellido());
            comando.setString(4, updateUsuario.getTelefono());
            comando.setString(5, updateUsuario.getFechanacimiento());
            comando.setInt(6, updateUsuario.getEdad());
            comando.setInt(7, updateUsuario.getId());
            comando.executeUpdate();
            comando.close();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void deleteUsuario(DatosUsuario deleteUsuario) {
        try {
            String sqlStrDelete = "DELETE FROM DATOSUSUARIO where id = ?;";
            PreparedStatement comando = this.conn.prepareStatement(sqlStrDelete);
            comando.setInt(1, deleteUsuario.getId());
            comando.executeUpdate();
            comando.close();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<DatosUsuario> obtenerDatos() {
        try {
            String sqlstr = "SELECT * from DATOSUSUARIO;";
            Statement comando = conn.createStatement();
            ArrayList usuarios = new ArrayList<DatosUsuario>();
            ResultSet registros = comando.executeQuery(sqlstr);
            while (registros.next()){
                DatosUsuario datosUsuario = new DatosUsuario();
                datosUsuario.setId(registros.getInt("id"));
                datosUsuario.setNombreUsuario(registros.getString("nombreUsuario"));
                datosUsuario.setNombre(registros.getString("nombre"));
                datosUsuario.setApellido(registros.getString("apellido"));
                datosUsuario.setTelefono(registros.getString("telefono"));
                datosUsuario.setFechanacimiento(registros.getString("fechanacimiento"));
                datosUsuario.setEdad(registros.getInt("edad"));
                usuarios.add(datosUsuario);
            }
            return usuarios;
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            return new ArrayList<DatosUsuario>();
        }
    }
}
