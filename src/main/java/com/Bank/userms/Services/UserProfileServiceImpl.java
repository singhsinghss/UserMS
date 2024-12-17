package com.Bank.userms.Services;

import com.Bank.userms.Models.UserProfile;
import com.Bank.userms.Repositories.UserProfileRepository;
import com.Bank.userms.Services.Interface.UserProfileService;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private final UserProfileRepository userProfileRepository;
    @Override
    public UserProfile createUserProfile(UserProfile userProfile) {

        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile getUserById(Long id) {
        Optional<UserProfile> userProfile=userProfileRepository.findById(id);
        if(userProfile.isPresent())
        {
            return userProfile.get();
        }
        else
        {
            throw new RuntimeException("User not found with id: " + id);
        }

    }

    @Override
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile updateUserProfile(UserProfile userProfile,Long id) {
        Optional<UserProfile> existingUser = userProfileRepository.findById(id);
        if (existingUser.isPresent()) {
            UserProfile user = existingUser.get();
            user.setUsername(userProfile.getUsername());
            user.setEmail(userProfile.getEmail());
            user.setPhone_number(userProfile.getPhone_number());
            user.setPassword_hash(userProfile.getPassword_hash());
            user.setUpdatedAt(LocalDateTime.now()); // Set the updatedAt field
            user.setTwo_factor_enabled(userProfile.isTwo_factor_enabled());
            user.setKyc_status(userProfile.getKyc_status());
            return userProfileRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }
}
