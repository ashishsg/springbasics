package springbasics.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import asg.springbasics.service.DemoService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringBasicsGzipApplication {
	
//	@Bean
//	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//		return http.authorizeExchange().pathMatchers("/actuator/**").permitAll().anyExchange().authenticated().and()
//				.build();
//	}

	@Bean(name = "/gzipdemo")
	HttpInvokerServiceExporter demoService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerGzipServiceExporter();
        exporter.setService( new asg.springbasics.service.impl.DemoServiceImpl() );
        exporter.setServiceInterface( DemoService.class );
        return exporter;
    }
	public static void main(String[] args) {
		
		// System.out.println("Brotli available: " + BrotliLoader.isBrotliAvailable());
		SpringApplication.run(SpringBasicsGzipApplication.class, args);
	}
}
