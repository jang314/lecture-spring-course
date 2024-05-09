package hello.itemservice.config;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jpa.JpaItemRepositoryV2;
import hello.itemservice.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.repository.jpa.SpringDataJpaItemRepository;
import hello.itemservice.repository.v2.ItemRepositoryV2;
import hello.itemservice.repository.v2.ItempQueryRepositoryV2;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import hello.itemservice.service.ItemServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class V2Config {
    private final SpringDataJpaItemRepository springDataJpaItemRepository;
    private final EntityManager em;
    private final  ItemRepositoryV2 itemRepositoryV2;
    @Bean
    public ItemServiceV2 itemService() {
        return new ItemServiceV2(itemRepositoryV2, itemQueryRepository());
    }


    @Bean
    public ItempQueryRepositoryV2 itemQueryRepository() {
        return new ItempQueryRepositoryV2(em);
    }
    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em);
    }
}
