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

    @Column(name = "address_street")
    private String street;

    @ToString.Exclude
    @OneToOne(mappedBy = "address")
    private UserDataSet userDataSet;

}
