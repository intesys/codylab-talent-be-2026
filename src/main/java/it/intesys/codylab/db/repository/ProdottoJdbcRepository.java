package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.ProdottoModel;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdottoJdbcRepository {

    private final DataSource dataSource;

    public ProdottoJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ProdottoModel> findAll() {
        String sql = "SELECT id, descrizione, prezzo, created_at, updated_at FROM prodotto ORDER BY id";
        List<ProdottoModel> result = new ArrayList<>();

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql);
             var rs = statement.executeQuery()) {
            while (rs.next()) {
                result.add(map(rs));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante findAll su prodotto", e);
        }
        return result;
    }

    public Optional<ProdottoModel> findById(long id) {
        String sql = "SELECT id, descrizione, prezzo, created_at, updated_at FROM prodotto WHERE id = ?";

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
            throw new IllegalStateException("Errore durante findById su prodotto", e);
        }
    }

    public long insert(ProdottoModel prodotto) {
        String sql = "INSERT INTO prodotto (descrizione, prezzo) VALUES (?, ?) RETURNING id";

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, prodotto.getDescrizione());
            statement.setBigDecimal(2, prodotto.getPrezzo());
            try (var rs = statement.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante insert su prodotto", e);
        }
    }

    public boolean update(ProdottoModel prodotto) {
        String sql = "UPDATE prodotto SET descrizione = ?, prezzo = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, prodotto.getDescrizione());
            statement.setBigDecimal(2, prodotto.getPrezzo());
            statement.setLong(3, prodotto.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante update su prodotto", e);
        }
    }

    public boolean deleteById(long id) {
        String sql = "DELETE FROM prodotto WHERE id = ?";

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Errore durante deleteById su prodotto", e);
        }
    }

    private ProdottoModel map(ResultSet rs) throws SQLException {
        return new ProdottoModel()
                .setId(rs.getLong("id"))
                .setDescrizione(rs.getString("descrizione"))
                .setPrezzo(rs.getBigDecimal("prezzo"))
                .setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime())
                .setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
    }
}

