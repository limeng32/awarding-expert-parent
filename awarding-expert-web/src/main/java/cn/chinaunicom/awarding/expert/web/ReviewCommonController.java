package cn.chinaunicom.awarding.expert.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewCommonController {

	@RequestMapping(method = { RequestMethod.GET }, value = "/review")
	public String review(HttpServletRequest request) {
		return "account/review";
	}

}
