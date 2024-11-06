package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.storage.Storage;

import java.util.List;

public class AuthorService implements Service<Author> {

    private final AppHelper<Author> appHelperAuthor;
    private final String fileName = "authors";
    private final FileRepository<Author> storage;

    public AuthorService(AppHelper<Author>  appHelperAuthor, FileRepository<Author> storageAuthor) {
        this.appHelperAuthor = appHelperAuthor;
        this.storage = storageAuthor;
    }

    @Override
    public boolean add() {
        try {
            Author author = appHelperAuthor.create();
            if(author == null) {return false;}
            storage.save(author,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit(Author entity) {
        return false;
    }

    @Override
    public boolean remove(Author entity) {
        return false;
    }

    @Override
    public boolean print() {

        return appHelperAuthor.printList(this.list());
    }

    @Override
    public List<Author> list() {
        return storage.load(fileName);
    }
}
