package dev.dao;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JpaTestConfig;

// EMF + Gestion Tx + DataSource
@SpringJUnitConfig(classes = {PlatDaoJpa.class, JpaTestConfig.class})
@ActiveProfiles("jpa")
public class PlatDaoJpaIntegrationTest extends IPlatDaoIntegrationTest{

}
