package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/rick_lab/{function}")
    @PreAuthorize("hasPermission(#function, 'rick_lab', 'owner')")
    public String rickLabOwner(final @PathVariable ("function") String function) {
    	
    	return String.format("You are an authorized owner for function %s of rick's lab", function);
    }
    
    @RequestMapping("/rick_lab/{function}")
    @PreAuthorize("hasPermission(#function, 'rick_lab', 'admin')")
    public String rickLabAdmin(final @PathVariable ("function") String function) {
    	
    	return String.format("You are an authorized admin for function %s of rick's lab", function);
    }
    
    @RequestMapping("/vindicator_base/{function}")
    @PreAuthorize("hasPermission(#function, 'vindicator_base', 'owner')")
    public String vindicatorBaseOwner(final @PathVariable ("function") String function) {
    	
    	return String.format("You are an authorized owner for function %s of the vindicator base", function);
    }
    
    @RequestMapping("/vindicator_base/{function}")
    @PreAuthorize("hasPermission(#function, 'vindicator_base', 'admin')")
    public String vindicatorBaseAdmin(final @PathVariable ("function") String function) {
    	
    	return String.format("You are an authorized admin for function %s of the vindicator base", function);
    }

}