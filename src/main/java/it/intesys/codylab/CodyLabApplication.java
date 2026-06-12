package it.intesys.codylab;

import it.intesys.codylab.db.JdbcDemo;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;

import java.time.LocalDate;

public class CodyLabApplication {

    static void main() {
        JdbcDemo jdbcDemo = new JdbcDemo();

        // Eseguiamo il test per le Activity
        jdbcDemo.getAllActivitiesFromRepository();
//        jdbcDemo.getAllProjects();

//        jdbcDemo.getAllProjectsFromRepository();

//        jdbcDemo.getProjectById(1L);

/*        Project project = new Project()
                .setTitle("Prova")
                .setDescription("Descrizione prova")
                .setEstimatedHours(100)
                .setStatus(ProjectStatus.CREATED)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(10))
                .setCreateDate(LocalDate.now())
                .setUpdateDate(LocalDate.now());
        jdbcDemo.insertProject(project);

        jdbcDemo.updateProjectById(1L, project); */
    }
}
