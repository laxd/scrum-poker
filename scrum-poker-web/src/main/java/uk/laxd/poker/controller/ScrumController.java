package uk.laxd.poker.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.laxd.poker.dto.UserDto;
import uk.laxd.poker.dto.VoteDto;

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
    @SendTo("/topic/userConnect")
    public UserDto addUser(UserDto userDto) {
        return userDto;
    }

    @MessageMapping("/removeUser")
    @SendTo("/topic/userDisconnect")
    public UserDto removeUser(UserDto userDto) {
        return userDto;
    }

    @MessageMapping("/vote")
    @SendTo("/topic/vote")
    public VoteDto vote(VoteDto voteDto) {
        return voteDto;
    }

}
