package github.jhchee.otel.domain.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "github.jhchee.otel.domain.persistence"
)
public class PostgresConfig {
    @Bean
    public DataSource dataSource() {
        java.security.Security.setProperty("networkaddress.cache.ttl", "60");
        final Properties props = new Properties();
        props.put("driverClassName", "org.postgresql.Driver");
        props.put("jdbcUrl", "jdbc:postgresql://localhost:5432/postgres");
        props.put("username", "postgres");
        props.put("password", "postgres");
        HikariConfig hc = new HikariConfig(props);
        hc.setPoolName("bve-postgres-pool");
        hc.addDataSourceProperty("stringtype", "unspecified");
        hc.setConnectionTimeout(1000 * 60);
        hc.setMaxLifetime(1000 * 60 * 3);
        hc.setMaximumPoolSize(5);
        hc.addDataSourceProperty("socketTimeout", 180);
        return new HikariDataSource(hc);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("github.jhchee.otel.domain.persistence");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(getHibernateProperties());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
}
