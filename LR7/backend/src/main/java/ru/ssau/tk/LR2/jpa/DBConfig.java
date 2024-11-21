package ru.ssau.tk.LR2.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.ssau.tk.LR2.hash.BasicHaserFactory;
import ru.ssau.tk.LR2.hash.HasherFactory;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"jpa", "web"})
@EnableAutoConfiguration
@EnableJpaRepositories(namedQueriesLocation = "classpath:sql/jdbc-named-queries.properties")
@EnableJpaAuditing
public class DBConfig {

    @Autowired
    public Environment env;

    @Bean
    HasherFactory hasherFactory() {
        return new BasicHaserFactory();
    }

//    @Bean
//    DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//
//        Resource initSchema = new ClassPathResource("sql/schema.sql");
//        DatabasePopulator dbPopulator = new ResourceDatabasePopulator(initSchema);
//        DatabasePopulatorUtils.execute(dbPopulator, dataSource);
//
//        return dataSource;
//    }

    @Bean
    public NamedParameterJdbcTemplate template(DataSource source) {
        return new NamedParameterJdbcTemplate(source);
    }
}
