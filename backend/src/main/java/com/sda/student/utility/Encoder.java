package com.sda.student.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder {
    public static void main(String[] args){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
          System.out.println(encoder.encode("user"));
    }
}
