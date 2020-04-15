package com.soumya.springbasic.jpa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserCommandLiner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    public static final Logger logger = LoggerFactory.getLogger(UserCommandLiner.class);

    @Override
    public void run(String... args) {
        User user1 = userRepository.save(new User("soumya", "ADMIN"));
        User user2 = userRepository.save(new User("Rishi", "User"));
        User user3 = userRepository.save(new User("Harsha", "ADMIN"));
        User user4 = userRepository.save(new User("Raja", "User"));
        logger.info("----count ------" + userRepository.count());
        logger.info("----findByID ------" + userRepository.findById(user1.getId()).toString());
        logger.info("-------existing by id" + userRepository.existsById(user1.getId()));
        logger.info(" -------find all users before deleting--------");
        for (User user : userRepository.findAll()) {
            logger.info(user.getName() + "," + user.getRole());
        }
        logger.info(" ------------------------------");
        userRepository.deleteAll(Arrays.asList(user1, user2));
        userRepository.save(new User("soumya", "ADMIN"));
        userRepository.save(new User("Rishi", "User"));
        userRepository.save(new User("test", "ADMIN"));
        userRepository.save(new User("test1", "User"));
        userRepository.save(new User("soumya2222", "User"));
        userRepository.save(new User("test2", "User"));
        userRepository.save(new User("test3", "User"));
        logger.info(" -------find all users by Role--------");
        for (User user : userRepository.findByRole("User")) {
            logger.info(user.getName() + "," + user.getRole());
        }
        logger.info(" ------------------------------");
    }
}
