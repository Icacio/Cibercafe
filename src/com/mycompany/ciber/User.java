package com.mycompany.ciber;

import java.time.Instant;

public class User {
    public Instant timeStart;
    public String userNumber;
    public float consumo;
    
    public User(int userNumber) {
        timeStart = Instant.now();
        this.userNumber = "PC número "+Integer.toString(userNumber);
        
    }
}
