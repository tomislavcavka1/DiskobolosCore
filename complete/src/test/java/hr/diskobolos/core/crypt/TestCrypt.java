/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.core.crypt;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Tomislav Čavka
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCrypt {

    @Autowired
    private IPasswordHashCalculator calculator;

    @Test
    public void PasswordCheck() {
        String result = calculator.hashPassword("mySecret");
        assertNotNull(result);
        boolean isValid = calculator.checkPassword("mySecret", result);
        assertTrue(isValid);
    }
}
