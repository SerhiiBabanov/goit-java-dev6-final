package ua.goit.dev6.account;

import lombok.Data;
@Data
public class ChangePasswordForm {
    String oldPassword;
    String password;
    String passwordConfirm;
}
