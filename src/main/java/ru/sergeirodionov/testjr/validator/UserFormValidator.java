package ru.sergeirodionov.testjr.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sergeirodionov.testjr.model.User;
import ru.sergeirodionov.testjr.service.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SergeiRodionov on 05.08.2015.
 */
@Component
public class UserFormValidator implements Validator {

    @Autowired
    UserService userService;

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
    private static final String AGE_PATTERN = "^(0?[1-9]|[1-9][0-9])$";

    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty.userForm.age");

        User user = (User) target;

        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(user.getName());
        if (!matcher.matches()) {
            errors.rejectValue("name", "NotEmpty.userForm.name");
        }

        pattern = Pattern.compile(AGE_PATTERN);
        matcher = pattern.matcher(user.getAge().toString());
        if (!matcher.matches()) {
            errors.rejectValue("age", "NotEmpty.userForm.age");
        }
    }
}
