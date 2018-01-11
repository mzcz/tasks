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

}
