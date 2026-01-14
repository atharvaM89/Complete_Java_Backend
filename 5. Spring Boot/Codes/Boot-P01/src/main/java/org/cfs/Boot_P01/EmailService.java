package org.cfs.Boot_P01;

import org.springframework.stereotype.Service;


@Service

public class EmailService implements MessageService{
    @Override
    public String sendMessage() {
        return "Email: You have got a new message! ";
    }
}
