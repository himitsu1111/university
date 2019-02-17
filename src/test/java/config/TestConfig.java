package config;

/**
 * Created by book on 14.02.2019.
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.PersistenceUnit;

@Configuration
@ComponentScan(basePackages = "com.mycrud.controller")
@ImportResource({"/WEB-INF/spring/root-context.xml",
        "/WEB-INF/spring/datasource-tx-jpa.xml",
        "/WEB-INF/spring/servlet-context.xml"
})
public class TestConfig {

}
