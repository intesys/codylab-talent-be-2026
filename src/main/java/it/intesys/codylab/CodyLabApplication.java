package it.intesys.codylab;

import it.intesys.codylab.db.JdbcDemo;

public class CodyLabApplication {

    static void main() {
        JdbcDemo jdbcHikariDemoMain = new JdbcDemo();
//        jdbcHikariDemoMain.getAllProjects();
        jdbcHikariDemoMain.getAllProjectsFromRepository();
    }
}
