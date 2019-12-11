#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(
    value = {@ComponentScan(value = "com.coding"), @ComponentScanvalue="${package}"}
)
@SpringBootApplication
public class ArchetypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchetypeApplication.class, args);
    }
}
