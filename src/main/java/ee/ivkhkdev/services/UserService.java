package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.User;

import java.util.List;

public class UserService implements Service<User>, Input {
    private final String fileName="users";
    private final AppHelper<User> userAppHelper;
    private final FileRepository<User> storage;

    public UserService(AppHelper<User> userAppHelper, FileRepository<User> storage) {
        this.storage = storage;
        this.userAppHelper = userAppHelper;
    }

    @Override
    public boolean add() {
        User user = userAppHelper.create();
        if(user == null) {return false;}
        storage.save(user,fileName);
        return true;
    }

    @Override
    public boolean edit() {
        try {
            List<User> modifedUsers = userAppHelper.update(list());
            if(modifedUsers == null && modifedUsers.isEmpty()){
                return false;
            }
            storage.saveAll(modifedUsers,fileName);
            return true;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(User entity) {
        return false;
    }

    @Override
    public boolean print() {
        return userAppHelper.printList(storage.load(fileName));
    }

    @Override
    public List<User> list() {
        return storage.load(fileName);
    }
}
