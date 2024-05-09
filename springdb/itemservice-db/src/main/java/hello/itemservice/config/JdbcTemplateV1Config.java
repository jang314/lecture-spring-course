package hello.itemservice.config;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.JdbcTemplateItemRepositoryV1;
import hello.itemservice.repository.memory.MemoryItemRepository;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV1Config {
    private final DataSource dataSource;

    @Bean
    public ItemService itemServiceV1() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public JdbcTemplateItemRepositoryV1 itemRepository() {
        return new JdbcTemplateItemRepositoryV1(dataSource);
    }


}
