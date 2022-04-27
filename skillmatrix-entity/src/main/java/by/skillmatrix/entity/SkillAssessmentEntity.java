package by.skillmatrix.entity;

import javax.persistence.*;

//TODO: Изменить optional и cascade
@Entity
@Table(name = "skill_assessments")
public class SkillAssessmentEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assessment")
    private Byte assessment;

    @Column(name = "comment")
    private String comment;

    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "skill_matrix_id")
    private SkillMatrixEntity skillMatrix;

    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private SkillEntity skill;
}
