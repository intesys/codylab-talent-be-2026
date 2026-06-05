package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.LibroModel;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroJdbcRepository {

    private final DataSource dataSource;

    public LibroJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<LibroModel> findAll() {
        String sql = "SELECT id, titolo, autore, lingua, created_at, updated_at FROM libro ORDER BY id";
        List<LibroModel> result = new ArrayList<>();

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql);
             var rs = statement.executeQuery()) {
            while (rs.next()) {
                result.add(map(rs));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante findAll su libro", e);
        }
        return result;
    }

    public Optional<LibroModel> findById(long id) {
        String sql = "SELECT id, titolo, autore, lingua, created_at, updated_at FROM libro WHERE id = ?";

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante findById su libro", e);
        }
    }

    public long insert(LibroModel libro) {
        String sql = "INSERT INTO libro (titolo, autore, lingua) VALUES (?, ?, ?) RETURNING id";

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTitolo());
            statement.setString(2, libro.getAutore());
            statement.setString(3, libro.getLingua());
            try (var rs = statement.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante insert su libro", e);
        }
    }

    public boolean update(LibroModel libro) {
        String sql = "UPDATE libro SET titolo = ?, autore = ?, lingua = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTitolo());
            statement.setString(2, libro.getAutore());
            statement.setString(3, libro.getLingua());
            statement.setLong(4, libro.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante update su libro", e);
        }
    }

    public boolean deleteById(long id) {
        String sql = "DELETE FROM libro WHERE id = ?";

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante deleteById su libro", e);
        }
    }

    private LibroModel map(ResultSet rs) throws SQLException {
        return new LibroModel()
                .setId(rs.getLong("id"))
                .setTitolo(rs.getString("titolo"))
                .setAutore(rs.getString("autore"))
                .setLingua(rs.getString("lingua"))
                .setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime())
                .setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
    }
}

