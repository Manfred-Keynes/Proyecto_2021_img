/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Lenovo FX
 */
public abstract class Persona {
    private int id;
    private String nombres,apellidos,telefono;
    public String genero;

    public Persona() {}

    public Persona(int id, String nombres, String apellidos, String telefono, String genero) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.genero = genero;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        //Case para asignar cuando es Masculino y cuando Femenino.
           /* switch(gen) {
                case 'M':
                    genero = "1";
                    break;
                
                case 'F':
                    genero = "0";
                    break;
            }*/
        
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public int agregar() {return 0;}
    public int modificar() {return 0;}
    public int eliminar() {return 0;}
}
