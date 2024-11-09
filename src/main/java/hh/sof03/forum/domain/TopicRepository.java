package hh.sof03.forum.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long>{
    List<Topic> findByCategory(Category category);
    Topic findByTopicid(Long topicid);
}
