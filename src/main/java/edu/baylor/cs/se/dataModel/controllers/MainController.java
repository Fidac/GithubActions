package edu.baylor.cs.se.dataModel.controllers;

import edu.baylor.cs.se.dataModel.model.*;
import edu.baylor.cs.se.dataModel.services.SuperService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@AllArgsConstructor
public class MainController {

    private SuperService superService;

    @Autowired
    public MainController(SuperService superService) {
        this.superService = superService;
    }

    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    public ResponseEntity<?> getPopulate() {
        superService.populate();
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
