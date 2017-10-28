package com.jeanr84.springapi.service;

import com.jeanr84.springapi.configuration.HadoopProperties;
import com.jeanr84.springapi.model.WorkflowParameters;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Properties;

@Slf4j
@Component
public class OozieService {

    private final HadoopProperties properties;

    @Autowired
    public OozieService(HadoopProperties properties) {
        this.properties = properties;
    }

    public Optional<String> submitWorkflow(WorkflowParameters workflowParameters) {
        OozieClient oozieClient = new OozieClient(properties.getOozieUrl());
        Properties conf = initConf(oozieClient, workflowParameters);
        try {
            String jobId = oozieClient.run(conf);
            log.info("Workflow job, " + jobId + " submitted");
            while (oozieClient.getJobInfo(jobId).getStatus() == WorkflowJob.Status.RUNNING) {
                log.info("Workflow job running ...");
                Thread.sleep(10 * 1000);
            }
            log.info("Workflow job completed ...");
            log.info("{}",oozieClient.getJobInfo(jobId));
            return Optional.of(jobId);
        } catch (Exception r) {
            log.error("Errors " + r.getLocalizedMessage());
            return Optional.empty();
        }
    }

    private Properties initConf(OozieClient oozieClient, WorkflowParameters workflowParameters) {
        Properties conf = oozieClient.createConfiguration();

        conf.setProperty("nameNode", properties.getNameNodeUrl());
        conf.setProperty("jobTracker", properties.getJobTrackerUrl());
        conf.setProperty("queueName", "default");
        conf.setProperty("oozie.libpath", "${nameNode}/user/oozie/share/lib");
        conf.setProperty("oozie.use.system.libpath", "true");
        conf.setProperty("oozie.wf.rerun.failnodes", "true");

        conf.setProperty(OozieClient.APP_PATH, workflowParameters.getApplicationPath());
        conf.setProperty("applicationPath", workflowParameters.getApplicationPath());
        conf.setProperty("applicationName", workflowParameters.getApplicationName());
        conf.setProperty("applicationMainClass", workflowParameters.getApplicationMainClass());
        conf.setProperty("applicationSparkConf", workflowParameters.getApplicationSparkConf());
        conf.setProperty("applicationJar", workflowParameters.getApplicationJar());
        conf.setProperty("inputData", workflowParameters.getInputData());
        conf.setProperty("outputData", workflowParameters.getOutputData());

        return conf;
    }
}
