package dev.dao;

import dev.config.DataSourceH2TestConfig;
import dev.config.JdbcTestConfig;
import dev.config.JpaConfig;
import dev.config.JpaTestConfig;
import dev.entite.Plat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// EMF + Gestion Tx + DataSource
@SpringJUnitConfig(classes = {PlatDaoJpa.class, JpaTestConfig.class})
@ActiveProfiles("jpa")
public class PlatDaoJpaIntegrationTest extends IPlatDaoIntegrationTest{

}
