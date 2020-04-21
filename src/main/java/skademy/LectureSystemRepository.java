package skademy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LectureSystemRepository extends CrudRepository<LectureSystem, Long> {

    Optional<LectureSystem> findById(Long id);
}