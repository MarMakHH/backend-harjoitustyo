package hh.sof03.forum;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.forum.domain.AppUser;
import hh.sof03.forum.domain.AppUserRepository;
import hh.sof03.forum.domain.Category;
import hh.sof03.forum.domain.CategoryRepository;
import hh.sof03.forum.domain.Message;
import hh.sof03.forum.domain.MessageRepository;
import hh.sof03.forum.domain.Topic;
import hh.sof03.forum.domain.TopicRepository;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository categoryrepo, TopicRepository topicrepo , MessageRepository messagerepo , AppUserRepository userRepo) {
		return (args) -> {
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"ADMIN");
			userRepo.save(user1);
			userRepo.save(user2);

            Category c1 = new Category("General");
            Category c2 = new Category("Elokuvat");
            Category c3 = new Category("Pelit");
			categoryrepo.save(c1);
			categoryrepo.save(c2);
			categoryrepo.save(c3);

            Topic t1 = new Topic("Säännöt", c1);
            Topic t2 = new Topic("Tervehdys!", c1);
            topicrepo.save(t1);
            topicrepo.save(t2);

            Message m1 = new Message("Ei sääntöjä tällä hetkellä", user1, t1);
            Message m2 = new Message("Kiitos tiedosta", user2, t1);
            messagerepo.save(m1);
            messagerepo.save(m2);


		};
	}

}
