package com.sportify.Sportify.model;

import com.sportify.Sportify.annotation.FieldEquals;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldEquals(field = "password", equalsTo = "passwordConfirm", message = "Пароли не совпадают")
public class UserDTO {

    @Size(min = 3, max = 25, message = "Должен быть от 3 до 25 символов")
    @NotNull(message = "Поле логин не может быть пустым")
    private String username;

    @Size(min = 4, message = "Пароль должен быть от 4 символов")
    @NotNull(message = "Поле пароль не может быть пустым")
    private String password;

    @NotNull
    private String passwordConfirm;

    @NotNull(message = "Поле email не может быть пустым")
    private String eMail;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String passwordConfirm, String eMail) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.eMail = eMail;
    }
}
