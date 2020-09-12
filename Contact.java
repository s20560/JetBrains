package contacts;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

    private String phoneNumber;
    private final LocalDateTime creationTime = LocalDateTime.now();
    private LocalDateTime lastEditTime = creationTime;
    private final TYPE type;

    public Contact(String phoneNumber, TYPE type) {
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public static boolean checkNumberValidation(String numberInput) {
        Pattern pattern = Pattern.compile("\\+?" +
                "(\\s?([\\da-zA-Z]+([ -]\\([\\da-zA-Z]{2,}\\))?)|(\\([\\da-zA-Z]+\\)([ -][\\da-zA-Z]{2,})?))" +
                "([ -][\\da-zA-Z]{2,})*");
        Matcher matcher = pattern.matcher(numberInput);
        return matcher.matches();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        setLastEditTime(LocalDateTime.now());
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

}
