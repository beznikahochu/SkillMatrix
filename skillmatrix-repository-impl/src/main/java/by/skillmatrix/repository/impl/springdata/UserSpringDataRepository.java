package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpringDataRepository extends JpaRepository<UserEntity, Long> {

}
