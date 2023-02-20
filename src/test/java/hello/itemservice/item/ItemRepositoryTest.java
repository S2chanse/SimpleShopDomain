package hello.itemservice.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();
    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }
    @Test
    void save() {
        Item item = new Item("itemA",10000,10);
        Item savedItem = itemRepository.save(item);
        Assertions.assertThat(itemRepository.findById(savedItem.getId())).isEqualTo(savedItem);

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA",10000,10);
        Item itemB = new Item("itemB",20000,5);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        List<Item> itemList = itemRepository.findAll();
        Assertions.assertThat(itemList.size()).isEqualTo(2);
        Assertions.assertThat(itemList).contains(itemA,itemB);
    }

    @Test
    void update() {
        Item item = new Item("itemA",10000,10);
        itemRepository.save(item);
        Item savedItem = itemRepository.save(item);

        Item updateItem = new Item("item1",20000,10);
        itemRepository.update(savedItem.getId(), updateItem);

        Item updatedItem = itemRepository.findById(savedItem.getId());

        Assertions.assertThat(updatedItem.getItemName()).isEqualTo(updateItem.getItemName());
        Assertions.assertThat(updatedItem.getQuantity()).isEqualTo(updateItem.getQuantity());
        Assertions.assertThat(updatedItem.getPrice()).isEqualTo(updateItem.getPrice());
    }
}