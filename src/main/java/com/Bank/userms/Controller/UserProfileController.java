package com.Bank.userms.Controller;

import com.Bank.userms.Models.UserProfile;
import com.Bank.userms.Services.UserProfileServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserProfileController {

    private static final Logger logger = LogManager.getLogger(UserProfileController.class);
    private final UserProfileServiceImpl userProfileServiceImpl;

    @PostMapping("/register")
    private ResponseEntity<UserProfile> createUserProfile(@Valid @RequestBody UserProfile userProfile)
    {
        return new ResponseEntity<>(userProfileServiceImpl.createUserProfile(userProfile),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    private ResponseEntity<UserProfile> getUserProfileById(@PathVariable Long id)
    {
        return new ResponseEntity<>(userProfileServiceImpl.getUserById(id),
                HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<UserProfile>> getAllUsers()
    {
        return  new ResponseEntity<>(userProfileServiceImpl.getAllUsers(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    private ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile userProfile,@PathVariable Long id)
    {
            try {
            UserProfile updatedUser = userProfileServiceImpl.updateUserProfile(userProfile,id);
            return ResponseEntity.ok(updatedUser); // Return the updated user
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // User not found, return 404
        }
    }
    @ExceptionHandler
    public ResponseEntity<String> respondWithError(Exception e){
        logger.error("Exception Occurred. Details : {}", e.getMessage());
        return new ResponseEntity<>("Exception Occurred. More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
