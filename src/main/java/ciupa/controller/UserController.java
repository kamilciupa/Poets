package ciupa.controller;

import ciupa.model.Post;
import ciupa.model.User;
import ciupa.service.PostService;
import ciupa.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Kamil on 2017-09-11.
 */
@Controller
public class UserController {


    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @RequestMapping(value="/superadmin/viewUsers", method = RequestMethod.GET)
    public ModelAndView addPost(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        modelAndView.addObject("users", users);
        //  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", "Welcome " + user.getEmail());
        modelAndView.setViewName("/superadmin/viewUsers");
        return modelAndView;
    }

    @RequestMapping(value = "superadmin/viewUser/{id}/delete")
    public ModelAndView  delete(@PathVariable("id") Integer id){
        //ModelAndView modelAndView = new ModelAndView();
        //List<User> users = userService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        //modelAndView.addObject("users", users);
        userService.deleteById(id);
        //modelAndView.
        return new ModelAndView("redirect:/superadmin/viewUsers");
    }


}
