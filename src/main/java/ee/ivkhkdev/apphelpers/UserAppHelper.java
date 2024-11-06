package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;

import java.util.List;

public class UserAppHelper implements AppHelper<User>, Input {
    @Override
    public User create() {
        try {
            User user = new User();
            System.out.print("Имя автора: ");
            user.setFirstname(getString());
            System.out.println("Фамилия автора: ");
            user.setLastname(getString());
            return user;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean printList(List<User> users) {
        System.out.println("---------- Список читателей --------");
        for(int i=0;i<users.size();i++) {
            User user = users.get(i);
            System.out.printf("%d. %s %s. %s%n", i+1,user.getFirstname(),user.getLastname(), user.getPhone());
        }
        return false;
    }
}
