package com.virusvaccine.controller;

import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.ReturnForm;
import com.virusvaccine.exception.NotLoginException;
import com.virusvaccine.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/lookup")
public class LookupController {

    private final String userKey = UserController.userKey;

    @Autowired
    private LookupService lookupService;

    @PostMapping("/agency")
    public List<ReturnForm> agency(@RequestBody LookupRequest lookupRequest, HttpSession session){

        if(session.getAttribute(userKey)==null){
            throw new NotLoginException();
        }

        return lookupService.lookup(lookupRequest);

    }
}
