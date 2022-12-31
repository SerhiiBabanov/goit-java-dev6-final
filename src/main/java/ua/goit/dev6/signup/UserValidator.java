package ua.goit.dev6.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.account.UserService;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final UserService userService;

    private static final String EMAIL_REGEX = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final Pattern pattern= Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        validateEmail(user, errors);
        validatePassword(user, errors);
    }

    public void validateEmail(UserDTO user, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!pattern.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "Check.correct.email");
        }
//        if (user.getUsername().length() < 5 || user.getUsername().length() > 50) {
//            errors.rejectValue("username", "Size.userForm.username");
//        }
        if (!userService.findByName(user.getEmail()).isEmpty()) {
            errors.rejectValue("email", "Duplicate.userForm.username");
        }
    }

    public void validatePassword(UserDTO user, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 100) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}