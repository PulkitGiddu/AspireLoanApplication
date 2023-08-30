package com.app.aspireloan.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @GetMapping("/")
    public String home()
    {
        String str
                = "<html><body><font color=\"green\">"
                + "<h1>WELCOME To Aspire App</h1>"
                + "</font></body></html>";
        return str;
    }

    // Another syntax to implement a
    // GET method
    @RequestMapping(
            method = { RequestMethod.GET },
            value = { "/aspire" })

    public String info()
    {
        String str2
                = "<html><body><font color=\"green\">"
                + "<h2>Aspire is a Fintech company."
                + " Aspire is the all-in-one finance software for growing businesses. The company serves over 15,000 startups and SMBs in Southeast Asia, helping them save time and money with multi-currency accounts and cards, expense management, payable management, and receivable management solutions - all in one account.\n" +
                "\n" +
                "Headquartered in Singapore, Aspire has over 400 employees across four countries and is backed by global top tier VCs, including Sequoia, Lightspeed, and Y-Combinator."
                + "</h2></font></body></html>";
        return str2;
    }
}
