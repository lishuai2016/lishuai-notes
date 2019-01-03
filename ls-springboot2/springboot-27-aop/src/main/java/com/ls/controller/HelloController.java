package com.ls.controller;

import com.ls.annotation.executeTime;
import org.springframework.web.bind.annotation.*;

/**
 * @program: lishuai-notes
 * @author: lishuai
 * @create: 2018-12-16 21:55
 */
@RestController
public class HelloController {

    @executeTime
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello ";
    }

}

