package hello.itemservice.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {
    //static이 아닌 경우, 새롭게 new 할 때마다, store가 생성된다.
    //HashMap을 동시에 멀티쓰레드로 사용해야하는 경우, HashMap이 아닌, ConcurrentHashMap을 사용해야한다.

    private static final Map<Long,Item> store = new ConcurrentHashMap<>();//static
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long id,Item updateParam){
        Item findItem = findById(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
