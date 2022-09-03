package ru.otus.dataset;


import lombok.*;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserDataSet {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private int age;

    @Embedded
    @Target(PhoneDataSet.class)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<PhoneDataSet> phoneDataSetList = new ArrayList<>();

    @Embedded
    @Target(AddressDataSet.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDR_ADDR_ID")
    @ToString.Exclude
    private AddressDataSet address;

}
