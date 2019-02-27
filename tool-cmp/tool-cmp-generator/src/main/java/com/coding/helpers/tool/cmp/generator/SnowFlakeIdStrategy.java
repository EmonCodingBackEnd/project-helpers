package com.coding.helpers.tool.cmp.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 雪花碎片算法(SnowFlake)生成分布式系统唯一ID.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190227 18:13</font><br>
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
 * 网上测试数据每秒26万左右；我使用自己笔记本测试10次取平均值约每秒166万左右。 *
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class SnowFlakeIdStrategy implements IdentifierGenerator {

    @Override
    public Serializable generate(
            SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
            throws HibernateException {
        return SnowFlakeIdGenerator.getInstance().nextId();
    }
}
