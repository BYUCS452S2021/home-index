package net.holderness.brian.homeindexapi.controller;

import net.holderness.brian.homeindexapi.model.Property;
import net.holderness.brian.homeindexapi.model.UserAccount;
import net.holderness.brian.homeindexapi.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAccountController {

    private final UserAccountService userAccountService;

    UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/user-account/{username}")
    public Optional<UserAccount> findByUsername(@PathVariable String username) {
        return userAccountService.findByUsername(username);
    }

    @GetMapping("/user-account/{username}/properties")
    public List<Property> getUserProperties(@PathVariable String username) {
        return userAccountService.getUserProperties(username);
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @PutMapping("/user-account/{username}")
    public UserAccount replace(@RequestBody UserAccount userAccount) {
        return userAccountService.replace(userAccount);
    }

    @DeleteMapping("/user-account/{username}")
    public void delete(@PathVariable String username) {
        userAccountService.delete(username);
    }
}
