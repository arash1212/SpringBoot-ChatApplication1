package com.example.springbootchatapplication1.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/view")
public class ViewController {

    @GetMapping("/")
    public String goToIndex() {
        return "index";
    }

    @GetMapping("/pub/user/create")
    public String goToUserRegister() {
        return "/pub/user/create";
    }

    @GetMapping("/pub/user/login")
    public String goToUserLogin() {
        return "/pub/user/login";
    }

    @GetMapping("/pub/user/{username}")
    public String goToUserProfile(@PathVariable(name = "username") String username) {
        return "/pub/user/user";
    }

    /****************************************************user*********************************************************/

    @GetMapping("/user/chat/chat/{title}")
    public String goToChat(@PathVariable(name = "title") String title) {
        return "/user/chat/chat";
    }

    @GetMapping("/user/chat/chats/")
    public String goToChatSearch() {
        return "/user/chat/chats";
    }

    /****************************************************admin*********************************************************/
    @GetMapping("/adm/")
    public String goToAdmIndex() {
        return "/adm/index";
    }

    @GetMapping("/adm/authority")
    public String goToAdmAuthorities() {
        return "/adm/authority/authority";
    }

    @GetMapping("/adm/authority/create")
    public String goToAdmAuthorityCreate() {
        return "/adm/authority/create";
    }

    @GetMapping("/adm/messaging/provider/")
    public String goToAdmProviders() {
        return "/adm/messageProvider/messageProvider";
    }

    @GetMapping("/adm/messaging/provider/create")
    public String goToAdmProviderCreate() {
        return "/adm/messageProvider/create";
    }

    @GetMapping("/adm/messaging/create")
    public String goToAdmMessagingCreate() {
        return "/adm/messaging/create";
    }

    @GetMapping("/adm/user/users")
    public String goToAdmUsers() {
        return "/adm/user/users";
    }
}