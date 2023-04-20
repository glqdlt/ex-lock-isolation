package com.glqdlt.ex.lockisolation;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author glqdlt
 */
@EnableTransactionManagement
@EnableJpaRepositories
@Configuration
public class JpaConfig {


}
