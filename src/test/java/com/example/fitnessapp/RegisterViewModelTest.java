package com.example.fitnessapp.ui.register;

import static org.junit.Assert.*;

import android.util.Patterns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


    @RunWith(Parameterized.class)
    public class RegisterViewModelTest {
        private String input;
        private boolean expectedOutput;

        public RegisterViewModelTest(String input, boolean expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> testCases() {
            return Arrays.asList(new Object[][]{
                    {"null", false},
                    {"", false},
                    {"u@a.r", false},
                    {"vlad@gmail.com", true},
                    {"matei@yahoo", false},
                    {"bogdan.domain.com", false},
                    {"fitfuelAdmin123@domain.com", true},
            });
        }

        //@Test
        //public void testIsUserNameValid() {
        //    RegisterViewModel validator = new RegisterViewModel();
        //    assertEquals(expectedOutput, validator.isUserNameValid(input));
//
        //}

        @Test
        public void testIsUserNameValid() {
            RegisterViewModel validator = new RegisterViewModel();

            // Test invalid usernames with min and max lengths
            assertFalse(validator.isUserNameValid("u@a.r")); //5
            assertTrue(validator.isUserNameValid("u@a.ro")); //6
            assertTrue(validator.isUserNameValid("uu@a.ro")); //7

            assertTrue(validator.isUserNameValid("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@a.ro")); //48
            assertFalse(validator.isUserNameValid("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@a.ro")); //49
            assertFalse(validator.isUserNameValid("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@a.ro")); //50
        }

        @Test
        public void countCalories() {
            String[] foods1 ={};
            int expected = 0;
            int actual = RegisterViewModel.countCalories(foods1);
            assertEquals(expected, actual);

            String[] foods2 = {"apple", "banana", "orange"};
            int expected2 = 262;
            int actual2 = RegisterViewModel.countCalories(foods2);
            assertEquals(expected2, actual2);

            String[] foods3 = {"banana"};
            int expected3 = 105;
            int actual3 = RegisterViewModel.countCalories(foods3);
            assertEquals(expected3, actual3);

            String[] foods4 = {"banana", "apple"};
            int expected4 = 200;
            int actual4 = RegisterViewModel.countCalories(foods4);
            assertEquals(expected4, actual4);

            String[] foods5 = {"carrot", "lettuce", "spinach"};
            int expected5 = 0;
            int actual5 = RegisterViewModel.countCalories(foods5);
            assertEquals(expected5, actual5);
        }
    }