package ru.otus.dataset;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class AddressDataSet  {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "address_street", nullable = false)
    private String street;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private UserDataSet userDataSet;


    public AddressDataSet(String street) {
        this.street = street;
    }
}
