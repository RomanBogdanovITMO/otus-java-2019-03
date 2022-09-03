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
@Table(name = "phone")
public class PhoneDataSet {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "phone_number", nullable = false)
    private String number;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private UserDataSet user;

}
