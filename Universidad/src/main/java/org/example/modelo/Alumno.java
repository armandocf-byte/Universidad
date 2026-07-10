package org.example.modelo;

import java.util.Locale;

public class Alumno {
    private int numeroExpediente;
    private String nombre;
    private int edad;
    private String carrera;
    private int cuatrimestre;

    private boolean resgistroCorrecto = false;

    private static final String[] CARRERAS_VALIDAS = {"TI", "Qui", "Mec", "Mkt"};

    public Alumno() {
    }

    public Alumno(int numeroExpediente, String nombre, int edad, String carrera, int cuatrimestre) {
        setNumeroExpediente(numeroExpediente);
        setNombre(nombre);
        setEdad(edad);
        setCarrera(carrera);
        setCuatrimestre(cuatrimestre);
    }

    public int getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(int numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getNombre() {
        return nombre.toUpperCase(Locale.ROOT);
    }

    public void setNombre(String nombre) {
        if (nombre.isBlank() || nombre.isEmpty()) {
            System.out.println("Error, el nombre es requerido");
        } else {
            this.nombre = nombre;

        }
    }

    public int getEdad() {
        return edad;

    }

    public void setEdad(int edad) {
        if (edad > 15 && edad < 110) {
            this.edad =edad;

        } else {
            System.out.println("La edad debe de estar entre 15 y 110 años de edad");
        }
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        if (validarCarreras(carrera)) {
            this.carrera = carrera;
        }
        else {
            System.out.println("Carrera invalilda");
        }
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        if (cuatrimestre>0 && cuatrimestre<12) {
            this.cuatrimestre = cuatrimestre;
        }
        else {
            System.out.println("El cuatrimestre debe de ser entre 1 y 11");
        }
    }

    @Override
    public String toString() {
        return "numeroExpediente: " + numeroExpediente+'\n'+
                "nombre: "+ nombre+ '\n'+
                "edad: "+ edad + '\n'+
                "Carrera: " + carrera + '\n'+
                "Cuatrimestre: " + cuatrimestre + '\n'+
                "--------------------------------------";
    }
    public boolean validarCarreras(String carrera){
        boolean carreraValida=false;
        for(String validar : Alumno.CARRERAS_VALIDAS)
            if (carrera.equalsIgnoreCase(validar)) {
                return true;}

        return carreraValida;
    }
}