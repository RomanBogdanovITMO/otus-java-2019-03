package ru.otus.message_server.dataset;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Phone extends DataSet {
    private String number;

    @ManyToOne
    private UserDataS userDataSet;

    public Phone(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UserDataS getUserDataSet() {
        return userDataSet;
    }

    public void setUserDataSet(UserDataS userDataSet) {
        this.userDataSet = userDataSet;
    }
}
