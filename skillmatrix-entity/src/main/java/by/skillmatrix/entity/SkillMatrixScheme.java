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
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;


@Data
@Entity
@Table(name = "skill_matrix_schemes")
public class SkillMatrixScheme {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (cascade= {CascadeType.REMOVE, CascadeType.DETACH}, mappedBy = "skillMatrixScheme")
    private List<SkillCategory> skillCategories;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (cascade= {CascadeType.REMOVE, CascadeType.DETACH}, mappedBy = "skillMatrixScheme")
    private List<SkillMatrix> skillMatrixEntities;
}
