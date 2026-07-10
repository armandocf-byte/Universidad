package org.example.dao;

import org.example.modelo.Maestro;
import org.example.config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaestroDAO {
    public boolean nuevoMaestro(Maestro maestro) {
        boolean registrado = false;
        String sql = "INSERT INTO maestros VALUES(?,?,?,?)";

        try(Connection conexion = Conexion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, maestro.getNumEmpleado());
            stm.setString(2, maestro.getNombre());
            stm.setInt(3, maestro.getEdad());
            stm.setString(4, maestro.getCedProfecional());

            stm.executeUpdate();
            registrado = true;
        }
        catch (SQLException err){
            System.out.println("Error al agregar al maestro: " + err.getMessage());
        }
        return registrado;
    }


    public boolean updateMae(Maestro maestro){
        boolean actualizado = false;

        String sql = "UPDATE maestros SET edad = ?, nombre = ?, cedulaProfesional = ? WHERE numEmpleado = ?";

        try(Connection conexion = Conexion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, maestro.getEdad());
            stm.setString(2, maestro.getNombre());
            stm.setString(3, maestro.getCedProfecional());
            stm.setInt(4, maestro.getNumEmpleado());

            int registrosAfectados = stm.executeUpdate();
            if (registrosAfectados > 0){
                actualizado = true;
                System.out.println("Maestro actualizado correctamente");
            } else {
                System.out.println("No se encontró el número de empleado");
            }

        } catch(SQLException err){
            System.out.println("Error al actualizar los datos del maestro: " + err.getMessage());
        }
        return actualizado;
    }

    public boolean deleteMae(Maestro maestro){
        boolean maestroEliminado = false;
        String sql = "DELETE FROM maestros WHERE numEmpleado = ?";

        try(Connection conexion = Conexion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, maestro.getNumEmpleado());
            int maeEliminado = stm.executeUpdate();
            if(maeEliminado > 0){
                maestroEliminado = true;
                System.out.println("Maestro eliminado correctamente");
            } else {
                System.out.println("No se encontró este empleado");
            }

        } catch(SQLException err){
            System.out.println("Error al eliminar maestro: " + err.getMessage());
        }
        return maestroEliminado;
    }

    public ArrayList<Maestro> extraerMaestro(){
        ArrayList<Maestro> maestroBD = new ArrayList<>();
        String sql = "SELECT * FROM maestros";

        try(Connection conexion = Conexion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql);
            ResultSet rs = stm.executeQuery()) {

            while(rs.next()) {
                Maestro maestro = new Maestro();
                maestro.setNumEmpleado(rs.getInt("numEmpleado"));
                maestro.setNombre(rs.getString("nombre"));
                maestro.setEdad(rs.getInt("edad"));
                maestro.setCedProfecional(rs.getString("cedulaProfesional"));

                maestroBD.add(maestro);
            }
        } catch(SQLException err){
            System.out.println("Error al extraer los datos: " + err.getMessage());
        }
        return maestroBD;
    }


    public ArrayList<Maestro> buscarMae(Maestro maestro) {
        ArrayList<Maestro> maestroBD2 = new ArrayList<Maestro>();
        String sql = "SELECT * FROM maestros WHERE numEmpleado = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, maestro.getNumEmpleado());

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Maestro maestro2 = new Maestro();
                    maestro2.setNumEmpleado(rs.getInt("numEmpleado"));
                    maestro2.setNombre(rs.getString("nombre"));
                    maestro2.setEdad(rs.getInt("edad"));
                    maestro2.setCedProfecional(rs.getString("cedulaProfesional"));

                    maestroBD2.add(maestro2);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar maestro: " + e.getMessage());
            e.printStackTrace();
        }

        return maestroBD2;
    }
}