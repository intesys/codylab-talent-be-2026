package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivitiesRepository {

    private final DataSource dataSource;

    public ActivitiesRepository(DataSource dataSource) {
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
            create_date,
            update_date
        )
        VALUES (?, ?, ?, ?)
        RETURNING id
        """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, activity.getName());
            statement.setInt(3, activity.getEstimatedHours());
            statement.setDate(4, Date.valueOf(activity.getCreateDate()));
            statement.setDate(5, Date.valueOf(activity.getUpdateDate()));

            try (var rs = statement.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public long update(Activity activity) {

        String sql = """
        update activities
            set name = ?,
                estimated_hours = ?,
                update_date = ?
        where id = ?
        returning id;
        """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, activity.getName());
            statement.setInt(2, activity.getEstimatedHours());
            statement.setObject(3, activity.getCreateDate());
            statement.setObject(4, activity.getUpdateDate());
            statement.setLong(5, activity.getId());

            try (var rs = statement.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
