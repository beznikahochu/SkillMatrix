package by.skillmatrix.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "people")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "is_employee", nullable = false)
    private Boolean isEmployee;
}
