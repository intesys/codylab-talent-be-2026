package it.intesys.codylab;

import it.intesys.codylab.db.JdbcDemo;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;

import java.time.LocalDate;

public class CodyLabApplication {

    static void main() {
        JdbcDemo jdbcDemo = new JdbcDemo();
//        jdbcDemo.getAllProjects();

      jdbcDemo.getAllProjectsFromRepository();



        /*Project project = new Project()
                .setTitle("Prova")
                .setDescription("Descrizione prova")
                .setEstimatedHours(100)
                .setStatus(ProjectStatus.CREATED)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(10))
                .setCreateDate(LocalDate.now())
                .setUpdateDate(null);*/

        Project project = jdbcDemo.getProjectById(1L);
        project.setTitle("Nuovo Titolo Funzionante")
                .setDescription("Ho modificato il database con successo!")
                .setEstimatedHours(250)
                .setStatus(ProjectStatus.CREATED)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(5))
                .setUpdateDate(LocalDate.now());

        jdbcDemo.updateProjectById(project, 1L);

        /*Project activity = new Project()
                .setTitle("Prova")
                .setDescription("Descrizione prova modificata")
                .setEstimatedHours(100)
                .setStatus(ProjectStatus.CREATED)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(10))
                .setCreateDate(LocalDate.now())
                .setUpdateDate(null);*/


        //jdbcDemo.insertProject(project);
        //jdbcDemo.updateProjectById(project);
        //jdbcDemo.updateProjectById(activity);
    }
}
