package hh.sof03.forum.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;



public interface CategoryRepository extends CrudRepository<Category, Long>{
    Category findByCategoryid(Long categoryid);
    Category findByTopics(Topic topic);
    List<Category> findByHeader(String header);
}
