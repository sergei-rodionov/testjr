package ru.sergeirodionov.testjr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sergeirodionov.testjr.model.User;
import ru.sergeirodionov.testjr.service.UserService;
import ru.sergeirodionov.testjr.validator.UserFormValidator;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by SergeiRodionov on 03.08.2015.
 */

@Controller
public class MainController {

    int RECORD_PER_PAGE = 5; // max row in page

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserFormValidator userFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userFormValidator);
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("index()");
        return "redirect:/users";
    }

    // list users
    @RequestMapping(value = {"/users"}, method = {RequestMethod.GET})
    public String showAllUsers(@RequestParam(value = "page", required = false) Integer currentPage,
                               Model model) {

        if (currentPage == null || currentPage == 0) currentPage = 1;
        int firstRecord = (currentPage == 1) ? 0 : (currentPage - 1) * RECORD_PER_PAGE;

        logger.debug("show users page #" + currentPage);

        model.addAttribute("maxPages", userService.getCountPages(RECORD_PER_PAGE, ""));
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("users", userService.findAllUsers(firstRecord, RECORD_PER_PAGE, "", ""));
        return "users/list";
    }

    // list search users
    @RequestMapping(value = {"/users/find/{searchUserName}"}, method = {RequestMethod.GET})
    public String searchUsers(@PathVariable("searchUserName") String userName,
                              @RequestParam(value = "page", required = false) Integer currentPage,
                              Model model) {

        if (currentPage == null || currentPage == 0) currentPage = 1;
        int firstRecord = (currentPage == 1) ? 0 : (currentPage - 1) * RECORD_PER_PAGE;

        logger.debug("show users page #" + currentPage);

        model.addAttribute("currentPage", currentPage);


        if (userName != null && userName.trim().length() > 2) {
            int count = userService.getCountPages(RECORD_PER_PAGE, userName);
            if (count > 0) {
                model.addAttribute("css", "success");
                model.addAttribute("msg", "User(s) *" + userName + "* found.");
                model.addAttribute("maxPages", count);
                model.addAttribute("users", userService.findAllUsers(firstRecord, RECORD_PER_PAGE, userName, ""));
                return "users/list";
            }
        }
        model.addAttribute("css", "danger");
        model.addAttribute("msg", "User not found or the name is too short");
        model.addAttribute("maxPages", userService.getCountPages(RECORD_PER_PAGE, ""));
        model.addAttribute("users", userService.findAllUsers(firstRecord, RECORD_PER_PAGE, "", ""));

        return "users/list";
    }

    // show user
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int userId, Model model) {

        logger.debug("show user id:" + userId);

        User user = userService.getUserById(userId);
        if (user == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }
        model.addAttribute("user", user);
        return "users/show";
    }

    // show update user form
    @RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") int userId, Model model) {

        logger.debug("show form update user id:" + userId);

        User user = userService.getUserById(userId);
        model.addAttribute("userForm", user);

        return "users/userform";
    }

    // delete user
    @RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int userId, final RedirectAttributes redirectAttributes) {

        logger.debug("deleteUser() : {}", userId);

        userService.deleteUser(userId);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is deleted!");
        return "redirect:/users";
    }

    // show add user form
    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {

        logger.debug("showAddUserForm()");
        User user = new User();
        model.addAttribute("userForm", user);

        return "users/userform";
    }

    // save or update user
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user,
                                   BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        logger.debug("saveOrUpdateUser : {}", user);
        if (result.hasErrors()) {
            return "users/userform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (user.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "User added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
            }

            // fill hidden field
            user.setCreateDate(new java.util.Date());
            userService.saveOrUpdateUser(user);

            // POST/REDIRECT/GET
            return "redirect:/users/" + user.getId();

            // POST/FORWARD/GET
            // return "user/list";
        }
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("user/show");
        model.addObject("msg", "user not found");

        return model;
    }
}
