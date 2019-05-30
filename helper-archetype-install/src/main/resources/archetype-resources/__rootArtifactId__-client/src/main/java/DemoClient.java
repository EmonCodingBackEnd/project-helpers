#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "HELPER-ARCHETYPE-PROVIDER", fallback = DemoClient.DemoClientFallback.class)
public interface DemoClient {

    @GetMapping("/msg")
    String msg();

    @Component
    class DemoClientFallback implements DemoClient {
        @Override
        public String msg() {
            return null;
        }
    }
}
