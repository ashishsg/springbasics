package springbasics.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.nixxcode.jvmbrotli.common.BrotliLoader;

import asg.springbasics.service.DemoService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringBasicsBrotliApplication {
	
	@Bean(name = "/brotlidemo")
	HttpInvokerServiceExporter demoService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerBrotliServiceExporter();
        exporter.setService( new asg.springbasics.service.impl.DemoServiceImpl() );
        exporter.setServiceInterface( DemoService.class );
        return exporter;
    }
	public static void main(String[] args) {
		
		System.out.println("Brotli available: " + BrotliLoader.isBrotliAvailable());
		SpringApplication.run(SpringBasicsBrotliApplication.class, args);
	}
}
