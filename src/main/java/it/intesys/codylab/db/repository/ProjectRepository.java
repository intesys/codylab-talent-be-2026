package it.intesys.codylab.db.repository;

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
            statement.setDate(6, Date.valueOf(project.getCreateDate()));
            statement.setDate(7, project.getUpdateDate() != null ? Date.valueOf(project.getUpdateDate()) : null);
            statement.setObject(8, project.getStatus().name(), Types.OTHER);

            try (var rs = statement.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProjectById(long id, Project project) {
        String sql = """
            UPDATE projects
            SET title = ?,
                description = ?,
                estimated_hours = ?,
                start_date = ?,
                end_date = ?,
                update_date = ?,
                status = ?
            WHERE id = ?
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql)
        ) {
            // 1. Title (Required)
            statement.setString(1, project.getTitle());

            // 2. Description (Optional - Handling potential Nulls)
            if (project.getDescription() != null) {
                statement.setString(2, project.getDescription());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            // 3. Estimated Hours (Required)
            statement.setInt(3, project.getEstimatedHours());

            // 4. Start Date (Required)
            statement.setDate(4, Date.valueOf(project.getStartDate()));

            // 5. End Date (Required)
            statement.setDate(5, Date.valueOf(project.getEndDate()));

            // 6. Update Date (Optional - Handling potential Nulls)
            if (project.getUpdateDate() != null) {
                statement.setDate(6, Date.valueOf(project.getUpdateDate()));
            } else {
                statement.setNull(6, Types.DATE);
            }

            // 7. Status (Required - Mapped as Postgres custom type)
            statement.setObject(7, project.getStatus().name(), Types.OTHER);

            // 8. ID for the WHERE clause
            statement.setLong(8, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ==========================================
    // EXERCISE 3: JOIN - Find open projects with their activities
    // ==========================================
    public List<Project> findAllProjectsWithActivities() {
        // Query retrieving projects that are not CLOSED or COMPLETED using a JOIN
        String sql = """
            SELECT p.* FROM projects p
            JOIN activities a ON p.id = a.project_id
            WHERE p.status NOT IN ('COMPLETED', 'CLOSED')
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

    // ==========================================
    // EXERCISE 4: JOIN/COUNT - Active projects per client
    // ==========================================
    public void countProjectsInProgressByClient() {
        // Query grouping active projects by client name and counting them
        String sql = """
            SELECT c.name, COUNT(p.id) AS active_projects_count
            FROM customers c
            LEFT JOIN projects p ON c.id = p.customer_id AND p.status = 'WORKING'
            GROUP BY c.name
            """;

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement(sql);
                var rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                String clientName = rs.getString("name");
                int count = rs.getInt("active_projects_count");
                System.out.println("Client: " + clientName + " | Active Projects: " + count);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}