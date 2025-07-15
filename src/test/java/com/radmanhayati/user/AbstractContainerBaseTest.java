package com.radmanhayati.user;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractContainerBaseTest {

	@Container
	static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			DockerImageName.parse("postgres:13-alpine")
					.asCompatibleSubstituteFor("postgres"))
			.withDatabaseName("user-service")
			.withUsername("radman")
			.withPassword("password");

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		// Configure datasource using the container's default handling
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);

		// Configure JPA/Hibernate
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
		registry.add("spring.jpa.properties.hibernate.dialect",
				() -> "org.hibernate.dialect.PostgreSQLDialect");
	}

	@BeforeAll
	static void checkContainerIsRunning() {
		if (!postgres.isRunning()) {
			throw new IllegalStateException("PostgreSQL container failed to start!");
		}

		System.out.println("\n=== Test Container Info ===");
		System.out.println("JDBC URL: " + postgres.getJdbcUrl());
		System.out.println("Database: " + postgres.getDatabaseName());
		System.out.println("Username: " + postgres.getUsername());
		System.out.println("Password: " + postgres.getPassword());
		System.out.println("==========================\n");
	}
}