package ciupa.controller;

import ciupa.model.Post;
import ciupa.model.User;
import ciupa.service.PostService;
import ciupa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kamil on 2017-09-07.
 */
@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @RequestMapping(value="/admin/addPost", method = RequestMethod.GET)
    public ModelAndView addPost(){
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("post", post);
        modelAndView.addObject("user", user);
        //  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", "Welcome " + user.getEmail());
        modelAndView.setViewName("/admin/addPost");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addPost", method = RequestMethod.POST)
    public ModelAndView createPost(@Valid Post post, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

      //  Post po = new Post(post.getTitle(), post.getBody(), user);
        post.setAutor(user.getName() + " " + user.getLastName());


        post.setSrednia(0.0F);
        post.setVotesAmount(0);


        postService.savePost(post);
        modelAndView.addObject("userName", "Welcome " + user.getEmail());
        modelAndView.addObject("successMessage", "User has been registered successfully");
        //modelAndView.addObject("user", new User());
        modelAndView.setViewName("/admin/addPost");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/viewPosts", method = RequestMethod.GET)
    public ModelAndView getViewPosts(){
        ModelAndView modelAndView = new ModelAndView();
        List<Post> posts = postService.findAll();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", "Welcome " + user.getEmail());
        modelAndView.addObject("posts", posts);

        modelAndView.setViewName("/admin/viewPosts");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/viewPost/{id}", method = RequestMethod.GET)
    public ModelAndView getViewPost(@PathVariable("id") Integer id){

        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Post post = postService.findById(id);
        if(post == null)
        {

        }

        modelAndView.addObject("post", post);
        modelAndView.addObject("userName", "Welcome " + user.getEmail());
        modelAndView.setViewName("/admin/viewPost");
        return modelAndView;
    }

    @RequestMapping(value = "admin/viewPost/{id}/vote/{ocena}")
    public ModelAndView  delete(@PathVariable("id") Integer id, @PathVariable int ocena){
        //ModelAndView modelAndView = new ModelAndView();
        //List<User> users = userService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        postService.addVote(id,ocena);

        //modelAndView.
        return new ModelAndView("redirect:/admin/viewPosts");
    }

    @RequestMapping(value = "/admin/toplist", method = RequestMethod.GET)
    public ModelAndView getToplist(){
        ModelAndView modelAndView = new ModelAndView();
        List<Post> posts = postService.makeToplistVotes();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", "Welcome " + user.getEmail());
        modelAndView.addObject("posts", posts);

        modelAndView.setViewName("/admin/toplist");
        return modelAndView;
    }


}
