package modelo;


import java.math.BigDecimal;

public class Libro {


    private int id;
    private String nombre;
    private int genero_id;



    // Constructor vacío
    public Libro() {}


    // Constructor completo
    public Libro(int id, String nombre, int genero_id) {
        this.id = id;
        this.nombre = nombre;
        this.genero_id = genero_id;
    }



    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void getGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }

    public int getGenero_id() {
        return genero_id;
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", genero_id ='" + genero_id + '\'' +
                '}';
    }
}
