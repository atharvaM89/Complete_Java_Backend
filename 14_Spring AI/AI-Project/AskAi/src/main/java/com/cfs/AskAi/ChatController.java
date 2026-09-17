package com.cfs.AskAi;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {
    private final ChatClient chatClient;

    @Autowired
    public ChatController(OpenAiChatModel openAiChatModel)
    {
        this.chatClient=ChatClient.create(openAiChatModel);
    }

    @PostMapping
    public Map<String,String> chat(@RequestBody Map<String,String> body)
    {
        String question= body.get("question");
        String answer=chatClient.prompt()
                .user(question)
                .call()
                .content();
        return Map.of("answer",answer);
    }
}
