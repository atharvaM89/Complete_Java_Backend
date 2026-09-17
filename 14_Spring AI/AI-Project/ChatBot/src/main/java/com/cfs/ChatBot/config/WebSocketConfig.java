package com.cfs.ChatBot.config;


import com.cfs.ChatBot.controller.GemeniWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private GemeniWebSocketHandler gemeniWebSocketHandler;

    @Autowired
    public WebSocketConfig(GemeniWebSocketHandler gemeniWebSocketHandler)
    {
        this.gemeniWebSocketHandler=gemeniWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(gemeniWebSocketHandler,"/chat").setAllowedOrigins("*");
    }
}
