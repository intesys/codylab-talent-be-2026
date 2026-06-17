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

        jdbcDemo.getProjectById(1L);

//        Project project = new Project()
//                .setTitle("Prova")
//                .setDescription("Descrizione prova")
//                .setEstimatedHours(100)
//                .setStatus(ProjectStatus.CREATED)
//                .setStartDate(LocalDate.now())
//                .setEndDate(LocalDate.now().plusDays(10))
//                .setCreateDate(LocalDate.now())
//                .setUpdateDate(null);

//        Project projectModificato = new Project()
//                .setTitle("Prova Modificata")
//                .setDescription("Descrizione prova modificata")
//                .setEstimatedHours(100)
//                .setStatus(ProjectStatus.WORKING)
//                .setStartDate(LocalDate.now())
//                .setEndDate(LocalDate.now().plusDays(10));
//        jdbcDemo.insertProject(project);
//        jdbcDemo.updateProjectById(1L, projectModificato);
    }
}
