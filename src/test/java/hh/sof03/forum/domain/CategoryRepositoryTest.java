package hh.sof03.forum.domain;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Test
    public void findbyHeaderShouldReturnCategory() {
        List<Category> categories = repo.findByHeader("General");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getHeader()).isEqualTo("General");
    }

    @Test
    public void createNewCategory() {
        Category category = new Category("testi");
        repo.save(category);
        assertThat(category.getCategoryid()).isNotNull();
    }
}
