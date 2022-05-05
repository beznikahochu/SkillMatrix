package by.skillmatrix.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "skills")
public class SkillEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne (optional=false)
    @JoinColumn(name = "skill_category_id")
    private SkillCategoryEntity skillCategory;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (cascade=CascadeType.REMOVE, mappedBy = "skill")
    private List<SkillAssessmentEntity> skillAssessmentEntities;
}
