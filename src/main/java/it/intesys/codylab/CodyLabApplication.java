package it.intesys.codylab;

import it.intesys.codylab.db.JdbcDemo;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;

import java.time.LocalDate;

public class CodyLabApplication {

    static void main() {
        JdbcDemo jdbcDemo = new JdbcDemo();
//        jdbcDemo.getAllProjects();

//        jdbcDemo.getAllProjectsFromRepository();

//        jdbcDemo.getProjectById(1L);

        Project project = new Project()
                .setTitle("Prova")
                .setDescription("Descrizione prova")
                .setEstimatedHours(100)
                .setStatus(ProjectStatus.CREATED)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(10))
                .setCreateDate(LocalDate.now())
                .setUpdateDate(null);
        jdbcDemo.insertProject(project);
    }
}
