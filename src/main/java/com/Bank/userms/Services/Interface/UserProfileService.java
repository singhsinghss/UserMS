package com.Bank.userms.Services.Interface;

import com.Bank.userms.Models.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile createUserProfile(UserProfile userProfile);
    UserProfile getUserById(Long id);
    List<UserProfile> getAllUsers();
    UserProfile updateUserProfile(UserProfile userProfile,Long id);
}
