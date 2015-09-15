package es.sugarsoft.test.support.domain;

public class Person {

    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Person: firstname=" + firstname + ", lastname=" + lastname;
    }
}
