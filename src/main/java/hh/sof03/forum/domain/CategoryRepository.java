package hh.sof03.forum.domain;

import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long>{
    Category findByCategoryid(Long categoryid);
    Category findByTopics(Topic topic);
}
