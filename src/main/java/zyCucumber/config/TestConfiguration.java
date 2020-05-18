package zyCucumber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import zyCucumber.support.DataRepo;

@Configuration
@ComponentScan(
        basePackages = {"zyCucumber"}
)
/**
	配置文件
*/
public class TestConfiguration {

    @Bean(name="dataRepo")
    public DataRepo getDataRepo() {
        DataRepo dataRepo = new DataRepo();
        dataRepo.init();
        return  dataRepo;
    }
}
