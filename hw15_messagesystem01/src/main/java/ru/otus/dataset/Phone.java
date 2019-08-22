package ru.otus.dataset;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Data
@Entity
public class Phone extends DataSet {
    private String number;

    @ManyToOne
    private UserDataS userDataSet;

    public Phone(String number) {
        this.number = number;
    }
}
