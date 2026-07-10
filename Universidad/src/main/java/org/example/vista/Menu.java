package org.example.vista;

import org.example.dao.AlumnoDAO;
import org.example.dao.MaestroDAO;
import org.example.modelo.Alumno;
import org.example.modelo.Maestro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
    static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
    static AlumnoDAO alumnoDAO = new AlumnoDAO();
    static Alumno alumno = new Alumno();
    static MaestroDAO maestroDAO = new MaestroDAO();
    static Maestro maestro = new Maestro();

    public static void inscribir() throws IOException{
        System.out.println("Numero de expediente: ");
        alumno.setNumeroExpediente(Integer.parseInt(leer.readLine()));
        System.out.println("Nombre: ");
        alumno.setNombre(leer.readLine());
        System.out.println("Edad: ");
        alumno.setEdad(Integer.parseInt(leer.readLine()));
        System.out.println("Carrera: (TI, Qui, Mec, Mkt)");
        alumno.setCarrera(leer.readLine());
        System.out.println("Cuatrimestre");
        alumno.setCuatrimestre(Integer.parseInt(leer.readLine()));
        alumnoDAO.nuevoAlumno(alumno);
    }

    public static void mostrar(){
        ArrayList<Alumno> alumnos = alumnoDAO.extraerAlumnos();
        System.out.println("-----------------Lista de alumnos inscritos-----------------");
        for (Alumno alumno: alumnos){
            System.out.println(alumno);
        }
    }

    public static void modificar() throws IOException{
        System.out.println("Modificar alumno por numero de expediente");
        System.out.println("Numero de expediente: ");
        alumno.setNumeroExpediente(Integer.parseInt(leer.readLine()));
        System.out.println("Nombre: ");
        alumno.setNombre(leer.readLine());
        System.out.println("Edad: ");
        alumno.setEdad(Integer.parseInt(leer.readLine()));
        System.out.println("Carrera: (TI, Qui, Mec, Mkt) ");
        alumno.setCarrera(leer.readLine());
        System.out.println("Cuatrimestre: ");
        alumno.setCuatrimestre(Integer.parseInt(leer.readLine()));
        alumnoDAO.actualizarAlumno(alumno);
    }

    public static void borrar() throws IOException{
        System.out.println("Borrar alumno por numero de expediente");
        System.out.println("Numero de expediente: ");
        alumno.setNumeroExpediente(Integer.parseInt(leer.readLine()));
        alumnoDAO.borrarAlumno(alumno);
    }

    public static void buscar() throws IOException {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        System.out.println("-------Buscar alumno-------");
        Alumno alumnoBuscar = new Alumno();
        System.out.print("Número de expediente del alumno: ");
        alumnoBuscar.setNumeroExpediente(Integer.parseInt(leer.readLine()));
        boolean encontrado = alumnoDAO.buscarAlumno(alumnoBuscar);
        if (encontrado) {
            System.out.println("\n[Alumno encontrado: ]");
            System.out.println(alumnoBuscar);
        } else {
            System.out.println("No se encontró ningún alumno con ese número de expediente.");
        }
    }

    public static void ingresarMaestro()throws IOException{
        maestro = new Maestro();
        System.out.println("Numero de empleado");
        maestro.setNumEmpleado(Integer.parseInt(leer.readLine()));
        System.out.println("Edad");
        maestro.setEdad(Integer.parseInt(leer.readLine()));
        System.out.println("Nombre");
        maestro.setNombre(leer.readLine());
        System.out.println("Cedula profesional");
        maestro.setCedProfecional(leer.readLine());
        maestroDAO.nuevoMaestro(maestro);
    }

    public static void mostrarMaestro(){
        ArrayList<Maestro> maestros = maestroDAO.extraerMaestro();
        System.out.println("-----------------Lista de maestros-----------------");
        for (Maestro maestro : maestros){
            System.out.println(maestro);
        }
    }

    public static void modificarMaestro() throws IOException{
        System.out.println("ingresa el nuero de empleado que quieres modificar");
        maestro.setNumEmpleado(Integer.parseInt(leer.readLine()));
        System.out.println("<----Modificaciones---->");
        System.out.println("Nombre");
        maestro.setNombre(leer.readLine());
        System.out.println("edad");
        maestro.setEdad(Integer.parseInt(leer.readLine()));
        System.out.println("Cedula profesional");
        maestro.setCedProfecional(leer.readLine());
        maestroDAO.updateMae(maestro);
    }

    public static void borrarMaestro() throws IOException{
        System.out.println("Ingresa el numero de empleado del maestro que quieres eliminar");
        maestro.setNumEmpleado(Integer.parseInt(leer.readLine()));
        maestroDAO.deleteMae(maestro);
    }

    public static void buscarMaestro() throws IOException{
        System.out.println("Ingresa el numero de expediente del maestro que quieres buscar");
        maestro.setNumEmpleado(Integer.parseInt(leer.readLine()));
        ArrayList<Maestro> maestros_Lista = maestroDAO.buscarMae(maestro);
        System.out.println("-------------- Lista de maestros --------------");
        for (Maestro maestro : maestros_Lista){
            System.out.println(maestro);
        }
    }

    public static void menu()throws IOException {
        int salir=0;
        while (salir!=11){
            System.out.println("1. Inscribir nuevo alumno");
            System.out.println("2. Mostrar todos los alumnos");
            System.out.println("3. Modificar un alumno");
            System.out.println("4. Borrar un alumno");
            System.out.println("5. Buscar un alumno");
            System.out.println("6. Ingresar un nuevo maestro");
            System.out.println("7. Mostrar todos los maestros");
            System.out.println("8. Modificar maestro");
            System.out.println("9. Borrar un maestro");
            System.out.println("10. Buscar un maestro");
            System.out.println("11. Salir");
            salir = Integer.parseInt(leer.readLine());
            switch (salir){
                case 1: inscribir();break;
                case 2: mostrar();break;
                case 3: modificar();break;
                case 4: borrar();break;
                case 5: buscar();break;
                case 6: ingresarMaestro();break;
                case 7: mostrarMaestro();break;
                case 8: modificarMaestro();break;
                case 9: borrarMaestro();break;
                case 10: buscarMaestro();break;
                case 11:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opcion no valid0a");
                    break;
            }
        }
    }
}