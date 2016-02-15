package uk.laxd.poker.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.laxd.poker.dto.IssueDto;
import uk.laxd.poker.dto.UserDto;
import uk.laxd.poker.dto.VoteDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lawrence on 06/02/16.
 */
@Controller
public class ScrumController {

    private List<UserDto> users = new ArrayList<>();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/userConnect")
    public UserDto addUser(UserDto userDto) {
        this.users.add(userDto);
        return userDto;
    }

    @SubscribeMapping("/users")
    public List<UserDto> getUsers() {
        return users;
    }

    @MessageMapping("/removeUser")
    @SendTo("/topic/userDisconnect")
    public UserDto removeUser(UserDto userDto) {
        this.users.remove(userDto);
        return userDto;
    }

    @MessageMapping("/vote")
    @SendTo("/topic/vote")
    public VoteDto vote(VoteDto voteDto) {
        return voteDto;
    }

    @MessageMapping("/newIssue")
    @SendTo("/topic/newIssue")
    public IssueDto newIssue(IssueDto issueDto) {
        return issueDto;
    }

}
