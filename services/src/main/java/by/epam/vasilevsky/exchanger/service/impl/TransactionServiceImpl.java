package by.epam.vasilevsky.exchanger.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.epam.vasilevsky.exchanger.dataaccess.TransactionDao;
import by.epam.vasilevsky.exchanger.service.TransactionService;
import by.epam.vasilevsky.exchanger.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Inject
    private TransactionDao dao;

    @Inject
    private UserService userService;

}