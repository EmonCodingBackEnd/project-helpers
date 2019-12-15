#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.timer.schedule.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ScheduledTask {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /*
     * 定时配置有三种：
     * 1、cron 2、fixedRate 3、fixedDelay
     * 这里推荐使用cron，同时定义在该方法中的定时任务，都是异步执行的，
     * 两次任务执行之间只有共享资源的影响（比如：线程池资源、数据库资源）。
     * 所以，定时间隔要有考虑，保证任务执行时间间隔大于任务执行花费时间。
     * 针对corn的配置说明：
     * 元素               允许的配置字符
     * - 秒(0-59)         , - * /
     * - 分(0-59)         , - * /
     * - 时(0-23)         , - * /
     * - 天(0-31)         , - * / ?
     * - 月(1-12)         , - * /
     * - 星期(1-7 1=SUN，或者SUN,MON,TUE,WED,THU,FRI,SAT)    , - * / ? L C ${symbol_pound}
     * - 年(1970-2099)    , - * /                        【Spring不支持年位定制】
     * 其中： , 表示单值，比如： 0 0 10,14,16 * * ?    含义：每天上午10点，下午2点和4点执行
     *       - 表示区间，比如： 0 0 10-16 * * ?       含义：每天上午10点到下午4点的每个小时
     *       / 表示每隔，比如： 0/5 * * * * *         含义：每隔5秒
     *       ? 只能用于月与星期，月与星期的天数，可能冲突，所以可以用?指定其中某一个指不需要设置
     *       ${symbol_pound} 只能用于星期，表示第几个星期；比如2${symbol_pound}2，表示第二个星期二。
     * 其他的使用方式，请自行搜索cron表达式。
     * 这里有一个cron表达式生成器： http://cron.qqe2.com/
     */

    /*// 上一次任务执行完毕，到下一次任务执行开始的时间
    @Scheduled(fixedDelay = 10000)
    public void scheduled11() throws InterruptedException {
        log.info(
                "=====>>>>>开始fixedDelay1 {}",
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        TimeUnit.SECONDS.sleep(5);
        log.info("=====>>>>>结束fixedDelay1 {}", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }

    // 上一次任务执行开始，到下一次任务执行开始的时间
    @Scheduled(fixedRate = 10000)
    public void scheduled21() throws InterruptedException {
        log.info(
                "=====>>>>>开始fixedRate1 {}",
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        TimeUnit.SECONDS.sleep(5);
        log.info("=====>>>>>结束fixedRate1 {}", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void scheduled31() throws InterruptedException {
        log.info(
                "=====>>>>>开始cron1 {}",
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        TimeUnit.SECONDS.sleep(5);
        log.info("=====>>>>>结束cron1 {}", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }*/
}
