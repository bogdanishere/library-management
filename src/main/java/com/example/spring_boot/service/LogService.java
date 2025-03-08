package com.example.spring_boot.service;

import com.example.spring_boot.model.Log;
import com.example.spring_boot.model.User;
import com.example.spring_boot.repository.LogRepository;
import com.example.spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;
    private final UserRepository userRepository;

    public LogService(LogRepository logRepository, UserRepository userRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Log> getLogs(String adminId) {
        User existingUser = userRepository.findById(adminId).orElse(null);

        if(existingUser == null || !existingUser.getAdmin()) {
            throw new RuntimeException("Admin not found!");
        }

        return logRepository.findAll();
    }


    @Transactional
    public void LoginAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.LOGIN);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void RegisterAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.REGISTER);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void DeleteUserAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.DELETE);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void CreateUserByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.CREATE_USER_BY_ADMIN);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void UpdateUserByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.UPDATE_USER_BY_ADMIN);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void BookRequestByUserAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.BOOK_REQUEST_BY_USER);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void BookRequestAcceptedByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.ACCEPT_REQUEST);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void BookRequestRejectedByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.REJECT_REQUEST);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void CreateBookByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.ADD_BOOK);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void UpdateBookByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.UPDATE_BOOK);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void DeleteBookByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.REMOVE_BOOK);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void CreateBuilddingByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.ADD_BUILDING);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void UpdateBuildingByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.UPDATE_BUILDING);
        log.setUser(user);
        logRepository.save(log);
    }

    @Transactional
    public void DeleteBuildingByAdminAction(User user) {
        Log log = new Log();
        log.setType(Log.LogType.DELETE_BUILDING);
        log.setUser(user);
        logRepository.save(log);
    }

}
