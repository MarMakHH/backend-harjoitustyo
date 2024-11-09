package hh.sof03.forum.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTopic(Topic topic);
}
