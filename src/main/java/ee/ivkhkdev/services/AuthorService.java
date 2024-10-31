package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.interfaces.FileRepository;

import java.util.List;

public class AuthorService implements Service<Author>, FileRepository<Author> {

    private final AppHelper<Author> appHelperAuthor;
    private final String fileName = "authors";

    public AuthorService(AppHelper<Author>  appHelperAuthor) {
        this.appHelperAuthor = appHelperAuthor;
    }

    @Override
    public boolean add() {
        try {
            Author author = appHelperAuthor.create();
            if(author == null) {return false;}
            save(author,fileName);
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
    public void print() {
        appHelperAuthor.printList(this.list());
    }

    @Override
    public List<Author> list() {
        return load(fileName);
    }
}
