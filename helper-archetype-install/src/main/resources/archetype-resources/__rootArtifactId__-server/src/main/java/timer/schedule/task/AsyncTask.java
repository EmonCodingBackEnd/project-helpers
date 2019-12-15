#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.timer.schedule.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AsyncTask {

    @Autowired private ObjectMapper objectMapper;

    // 获取异步结果：可等待获取
    //    @Async
    public Future<Long> asyncTask11(int startNumber, int endNumber) throws InterruptedException {
        long beg = System.currentTimeMillis();
        long sum = 0;
        for (int i = startNumber; i <= endNumber; i++) {
            sum += i;
        }
        TimeUnit.SECONDS.sleep(2);
        long end = System.currentTimeMillis();
        log.info("asyncTask11执行耗时={}ms", end - beg);
        return new AsyncResult<>(sum);
    }

    // 获取异步结果
    //    @Async
    public void asyncTask22(int startNumber, int endNumber) throws InterruptedException {
        long beg = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        long end = System.currentTimeMillis();
        log.info("asyncTask22执行耗时={}ms", end - beg);
    }
}
