package it.intesys.codylab.db;

import it.intesys.codylab.ProdottiMain;
import it.intesys.codylab.db.model.LibroModel;
import it.intesys.codylab.db.model.ProdottoModel;
import it.intesys.codylab.db.repository.LibroJdbcRepository;
import it.intesys.codylab.db.repository.ProdottoJdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;

/**
 * Mini-runner per testare JDBC + Hikari sulle tabelle prodotto/libro.
 */
public class JdbcHikariDemoMain {

    private static final Logger logger = LoggerFactory.getLogger(JdbcHikariDemoMain.class);

    public static void main(String[] args) {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();

        ProdottoJdbcRepository prodottoRepository = new ProdottoJdbcRepository(dataSource);
        LibroJdbcRepository libroRepository = new LibroJdbcRepository(dataSource);

        long prodottoId = prodottoRepository.insert(new ProdottoModel()
                .setDescrizione("Monitor 24 pollici")
                .setPrezzo(new BigDecimal("149.90")));

        long libroId = libroRepository.insert(new LibroModel()
                .setTitolo("Clean Code")
                .setAutore("Robert C. Martin")
                .setLingua("EN"));

        logger.info("Prodotto inserito con id: {}", prodottoId);
        logger.info("Libro inserito con id: {}", libroId);

        logger.info("Lista prodotti:");
        prodottoRepository.findAll().forEach(l -> logger.info(l.toString()));

        logger.info("Lista libri:");
        libroRepository.findAll().forEach(l -> logger.info(l.toString()));

        HikariDataSourceProvider.shutdown();
    }
}

