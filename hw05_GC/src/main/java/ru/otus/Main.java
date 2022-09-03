package ru.otus;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

//-Xmx256m -Xms256m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./hw05_GC/logs/dump

// -XX:+UseSerialGC
// -XX:+UseParallelGC
// -XX:+UseConcMarkSweepGC

public class Main {

    static Logger logger = Logger.getLogger(GcInfo.class.getName());

    public static void main(String[] args) throws Exception {
        logger.info("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        final ObjectName name = new ObjectName("ru.otus:type=Benchmark");
        final Benchmark mbean = new Benchmark();

        mbs.registerMBean(mbean, name);
        int size = 2000;
        mbean.setSize(size);

        mbean.run();
    }
}
