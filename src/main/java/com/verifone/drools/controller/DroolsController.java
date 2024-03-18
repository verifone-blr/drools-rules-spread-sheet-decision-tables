package com.verifone.drools.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verifone.drools.form.epf.EPFWithDrawForm;
import com.verifone.drools.logger.DroolsLogger;
import com.verifone.drools.logger.aspect.DroolsInOut;

@RestController
@RequestMapping("/drools/v1")
public class DroolsController {

   @Autowired
   private KieSession session;

   @PostMapping("/epf/withdraw/request")
   @DroolsInOut(request = "#args[0]", logger = "drools.logger")
   public ResponseEntity<EPFWithDrawForm> formRequest(@RequestBody EPFWithDrawForm epfWithDrawForm) {

      session.insert(epfWithDrawForm);
      int fireAllRules = session.fireAllRules();

      DroolsLogger.info("Fired Rules : " + fireAllRules);

      return new ResponseEntity<EPFWithDrawForm>(epfWithDrawForm, HttpStatus.ACCEPTED);
   }

}