package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.Activity;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ActivityRepository {

    private final DataSource dataSource;

    public ActivityRepository(DataSource dataSource) {
        System.out.println("---- Chiamato costruttore di ActivityRepository");
        this.dataSource = dataSource;
    }

    // 1. READ ALL (Find All Activities)
    public List<Activity> findAll() {
        String sql = """
            SELECT id, name, estimated_hours, project_id, create_date, update_date
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

    // 2. READ BY ID (Find One Activity)
    public Optional<Activity> findById(long id) {
        String sql = """
            SELECT id, name, estimated_hours, project_id, create_date, update_date
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

    // 3. CREATE (Insert New Activity)
    public long insert(Activity activity) {
        String sql = """
            INSERT INTO activities (name, estimated_hours, project_id, create_date, update_date)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, activity.getName());
            statement.setInt(2, activity.getEstimatedHours());

            // Project ID relation
            statement.setLong(3, activity.getProjectId());

            statement.setDate(4, Date.valueOf(activity.getCreateDate()));

            // Update Date (Optional)
            if (activity.getUpdateDate() != null) {
                statement.setDate(5, Date.valueOf(activity.getUpdateDate()));
            } else {
                statement.setNull(5, Types.DATE);
            }

            statement.executeUpdate();

            try (var rs = statement.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 4. UPDATE (Modify Existing Activity)
    public void update(long id, Activity activity) {
        String sql = """
            UPDATE activities
            SET name = ?,
                estimated_hours = ?,
                project_id = ?,
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

            if (activity.getUpdateDate() != null) {
                statement.setDate(4, Date.valueOf(activity.getUpdateDate()));
            } else {
                statement.setNull(4, Types.DATE);
            }

            statement.setLong(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 5. DELETE (Remove Activity)
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

    // Row mapper to transform database results into Java objects
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
}