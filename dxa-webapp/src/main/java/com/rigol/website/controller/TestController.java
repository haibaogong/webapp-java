package com.rigol.website.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("test")
public class TestController {

    /**
     *  test
     * @param jsonParams
     * @return
     */
    @RequestMapping(value = "hello")
    @ResponseBody
    public String getImageValidateCode(String jsonParams) {
        return "Hello World!";
    }
}
