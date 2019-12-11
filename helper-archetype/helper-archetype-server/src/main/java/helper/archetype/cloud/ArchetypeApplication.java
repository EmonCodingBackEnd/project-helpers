package helper.archetype.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(
    value = {@ComponentScan(value = "com.coding"), @ComponentScanvalue="helper.archetype.cloud"}
)
@SpringBootApplication
public class ArchetypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchetypeApplication.class, args);
    }
}
