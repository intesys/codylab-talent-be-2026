package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository {

    private final DataSource dataSource;

    public ProjectRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Project> findAll() {

        String sql = """
            SELECT *
            FROM projects
            """;

        List<Project> result = new ArrayList<>();

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql);
                var rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                result.add(mapProject(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Project mapProject(ResultSet rs) throws SQLException {
        Date updatedAtSql = rs.getDate("update_date");

        return new Project()
                .setId(rs.getLong("id"))
                .setTitle(rs.getString("title"))
                .setDescription(rs.getString("description"))
                .setEstimatedHours(rs.getInt("estimated_hours"))
                .setStatus(ProjectStatus.valueOf(rs.getString("status")))
                .setStartDate(rs.getDate("start_date").toLocalDate())
                .setEndDate(rs.getDate("end_date").toLocalDate())
                .setCreateDate(rs.getDate("create_date").toLocalDate())
                .setUpdateDate(updatedAtSql != null ? updatedAtSql.toLocalDate() : null);
    }













    public Optional<Project> findById(long id) {

        String sql = """
            SELECT *
            FROM projects
            WHERE id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapProject(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }












    public long insert(Project project) {

        String sql = """
        INSERT INTO projects
        (
            title,
            description,
            estimated_hours,
            start_date,
            end_date,
            create_date,
            update_date,
            status
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        RETURNING id
        """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, project.getTitle());
            statement.setString(2, project.getDescription());
            statement.setInt(3, project.getEstimatedHours());
            statement.setDate(4, Date.valueOf(project.getStartDate()));
            statement.setDate(5, Date.valueOf(project.getEndDate()));
            statement.setDate(6, Date.valueOf(project.getEndDate()));
            statement.setDate(7, Date.valueOf(project.getEndDate()));
            statement.setObject(8, project.getStatus().name(), Types.OTHER);

            try (var rs = statement.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long update(long id, Project project) {
    }


//
//    public void update(long id, Project project) {
//        String sql = """
//        UPDATE projects
//        SET title = ?,
//            description = ?,
//            estimated_hours = ?,
//            start_date = ?,
//            end_date = ?,
//            update_date = ?,
//            status = ?
//        WHERE id = ?
//        """;
//
//        try (
//                var connection = dataSource.getConnection();
//                var statement = connection.prepareStatement(sql)
//        ) {
//            statement.setString(1, project.getTitle());
//            statement.setString(2, project.getDescription());
//            statement.setInt(3, project.getEstimatedHours());
//            statement.setObject(4, project.getStartDate());
//            statement.setObject(5, project.getEndDate());
//            statement.setObject(6, project.getCreateDate());
//            statement.setObject(7, project.getUpdateDate());
//            statement.setObject(8, project.getStatus().name(), Types.OTHER);
//            statement.setLong(9, project.getId());
//
//                try (var rs = statement.executeQuery()) {
//                    rs.next(); 
//                    return rs.getLong(1);
//                }  
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

