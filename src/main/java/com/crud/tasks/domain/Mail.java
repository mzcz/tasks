package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {

    private  String receiverEmail;
    private  String toCc;
    private  String subject;
    private  String  message;

    public static boolean checkIsNullOrEmpty(String arg){
        if(!arg.trim().equals("") || !arg.trim().equals(" ") || arg.trim() != null) return true;
        return false;
    }
}
