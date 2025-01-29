package model;

import java.util.ArrayList;
import java.util.List;

public class UserBasket {
    private List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}
