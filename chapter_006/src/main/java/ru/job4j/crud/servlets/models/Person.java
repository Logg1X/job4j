package ru.job4j.crud.servlets.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Firstnamae",
        "Lastname",
        "email",
        "Password",
        "sex"
})
public class Person {

    @JsonProperty("Firstnamae")
    private String firstnamae;
    @JsonProperty("Lastname")
    private String lastname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("sex")
    private boolean sex;

    /**
     * No args constructor for use in serialization
     */
    public Person() {
    }

    /**
     * @param firstnamae
     * @param sex
     * @param email
     * @param lastname
     * @param password
     */
    public Person(String firstnamae, String lastname, String email, String password, boolean sex) {
        super();
        this.firstnamae = firstnamae;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.sex = sex;
    }

    @JsonProperty("Firstnamae")
    public String getFirstnamae() {
        return firstnamae;
    }

    @JsonProperty("Firstnamae")
    public void setFirstnamae(String firstnamae) {
        this.firstnamae = firstnamae;
    }

    @JsonProperty("Lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("Lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("Password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("Password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("sex")
    public boolean isSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(boolean sex) {
        this.sex = sex;
    }

}