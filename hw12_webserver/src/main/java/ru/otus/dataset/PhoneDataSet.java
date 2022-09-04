package ru.otus.dataset;

import lombok.*;
import org.hibernate.annotations.Parent;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.Objects;
@Embeddable
@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phone")
public class PhoneDataSet  {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "phone_number")
    private String number;


    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDataSet user;

}
