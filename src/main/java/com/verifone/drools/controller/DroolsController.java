package com.verifone.drools.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verifone.drools.form.epf.EPFWithDrawForm;
import com.verifone.drools.logger.aspect.DroolsInOut;

@RestController
@RequestMapping("/drools/v1")
public class DroolsController {

   @PostMapping("/form/request")
   @DroolsInOut(request = "#args[0]", logger = "drools.logger")
   public ResponseEntity<EPFWithDrawForm> formRequest(@RequestBody EPFWithDrawForm epfWithDrawForm) {
      return new ResponseEntity<EPFWithDrawForm>(epfWithDrawForm, HttpStatus.ACCEPTED);
   }

}