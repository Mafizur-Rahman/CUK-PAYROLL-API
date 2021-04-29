package admin.payroll;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ComponentScan(basePackages = { "admin.payroll.exceptions" })
//@ComponentScan(basePackages = { "admin.payroll" })
//@EnableAutoConfiguration
@Configuration
//@EntityScan("admin.payroll")
@EnableJpaAuditing
//@EnableJpaRepositories("admin.payroll")
@EnableTransactionManagement
@SpringBootApplication

public class EmployeePayrollApplication {

	public static void main(String[] args) throws InterruptedException {
		// Hi I am mukul...developer of this project please call me on this number
		// 8754614361...plz
		SpringApplication.run(EmployeePayrollApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
