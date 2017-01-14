package cn.chinaunicom.awarding.expert.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExpertCommonController {

	public static final String UNIQUE_VIEW_NAME = "__unique_view_name";

	@RequestMapping(method = { RequestMethod.GET }, value = "/group")
	public String group(HttpServletRequest request) {
		return "account/group";
	}

}
