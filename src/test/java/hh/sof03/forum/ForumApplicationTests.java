package hh.sof03.forum;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.sof03.forum.web.AppUserController;
import hh.sof03.forum.web.CategoryController;
import hh.sof03.forum.web.MessageController;
import hh.sof03.forum.web.TopicController;

@SpringBootTest
class ForumApplicationTests {

	@Autowired
	private AppUserController userController;

	@Autowired
	private CategoryController categoryController;

	@Autowired
	private MessageController messageController;

	@Autowired
	private TopicController topicController;

	@Test
	void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
		assertThat(categoryController).isNotNull();
		assertThat(messageController).isNotNull();
		assertThat(topicController).isNotNull();
	}

}
