package com.projectx.projectx.helpers;

import org.springframework.stereotype.Component;

@Component
public class Message {
    
    public String noFieldProvided(String fieldName){
        return "Opps, You have\'nt provided " + fieldName + "!";
    }

    
    public String passwordsDoNotMatch(){
        return "Opps, Your both password is not same " + "!";
    }
}
