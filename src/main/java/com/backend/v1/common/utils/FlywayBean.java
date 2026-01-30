package com.backend.v1.common.utils;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.flywaydb.core.api.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FlywayBean implements ApplicationRunner {



    @Override
    public void run(ApplicationArguments args) throws Exception {

        var config = new ClassicConfiguration();
        config.setUser("yzh");
        config.setPassword("test");
        config.setUrl("jdbc:mysql://localhost:3306/Disaster?createDatabaseIfNotExist=true");
        config.setDriver("com.mysql.cj.jdbc.Driver");
        config.setCleanDisabled(false);

        Flyway flyway = new Flyway(config);

        flyway.clean();
        flyway.migrate();

    }


}
