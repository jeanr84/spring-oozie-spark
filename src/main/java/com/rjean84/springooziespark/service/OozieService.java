package com.rjean84.springooziespark.service;

import com.rjean84.springooziespark.model.WorkflowParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OozieService {

    public String submitWorkflow(WorkflowParameters workflowParameters) {
        return workflowParameters.getApplicationName();
    }
}
