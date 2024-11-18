package ee.ivkhkdev.interfaces;

import java.util.List;

public interface AppHelper<T> {
    T create();
    List<T> update(List<T> entities);
    boolean printList(List<T> elements);

}
