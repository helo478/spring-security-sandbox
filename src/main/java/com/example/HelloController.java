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
    
    @RequestMapping("/stark_tower/{system}/usage")
    @PreAuthorize("hasPermission(#system, 'stark_tower', 'usage')")
    public String starkTowerUsage(final @PathVariable ("system") String system) {
    	
    	return String.format("You are authorized for usage of the %s in Stark Tower", system);
    }
    
    @RequestMapping("/stark_tower/{system}/self_destruct")
    @PreAuthorize("hasPermission(#system, 'stark_tower', 'self_destruct')")
    public String starkTowerSelfDestruct(final @PathVariable ("system") String system) {
    	
    	return String.format("You are authorized to engage self-destruct of %s in Stark Tower", system);
    }
    
    @RequestMapping("/shield_base/{system}/usage")
    @PreAuthorize("hasPermission(#system, 'shield_base', 'usage')")
    public String shieldBaseUsage(final @PathVariable ("system") String system) {
    	
    	return String.format("You are authorized for usage of the %s in the SHIELD base", system);
    }
    
    @RequestMapping("/shield_base/{system}/self_destruct")
    @PreAuthorize("hasPermission(#system, 'shield_base', 'self_destruct')")
    public String shieldBaseSelfDestruct(final @PathVariable ("system") String system) {
    	
    	return String.format("You are authorized to engage self-destruct of %s in the SHIELD base", system);
    }

}