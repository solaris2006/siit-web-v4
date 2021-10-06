package siit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session){
        ModelAndView mav = new ModelAndView();
        session.setAttribute("logged_user", null);
        mav.setViewName("redirect:/login");
        return mav;
    }

}
