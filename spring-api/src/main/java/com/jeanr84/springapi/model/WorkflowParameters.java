package com.jeanr84.springapi.model;

import lombok.Data;

@Data
public class WorkflowParameters {

    private String applicationPath;
    private String applicationName;
    private String applicationMainClass;
    private String applicationSparkConf;
    private String applicationJar;

    private String inputData;
    private String outputData;
}
