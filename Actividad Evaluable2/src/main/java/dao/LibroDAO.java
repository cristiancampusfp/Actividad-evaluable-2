package dao;
import java.sql.*;
import config.DatabaseConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.Libro;

public class LibroDAO {

    public static int crear(Libro libro) {
        String sql = "INSERT INTO Libro(nombre, genero_id) VALUES (?,?)";

        try (Connection con = DatabaseConfig.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            // Asignación segura de parámetros
            ps.setString(1, libro.getNombre());
            ps.setInt(2, libro.getGenero_id());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }


        } catch (SQLException e) {
            System.out.println("Error al crear Libro: " + e.getMessage());
        }
        return -1; // Indica error
    }



    public static Optional<Libro> obtenerPorId(int id) {
        String sql = "SELECT * FROM Libro WHERE id = ?";


        try (Connection con = DatabaseConfig.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setInt(1, id);


            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Libro emp = new Libro(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("genero_id")
                    );
                    return Optional.of(emp);
                }
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener Libro por id: " + e.getMessage());
        }
        return Optional.empty();
    }


    //Consulta Select
    public static List<Libro> obtenerTodos() {
        List<Libro> lista = new ArrayList<>();


        String sql = "SELECT * FROM Libro";


        try (Connection con = DatabaseConfig.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {
                Libro emp = new Libro(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("genero_id")

                );
                lista.add(emp);
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener Libros: " + e.getMessage());
        }


        return lista;
    }

    public boolean actualizar(Libro libro) {
        String sql = "UPDATE Libro SET nombre=?, genero_id=? WHERE id=?";


        try (Connection con = DatabaseConfig.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setString(1, libro.getNombre());
            ps.setInt(2, libro.getGenero_id());


            return ps.executeUpdate() > 0;


        } catch (SQLException e) {
            System.out.println("Error al actualizar libro" + e.getMessage());
            return false;
        }
    }
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Libro WHERE id=?";


        try (Connection con = DatabaseConfig.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setInt(1, id);
            return ps.executeUpdate() > 0;


        } catch (SQLException e) {
            System.out.println("Error al eliminar Libro: " + e.getMessage());
            return false;
        }
    }
}



