package ua.goit.dev6.signin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.goit.dev6.account.UserDAO;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final UsersService userService;

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Pattern pattern= Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    private Matcher matcher;

    public boolean supports(Class<?> aClass) {
        return UserDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDAO user = (UserDAO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (!pattern.matcher(user.getUsername()).matches()) {
            errors.rejectValue("username", "Check.correct.password");
        }
//        if (user.getUsername().length() < 5 || user.getUsername().length() > 50) {
//            errors.rejectValue("username", "Size.userForm.username");
//        }
        List<UserDAO> byName = userService.findByName(user.getUsername());
        if (!userService.findByName(user.getUsername()).isEmpty()) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 100) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}