package com.jeanr84.springapi.controller;

import com.jeanr84.springapi.model.WorkflowParameters;
import com.jeanr84.springapi.service.OozieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OozieController {

    @Autowired
    private OozieService oozieService;

    @PostMapping("/oozie/submit")
    public ResponseEntity<String> submitWorkflow(@RequestBody WorkflowParameters workflowParameters) {
        //TODO : return the Oozie id
        Optional<String> jobIdOptional = oozieService.submitWorkflow(workflowParameters);
        if (jobIdOptional.isPresent()) {
            return ResponseEntity.ok(jobIdOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
