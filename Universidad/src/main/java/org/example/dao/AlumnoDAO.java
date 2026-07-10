package org.example.dao;

import org.example.config.Conexion;
import org.example.modelo.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnoDAO
{
    public boolean nuevoAlumno(Alumno alumno){
        boolean registrado=false;
        String sql="INSERT INTO alumnos VALUES (?,?,?,?,?)";

        try(Connection conexion= Conexion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql);){
            stm.setInt(1,alumno.getNumeroExpediente());
            stm.setString(2,alumno.getNombre());
            stm.setInt(3,alumno.getEdad());
            stm.setString(4,alumno.getCarrera());
            stm.setInt(5,alumno.getCuatrimestre());
            stm.executeUpdate();
        }
        catch (SQLException err){
            System.out.println("Error al agregar al alumno "+err.getMessage());

        }
        return registrado;
    }

    public ArrayList<Alumno> extraerAlumnos(){
        ArrayList<Alumno>alumnosBD = new ArrayList<Alumno>();
        String sql="SELECT * FROM alumnos";
        try (Connection conexion = Conexion.conectar();
        PreparedStatement stm = conexion.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()){
            while (rs.next()){
                Alumno alumno = new Alumno();
                alumno.setNumeroExpediente(rs.getInt("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setEdad(rs.getInt("edad"));
                alumno.setCarrera(rs.getString("carrera"));
                alumno.setCuatrimestre(rs.getInt("cuatrimestre"));
                alumnosBD.add(alumno);
            }

        }
        catch (SQLException err){
            System.out.println("Error al extraer los datos: "+err.getMessage());
        }
        return alumnosBD;
    }
    public boolean actualizarAlumno(Alumno alumno){
        boolean actualizado = false;
        String sql = "UPDATE alumnos SET nombre = ?, edad = ?, carrera = ?, cuatrimestre = ? WHERE numExpediente = ?" ;
        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql);){
            stm.setString(1, alumno.getNombre());
            stm.setInt(2, alumno.getEdad());
            stm.setString(3, alumno.getCarrera());
            stm.setInt(4, alumno.getCuatrimestre());
            stm.setInt(5,alumno.getNumeroExpediente());
            int registrosAfectados =stm.executeUpdate();
            if(registrosAfectados > 0) {
                System.out.println("Alumno actualizado correctamente");
                actualizado=true;
            }
            else {
                System.out.println("No se encontro este numero de expediente");
            }

        }
        catch (SQLException err) {
            System.out.println("Error al actualilx=ar al alumno" + err.getMessage());
        }

        return actualizado;
    }
    public boolean borrarAlumno(Alumno alumno) {
        boolean actual = false;
        String sql = "DELETE FROM alumnos WHERE numExpediente = ?";
        try (Connection conexion = Conexion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql);) {
            stm.setInt(1, alumno.getNumeroExpediente());
            int registrosAfectados = stm.executeUpdate();
            if (registrosAfectados > 0) {
                System.out.println("Alumno borrado correctamente");
                actual = true;
            } else {
                System.out.println("No se encontro este numero de expediente");
            }

        } catch (SQLException err) {
            System.out.println("Error al borrar al alumno" + err.getMessage());
        }

        return actual;
    }
    public boolean buscarAlumno(Alumno alumno){
        boolean buscar=false;
        String sql="SELECT * FROM alumnos WHERE numExpediente = ?";
        try(Connection conexion = Conexion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql);){
            stm.setInt(1,alumno.getNumeroExpediente());
            try(ResultSet rs = stm.executeQuery()) {
                if(rs.next()) {
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setEdad(rs.getInt( "edad"));
                    alumno.setCarrera(rs.getString("carrera"));
                    alumno.setCuatrimestre(rs.getInt("cuatrimestre"));
                    buscar=true;
                }
            }
        }
        catch(SQLException err){
            System.out.println("Error al buscar al alumno" +err.getMessage());
        }
        return buscar;
    }

}
