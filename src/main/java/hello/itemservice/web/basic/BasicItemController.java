package hello.itemservice.web.basic;

import hello.itemservice.item.Item;
import hello.itemservice.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
    @PostMapping("/add")
    public String addItem(Item item){
        itemRepository.save(item);

        //model.addAttribute("item",item); 자동으로 Item 클래스를 model에 바인딩 해준다.
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
   /** @PostMapping("/add")
    public String addItem(@ModelAttribute("item") Item item,Model model){
        itemRepository.save(item);

        model.addAttribute("item",item);
        return "basic/item";
    }*/
    /**    @PostMapping("/add")
    public String addItem(@RequestParam String itemName,@RequestParam Integer price,@RequestParam Integer quantity,Model model){
        Item item = new Item(itemName,price,quantity);
        itemRepository.save(item);

        model.addAttribute("item",item);
        return "basic/item";
    }*/
    /**
     * 테스트 아이템
     */
    @PostConstruct
    public void init(){
        Item itemA = new Item("itemA",10000,10);
        Item itemB = new Item("itemB",20000,5);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }
}
