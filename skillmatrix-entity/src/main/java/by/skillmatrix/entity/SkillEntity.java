package by.skillmatrix.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

//TODO: Изменить optional и cascade
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
}
