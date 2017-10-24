package com.rjean84.springooziespark.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/hadoop.properties")
@Getter
public class HadoopProperties {

    @Value("{hadoop.oozie.url}")
    private String oozieUrl;

    @Value("{hadoop.hdfs.namenode.url}")
    private String nameNodeUrl;

    @Value("{hadoop.mapreduce.jobtracker.url}")
    private String jobTrackerUrl;
}
