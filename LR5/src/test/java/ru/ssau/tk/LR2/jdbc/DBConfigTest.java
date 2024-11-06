package ru.ssau.tk.LR2.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.tk.LR2.jdbc.model.MathResult;
import ru.ssau.tk.LR2.jdbc.repository.MathResultRepository;

import javax.sql.DataSource;

@TestConfiguration
class DBTestConfig {
    @Bean
    @Profile("test")
    DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlite:mock.db");
        dataSource.setUsername("admin");
        dataSource.setPassword("");

        Resource initSchema = new ClassPathResource("sql/schema.sql");
        DatabasePopulator dbPopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(dbPopulator, dataSource);

        return dataSource;
    }
}

@ActiveProfiles("test")
@SpringBootTest(classes = {MathResultRepository.class, DBConfig.class, DBTestConfig.class})
@RunWith(SpringRunner.class)
public class DBConfigTest {

    @Autowired
    MathResultRepository repo;

    @Test
    public void testDatabase(){
        repo.deleteAll();

        Assert.assertEquals(0, repo.getCount());

        MathResult res1 = new MathResult(10.0, 10.0, 1000);

        repo.insert(res1);

        Assert.assertEquals(1, repo.getCount());
        Assert.assertEquals(res1, repo.findByXAndHash(10.0, 1000));

        repo.deleteByXAndHash(10.0, 1000);

        Assert.assertEquals(0, repo.getCount());



    }

}