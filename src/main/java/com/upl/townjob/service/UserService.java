package com.upl.townjob.service;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.model.User;
import com.upl.townjob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User createUser(User user){
        return userRepository.save(user);
    }

    public User retrieveUser(UUID id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(UUID id, User user){
       if(userRepository.existsById(id)){
           User existUser = userRepository.findById(id).get();
           existUser.setCandidacies(user.getCandidacies());
           existUser.setMail(user.getMail());
           existUser.setName(user.getName());
           existUser.setPassword(user.getPassword());
           return userRepository.save(existUser);
       }
       return null;
    }

    public User loginUser(String mail, String password){
        return  userRepository.findUserByMailAndPassword(mail, password).orElse(null);
    }

    public List<Candidacy> retrieveUserCandidacies(UUID id){
       User user = userRepository.findById(id).orElse(null);
       if(user != null){
           return user.getCandidacies();
       }
       return null;
    }

    public List<JobOffer> retrieveUserSavedJobOffers(UUID id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get().getSavedJobOffers();
        }
        return  null;
    }

    public User saveJobOffer(UUID id,JobOffer jobOffer){
        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            List<JobOffer> jobOffers= user.getSavedJobOffers();
            jobOffers.add(jobOffer);
            user.setSavedJobOffers(jobOffers);
            return userRepository.save(user);
        }
        return  null;
    }
}
