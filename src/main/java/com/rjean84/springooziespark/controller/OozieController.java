package com.rjean84.springooziespark.controller;

import com.rjean84.springooziespark.model.WorkflowParameters;
import com.rjean84.springooziespark.service.OozieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OozieController {

    @Autowired
    private OozieService oozieService;

    @PostMapping("/oozie/submit")
    public ResponseEntity<String> submitWorkflow(@RequestBody WorkflowParameters workflowParameters) {
        //TODO : return the Oozie id
        return ResponseEntity.ok(oozieService.submitWorkflow(workflowParameters));
    }
}
