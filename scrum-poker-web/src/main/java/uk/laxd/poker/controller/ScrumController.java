package uk.laxd.poker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lawrence on 06/02/16.
 */
@Controller
public class ScrumController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
