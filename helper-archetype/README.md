# 说明

## 1、如何使用两个archetype

先使用`helper-archetype-cloud-share`创建微服务共享项目，比如`coupon-common`，执行命令安装：

```bash
mvn clean install -Dmaven.test.skip=true
```

再使用`helper-archetype-cloud`创建某一个微服务项目，比如`coupon-template`。创建完成后需要把启动类

`ArchetypeApplication`更换到包`com.coding`下。

打开`coupon-template`最外层的`pom.xml`文件，增加如下配置：

```xml
<dependencyManagement>
        <dependencies>
            <!-- 定义三方包 beg -->
            <!-- 定义三方包 end -->

            <!-- 定义二方包 beg -->
            <!-- 定义二方包 end -->

            <!-- 定义一方包 beg -->
			......
            <dependency>
                <groupId>com.coding.coupon</groupId>
                <artifactId>coupon-common</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            ......
            <!-- 定义一方包 end -->
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- 引入三方包 beg -->
        <!-- 引入三方包 end -->

        <!-- 引入二方包 beg -->
        <!-- 引入二方包 end -->

        <!-- 引入一方包 beg -->
        ......
        <dependency>
            <groupId>com.coding.coupon</groupId>
            <artifactId>coupon-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        ......
        <!-- 引入一方包 end -->
    </dependencies>
```

配置完成。

## 2、如何使用`coupon-common`项目的支持

大部分提供的支持，都是可以直接调用的，类似工具类。但有一些需要yml配置使用，比如ftp服务、定时任务、延迟任务和异步任务的支持，都需要额外配置。配置如下：

```yml
ftp:
  enabled: true # 开启或关闭
  pool:
    timeBetweenEvictionRunsMillis: 60000
    maxTotal: 1000
    maxTotalPerKey: 10
    minIdlePerKey: 5
    maxIdlePerKey: 10
    maxWaitMillis: 10000
  servers[0]:
    alias: default
    host: 192.168.1.56
    port: 21
    username: ftp
    password: ftp123
    passiveMode: true
    accessUrlPrefixes: http://zygfile.zhaoshengbao.org

# 特殊任务线程池配置
timerpool:
  enabled: true # 开启或关闭
  delay:
    enabled: true # 开启或关闭
    threadNamePrefix: TIMER_DELAY-
    corePoolSize: 10
    maxPoolSize: 200
    queueCapacity: 1000
    keeyAliveSecond: 60
    awaitTerminationSeconds: 60
    delayTaskQueueDaemonThreadName: DelayTaskQueueDaemonThread
  schedule:
    enabled: true # 开启或关闭
    threadNamePrefix: TIMER_SCHEDULE-
    poolSize: 50
    awaitTerminationSeconds: 60
  async:
    enabled: true # 开启或关闭
    threadNamePrefix: TIMER_ASYNC-
    corePoolSize: 10
    maxPoolSize: 50
    queueCapacity: 100
    keeyAliveSecond: 60
    awaitTerminationSeconds: 60
```



