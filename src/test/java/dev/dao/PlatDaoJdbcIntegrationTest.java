package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import dev.config.JdbcTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;

import javax.sql.DataSource;

@SpringJUnitConfig(classes = {PlatDaoJdbc.class, JdbcTestConfig.class})
@ActiveProfiles("jdbc")
public class PlatDaoJdbcIntegrationTest extends IPlatDaoIntegrationTest {

}
