package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages={"controller", "model"}, exclude = {DataSourceAutoConfiguration.class })
public class Main
{
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
