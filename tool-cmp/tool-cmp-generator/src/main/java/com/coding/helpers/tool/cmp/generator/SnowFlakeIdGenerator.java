package com.coding.helpers.tool.cmp.generator;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 雪花碎片算法(SnowFlake)生成分布式系统唯一ID.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190227 15:48</font><br>
 * 雪花碎片算法生成的ID一共包含四个部分(各部分用 - 隔开)：<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 *
 * <ul>
 *   <li>第一部分：1位标志位，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 *   <li>第二部分：41位时间戳（毫秒级），注意，41位时间戳不是存储当前时间的时间戳，而是存储时间戳的差值（当前时间戳 - 开始时间戳）得到的值，<br>
 *       这里的开始时间戳，一般是我们的ID生成器开始使用的时间。由这个开始时间戳为起点，41位时间戳可以使用69年(<code>
 *       T = ( 1L<<41 ) / (1000L * 60 * 60 * 24 *365) = 69</41></code>)。
 *   <li>第三部分：10位的工作机器位，可以部署在1024(2^10=1024)个节点，包括5位datacenterId（数据中心ID）和5位workerId（工作进程ID）<br>
 *   <li>第四部分：12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒（相同时间戳和工作机器位）产生4096个ID序号
 * </ul>
 *
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由数据中心ID和工作进程ID），并且效率较高，<br>
 * 网上测试数据每秒26万左右；我使用自己笔记本测试10次取平均值约每秒166万左右。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class SnowFlakeIdGenerator {

    // 获取20190227对应的毫秒值作为基准，按照69年的计算，可以使用到2088年。对于一个系统而言，该值指定后不可变动。
    private final long twepoch = 1551196800207L;

    // 数据中心ID的位数
    private final long datacenterIdBits = 5L;
    // 工作进程ID的位数
    private final long workerIdBits = 5L;
    // 毫秒内自增序列的位数
    private final long sequenceBits = 12L;

    // 数据中心ID的最大值
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);
    // 工作进程ID的最大值
    private final long maxWorkerId = ~(-1L << workerIdBits);

    // 数据中心ID掩码
    private final long datacenterIdMask = ~(-1L << datacenterIdBits);
    // 工作进程ID掩码
    private final long workerIdMask = ~(-1L << workerIdBits);
    // 序列掩码
    private final long sequenceMask = ~(-1L << sequenceBits);

    // 在long的64位二进制中，工作进程ID需要上档的位数
    private final long workerIdShift = sequenceBits;
    // 在long的64位二进制中，数据中心ID需要上档的位数
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    // 在long的64位二进制中，时间戳ID需要上档的位数
    private final long timestampShift = sequenceBits + workerIdBits + datacenterIdBits;

    // 上次生成ID时的时间戳
    private long lastTimestamp = -1L;
    // 当前服务的数据中心ID
    private long datacenterId;
    // 当前服务的工作进程ID
    private long workerId;
    // 在毫秒内自增序列
    private long sequence = 0L;

    private static SnowFlakeIdGenerator instance = null;

    static {
        String propDatacenterId = System.getProperty("snowflake.datacenterId");
        String propWorkerId = System.getProperty("snowflake.workerId");
        // 如果存在启动参数配置
        if (propDatacenterId != null && propWorkerId != null) {
            try {
                long initDatacenterId = Long.parseLong(propDatacenterId);
                long initWorkerId = Long.parseLong(propWorkerId);
                instance = new SnowFlakeIdGenerator(initDatacenterId, initWorkerId);
            } catch (NumberFormatException e) {
                log.error(
                        String.format(
                                "【SnowFlake】数据中心ID、工作进程ID属性值不合法 snowflake.datacenterId=%s, snowflake.workderId=%s 启用自动获取",
                                propDatacenterId, propWorkerId),
                        e);
                instance = new SnowFlakeIdGenerator();
            }
        } else {
            instance = new SnowFlakeIdGenerator();
        }
    }

    public static SnowFlakeIdGenerator getInstance() {
        return instance;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            log.error(
                    "【SnowFlake】clock is moving backwords. Rejecting requests until {}.",
                    lastTimestamp);
            throw new RuntimeException(
                    String.format(
                            "Clock moved backwords. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = untilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    private SnowFlakeIdGenerator() {
        this.datacenterId = getDatacenterId(maxDatacenterId);
        this.workerId = getWorkerId(this.datacenterId, maxWorkerId);
    }

    private SnowFlakeIdGenerator(long datacenterId, long workerId) {
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new RuntimeException(
                    String.format(
                            "datacenter Id can't be greater than %d or less than 0",
                            maxDatacenterId));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new RuntimeException(
                    String.format(
                            "worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        log.info(
                "【SnowFlake】generator starting. timestamp shift {}, datacenter id bits {}, worker id bits {}, sequence bits {}, datacenterId={}, workerId={}",
                timestampShift,
                datacenterIdBits,
                workerIdBits,
                sequenceBits,
                datacenterId,
                workerId);
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    protected long getDatacenterId(long maxDatacenterId) {
        long datacenterId = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                datacenterId = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                // TODO: 2019/2/27 为什么获取mac地址的低16位运算后作为数据中心ID？
                datacenterId =
                        ((0x000000FF & (long) mac[mac.length - 1])
                                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8)))
                                >> 6;
                datacenterId = datacenterId % (maxDatacenterId + 1);
            }
        } catch (UnknownHostException | SocketException e) {
            log.error("【SnowFlake】获取数据中心ID异常，使用默认值[datacenterId=0L]", e);
        }

        return datacenterId;
    }

    protected long getWorkerId(long datacenterId, long maxWorkerId) {
        long workerId;
        StringBuilder pid = new StringBuilder();
        pid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (name != null && !"".equals(name)) {
            // GET jvmPid
            pid.append(name.split("@")[0]);
        }
        // TODO: 2019/2/27 为什么这样的组合可以表示同一个数据中心ID对应的【不重复】的工作进程ID？
        // MAC + PID 的 hashcode 获取 16 个低位
        workerId = (pid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
        return workerId;
    }

    private long untilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            long now = System.currentTimeMillis();
            long diff = now - start;
            if (diff >= 1000) {
                log.info("统计1秒执行次数:{}, 实际耗时:{}ms", i, diff);
                break;
            }
            SnowFlakeIdGenerator.getInstance().nextId();
        }
    }
}
