package zyCucumber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import zyCucumber.support.DataRepo;

@Configuration
@ComponentScan(
        basePackages = {"zyCucumber"}
)
public class TestConfiguration {

    @Bean
    public DataRepo getDataRepo() {
        DataRepo dataRepo = new DataRepo();
        dataRepo.init();
        return  dataRepo;
    }
}
