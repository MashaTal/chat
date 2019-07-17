package chat.controller;


import chat.entities.SessionParameters;
import chat.entities.User;
import chat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(value = "/users")
public class UserController {
    private  UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<User> listUsers(@RequestParam("roomId") String roomId) {
        return this.userService.getUsers(roomId);
    }

    @RequestMapping(value = "/validate",
            params = {"login", "password"},
            method = RequestMethod.GET)
    public @ResponseBody
    SessionParameters isUserExist(@RequestParam("login") String login, @RequestParam("password") String password) {
        return this.userService.getResponce(login,password);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody String addUser(@RequestParam("roomId") String roomId, @RequestBody User user) {
        logger.info("add user " + user);
        return this.userService.addUser(user, roomId);
    }
}
