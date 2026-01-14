package org.cfs.Boot_P01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class Notification {
    //Creating obj of MessageService
    @Autowired
    private MessageService messageService;

    //default Constructor
    public Notification(){

    }

    //getter
    public MessageService getMessageService() {
        return messageService;
    }

    //setter
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    //Argument Constructor
    public Notification(MessageService messageService){
        this.messageService=messageService;
    }

    public void notifyUser(){
        System.out.println(messageService.sendMessage());
    }
    //DI here it need obj of Class EmailService
}
