package Medicine.Config;

import Medicine.DAO.MedDAOImpl;
import Medicine.Entities.Medicament;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("Medicine")
public class SpringConfig {

    @Bean
    public MedDAOImpl medDAO(){
        return new MedDAOImpl();
    }

    @Bean
    public SessionFactory sessionFactory(){
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgresPlusDialect")
                .setProperty("hibernate.connection.url",
                        "jdbc:postgresql://localhost:5432/medicine?useUnicode=true&amp;characterEncoding=UTF-8")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "qwerty")
                .setProperty("hibernate.show_sql", "false")
                .setProperty("hibernate.generate_statistics", "false")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.format_sql", "false")
                .setProperty("hibernate.use_sql_comments", "false");
        configuration.addAnnotatedClass(Medicament.class);
        StandardServiceRegistryBuilder builder = new
                StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }
}
