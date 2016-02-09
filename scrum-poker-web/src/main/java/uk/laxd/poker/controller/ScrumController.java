package uk.laxd.poker.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.laxd.poker.dto.GreetingDto;
import uk.laxd.poker.dto.UserDto;

/**
 * Created by lawrence on 06/02/16.
 */
@Controller
public class ScrumController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/useradded")
    public GreetingDto addUser(UserDto userDto) {
        System.out.println("Adding user: " + userDto.getName());

        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setGreeting(userDto.getName() + " joined!");

        return greetingDto;
    }

}
