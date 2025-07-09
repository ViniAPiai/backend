
package com.vini.piai.backend.api.access.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vini.piai.backend.api.access.user.dto.UserDtoResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDtoResponse> me() {
        return ResponseEntity.ok(userService.current().getDtoResponse());
    }

}
