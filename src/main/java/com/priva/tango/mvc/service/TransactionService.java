package com.priva.tango.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    @Transactional
    public void test1(){
        int i = 1/0;
    }

    public void test2(){
        int i = 1/0;
    }
}
