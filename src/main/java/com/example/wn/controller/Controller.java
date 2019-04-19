package com.example.wn.controller;

import com.example.wn.Component.MyWebSocketHandler;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    public MyWebSocketHandler handler;

    @RequestMapping("/send")
    @ResponseBody
    public void SennerMsg(String msg){
        amqpTemplate.convertAndSend("exchange","a","GJ");
    }
    @RabbitListener(queues = "queue")
    public void Recive(String msg) throws Exception{
        handler.sendMsgToJsp(new TextMessage(msg), "A");
    }

    @RequestMapping("/test")
    public String goTest() throws Exception{
        return "test.html";
    }

}
