package jopa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;


@SpringBootApplication
@ImportAutoConfiguration(Config.class)
public class Main implements CommandLineRunner {

    @Autowired
    ApplicationContext context;

    public void run(String... args) throws Exception {
        ProductUtils utils = context.getBean(ProductUtils.class);
        utils.createTable();
        try{
        utils.addTwo(true);
        }
        catch (BadSqlGrammarException bd) {
            System.out.println("TRANSACTION FAILED");
        }
        utils.addTwo(false);
        utils.print();
        System.out.println(utils.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);


    }
}
