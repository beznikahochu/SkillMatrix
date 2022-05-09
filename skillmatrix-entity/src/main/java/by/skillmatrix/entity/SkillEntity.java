package by.skillmatrix.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne (optional=false)
    @JoinColumn(name = "skill_category_id", updatable = false, nullable = false)
    private SkillCategoryEntity skillCategory;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (cascade=CascadeType.REMOVE, mappedBy = "skill")
    private List<SkillAssessmentEntity> skillAssessments;
}
