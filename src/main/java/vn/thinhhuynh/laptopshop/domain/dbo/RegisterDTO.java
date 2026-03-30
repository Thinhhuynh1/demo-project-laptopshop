package vn.thinhhuynh.laptopshop.domain.dbo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import vn.thinhhuynh.laptopshop.service.validator.RegisterChecked;

@RegisterChecked
public class RegisterDTO {
    @Size(min = 3, message = "FirstName phai co toi thieu 3 ky tu")
    private String firstName;

    private String lastName;

    @Email(message = "Email không hợp lệ", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    private String password;

    @Size(min = 3, message = "confirmPassword phai co toi thieu 3 ky tu")
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
