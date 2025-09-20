package com.edigest.journalApp.service;

import com.edigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserRepository userRepository;
    @Test
    void testFindByUserName() {
        Assertions.assertTrue(5>3);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,1"

    })
    public void testa(int a,int b,int exp)
    {
        Assertions.assertEquals(exp,a+b);
    }
}
