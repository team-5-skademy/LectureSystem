package skademy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LectureSystemRepository extends CrudRepository<LectureSystem, Long> {
}