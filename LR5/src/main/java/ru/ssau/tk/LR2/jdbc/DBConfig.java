package ru.ssau.tk.LR2.jdbc;

import org.komamitsu.spring.data.sqlite.EnableSqliteRepositories;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableSqliteRepositories(namedQueriesLocation = "sql/jdbc-named-queries.properties")
public class DBConfig {
    @Bean
    @Profile("prod")
    DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlite:test.db");
        dataSource.setUsername("admin");
        dataSource.setPassword("");

        Resource initSchema = new ClassPathResource("sql/schema.sql");
        DatabasePopulator dbPopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(dbPopulator, dataSource);

        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate template(DataSource source){
        return new NamedParameterJdbcTemplate(source);
    }

}
