package net.itfans.prototype.web.demo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/demo1")
public class Demo1Controller {

    private static final Logger logger =
            LoggerFactory.getLogger(Demo1Controller.class);

    @RequestMapping(path = "menu", method = RequestMethod.GET)
    public String menu(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("UserId", "lizheng");
        return "demo1/menu";
    }

    @RequestMapping(path = "start", method = RequestMethod.GET)
    public String startPage(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("date", new Date());
        return "demo1/start";
    }

    @RequestMapping(path = "end", method = RequestMethod.GET)
    public String endPage(HttpServletRequest request) {
        return "demo1/end";
    }
}
