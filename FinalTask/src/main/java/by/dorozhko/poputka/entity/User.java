package by.dorozhko.poputka.entity;


import java.time.LocalDate;
import java.util.Objects;

public class User extends Entity {
    private String login;
    private String password;
    private String salt;
    private Role role;
    private String name;
    private String surname;
    private String gender;
    private LocalDate birthday;
    private String country;
    private String passportNumber;
    private LocalDate passportDateOfIssue;
    private String phoneNumber;
    private String email;
    private int drivingExperience;
    private Car car;

    public User() {
    }

    public User(String login, String password, String name, String surname, String gender, String birthday, String country, String passportNumber, String passportDateOfIssue, String phoneNumber, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = LocalDate.parse(birthday);
        this.country = country;
        this.passportNumber = passportNumber;
        this.passportDateOfIssue = LocalDate.parse(passportDateOfIssue);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String login, String password, String name, String surname, String gender, String birthday, String country, String passportNumber, String passportDateOfIssue, String phoneNumber, String email, int drivingExperience, Car car) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = LocalDate.parse(birthday);
        this.country = country;
        this.passportNumber = passportNumber;
        this.passportDateOfIssue = LocalDate.parse(passportDateOfIssue);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.drivingExperience = drivingExperience;
        this.car = car;
    }

    public User(String login, String name, String surname, String gender, String birthday, String country, String passportNumber, String passportDateOfIssue, String phoneNumber, String email) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = LocalDate.parse(birthday);
        this.country = country;
        this.passportNumber = passportNumber;
        this.passportDateOfIssue = LocalDate.parse(passportDateOfIssue);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String login, String name, String surname, String gender, String birthday, String country, String passportNumber, String passportDateOfIssue, String phoneNumber, String email, int drivingExperience, Car car) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = LocalDate.parse(birthday);
        this.country = country;
        this.passportNumber = passportNumber;
        this.passportDateOfIssue = LocalDate.parse(passportDateOfIssue);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.drivingExperience = drivingExperience;
        this.car = car;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final int newRole) {
        this.role = Role.getById(newRole);
    }

    public String getName() {
        return name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPassportDateOfIssue(LocalDate passportDateOfIssue) {
        this.passportDateOfIssue = passportDateOfIssue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = LocalDate.parse(birthday);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getPassportDateOfIssue() {
        return passportDateOfIssue;
    }

    public void setPassportDateOfIssue(String passportDateOfIssue) {
        this.passportDateOfIssue = LocalDate.parse(passportDateOfIssue);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return drivingExperience == user.drivingExperience &&
                login.equals(user.login) &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                gender.equals(user.gender) &&
                birthday.equals(user.birthday) &&
                country.equals(user.country) &&
                passportNumber.equals(user.passportNumber) &&
                passportDateOfIssue.equals(user.passportDateOfIssue) &&
                phoneNumber.equals(user.phoneNumber) &&
                email.equals(user.email) &&
                car.equals(user.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, name, surname, gender, birthday, country, passportNumber, passportDateOfIssue, phoneNumber, email, drivingExperience, car);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportDateOfIssue=" + passportDateOfIssue +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", drivingExperience=" + drivingExperience +
                ", car=" + car +
                '}';
    }
}
