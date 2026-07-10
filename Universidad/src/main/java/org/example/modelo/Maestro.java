package org.example.modelo;

public class Maestro {
    private int numEmpleado;
    private int edad;
    private String cedulaProfecional;
    private String nombre;

    private boolean registroMaestro = false;
    private static final String[] PUESTOS_VALIDOS = {"Tutor", "Asesor", "Coordinador"};


    public Maestro(){

    }

    public Maestro(int numEmpleado, int edad, String puesto, String cedProfecional, String nombre) {
        setNumEmpleado(numEmpleado);
        setEdad(edad);
        setCedProfecional(cedProfecional);
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.isBlank() || nombre.isEmpty()){
            System.out.println("El nombre es requerido");
        }else{
            this.nombre=nombre;
            registroMaestro = true;
        }
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        if (numEmpleado == 0){
            System.out.println("El numero de empleado es requerido");
        }else{
            this.numEmpleado= numEmpleado;
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad>17 && edad <109){
            this.edad=edad;
        }else {
            System.out.println("La edad requerida es entre 18 y 99 años");
        }
    }



    public String getCedProfecional() {
        return cedulaProfecional;
    }

    public void setCedProfecional(String cedProfecional) {
        this.cedulaProfecional = cedProfecional;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + '\n' +
                "numEmpleado: " + numEmpleado + "\n" +
                "edad: " + edad + "\n" +
                "cedProfecional: " + cedulaProfecional;

    }
}