package com.nsc.kafkastreamdemo.controller;

import com.nsc.kafkastreamdemo.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private MessageSender messageSender;

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody Object value) {
        messageSender.send(value);
    }
}
