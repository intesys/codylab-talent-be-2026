package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.Tracking;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrackingRepository {

    private final DataSource dataSource;

    public TrackingRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 1. READ ALL
    public List<Tracking> findAll() {
        String sql = """
            SELECT id, description, duration_minutes, activity_id, user_id, create_date, update_date
            FROM trackings
            """;

        List<Tracking> result = new ArrayList<>();

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql);
                var rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                result.add(mapTracking(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // 2. READ BY ID
    public Optional<Tracking> findById(long id) {
        String sql = """
            SELECT id, description, duration_minutes, activity_id, user_id, create_date, update_date
            FROM trackings
            WHERE id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapTracking(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 3. CREATE
    public long insert(Tracking tracking) {
        String sql = """
            INSERT INTO trackings (description, duration_minutes, activity_id, user_id, create_date, update_date)
            VALUES (?, ?, ?, ?, ?, ?)
            RETURNING id
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, tracking.getDescription());
            statement.setInt(2, tracking.getDurationMinutes());
            statement.setLong(3, tracking.getActivityId());
            statement.setLong(4, tracking.getUserId());
            statement.setDate(5, Date.valueOf(tracking.getCreateDate()));

            if (tracking.getUpdateDate() != null) {
                statement.setDate(6, Date.valueOf(tracking.getUpdateDate()));
            } else {
                statement.setNull(6, Types.DATE);
            }

            try (var rs = statement.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 4. UPDATE
    public void update(long id, Tracking tracking) {
        String sql = """
            UPDATE trackings
            SET description = ?,
                duration_minutes = ?,
                activity_id = ?,
                user_id = ?,
                update_date = ?
            WHERE id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, tracking.getDescription());
            statement.setInt(2, tracking.getDurationMinutes());
            statement.setLong(3, tracking.getActivityId());
            statement.setLong(4, tracking.getUserId());

            if (tracking.getUpdateDate() != null) {
                statement.setDate(5, Date.valueOf(tracking.getUpdateDate()));
            } else {
                statement.setNull(5, Types.DATE);
            }

            statement.setLong(6, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 5. DELETE
    public void deleteById(long id) {
        String sql = """
            DELETE FROM trackings
            WHERE id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ==========================================
    // EXERCISE ANALYTICAL METHODS (SQL MODULE 2)
    // ==========================================

    // Scenario 1: How much time (in minutes) did a specific user log in total?
    public int getTotalMinutesByUserId(long userId) {
        String sql = """
            SELECT COALESCE(SUM(duration_minutes), 0)
            FROM trackings
            WHERE user_id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, userId);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Scenario 2: How many minutes were spent on a specific activity?
    public int getTotalMinutesByActivityId(long activityId) {
        String sql = """
            SELECT COALESCE(SUM(duration_minutes), 0)
            FROM trackings
            WHERE activity_id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, activityId);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Scenario 3: Who worked on a specific activity and for how long?
    public List<String> getUsersWorkSummaryByActivityId(long activityId) {
        String sql = """
            SELECT u.name, u.surname, SUM(t.duration_minutes) as total_minutes
            FROM trackings t
            JOIN users u ON t.user_id = u.id
            WHERE t.activity_id = ?
            GROUP BY u.id, u.name, u.surname
            """;

        List<String> summary = new ArrayList<>();

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, activityId);
            try (var rs = statement.executeQuery()) {
                while (rs.next()) {
                    String row = String.format("%s %s worked for %d minutes",
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("total_minutes"));
                    summary.add(row);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return summary;
    }

    // Row mapper for Tracking entity
    private Tracking mapTracking(ResultSet rs) throws SQLException {
        Date updatedAtSql = rs.getDate("update_date");

        return new Tracking()
                .setId(rs.getLong("id"))
                .setDescription(rs.getString("description"))
                .setDurationMinutes(rs.getInt("duration_minutes"))
                .setActivityId(rs.getLong("activity_id"))
                .setUserId(rs.getLong("user_id"))
                .setCreateDate(rs.getDate("create_date").toLocalDate())
                .setUpdateDate(updatedAtSql != null ? updatedAtSql.toLocalDate() : null);
    }
}