package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.Activity;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepository {

    private final DataSource dataSource;

    public ActivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Activity> findAll() {

        String sql = """
            SELECT *
            FROM activities
            """;

        List<Activity> result = new ArrayList<>();

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql);
                var rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                result.add(mapActivity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Activity mapActivity(ResultSet rs) throws SQLException {
        Date updatedAtSql = rs.getDate("update_date");

        return new Activity()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setEstimatedHours(rs.getInt("estimated_hours"))
                .setProjectId(rs.getLong("project_id"))
                .setCreateDate(rs.getDate("create_date").toLocalDate())
                .setUpdateDate(updatedAtSql != null ? updatedAtSql.toLocalDate() : null);
    }

    public Optional<Activity> findById(long id) {

        String sql = """
            SELECT *
            FROM activities
            WHERE id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapActivity(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long insert(Activity activity) {

        String sql = """
        INSERT INTO activities 
        (
            name, 
            estimated_hours, 
            project_id, 
            create_date, 
            update_date
        )
        VALUES (?, ?, ?, ?, ?)
        RETURNING id
        """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, activity.getName());
            statement.setInt(2, activity.getEstimatedHours());
            statement.setLong(3, activity.getProjectId());
            statement.setDate(4, Date.valueOf(activity.getCreateDate()));
            statement.setDate(5, activity.getUpdateDate() != null ? Date.valueOf(activity.getUpdateDate()) : null);

            try (var rs = statement.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo update
    public void updateActivityById(long id, Activity activity) {
        String sql = """
            UPDATE activities 
            SET name = ?, 
                estimated_hours = ?, 
                project_id = ?, 
                create_date = ?, 
                update_date = ?
            WHERE id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, activity.getName());
            statement.setInt(2, activity.getEstimatedHours());
            statement.setLong(3, activity.getProjectId());
            statement.setDate(4, Date.valueOf(activity.getCreateDate()));
            statement.setDate(5, activity.getUpdateDate() != null ? Date.valueOf(activity.getUpdateDate()) : null);
            statement.setLong(6, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {

        String sql = """
            DELETE FROM activities 
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
}