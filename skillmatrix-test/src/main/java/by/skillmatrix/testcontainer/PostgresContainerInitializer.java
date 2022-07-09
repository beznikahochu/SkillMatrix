package by.skillmatrix.testcontainer;

import lombok.SneakyThrows;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

    private static final String IMAGE_VERSION = "postgres:13.2";

    private static PostgreSQLContainer<?> container;

    @Override
    public void initialize(GenericApplicationContext genericApplicationContext) {
        instanceContainer();

        TestPropertyValues.of(
                "spring.datasource.url=" + container.getJdbcUrl(),
                "spring.datasource.username=" + container.getUsername(),
                "spring.datasource.password=" + container.getPassword(),
                "spring.liquibase.enabled=true"
        ).applyTo(genericApplicationContext.getEnvironment());
    }

    @SneakyThrows
    private void instanceContainer() {
        if (container == null) {
            container = new PostgreSQLContainer<>(IMAGE_VERSION);
            container
                    .withExposedPorts(5432)
                    .withDatabaseName("test")
                    .withUsername("postgres")
                    .withPassword("684615")
                    .start();
        }
    }
}
