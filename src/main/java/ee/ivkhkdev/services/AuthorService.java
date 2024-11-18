package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.interfaces.FileRepository;

import java.util.List;

public class AuthorService implements Service<Author> {

    private final AppHelper<Author> authorAppHelper;
    private final String fileName = "authors";
    private final FileRepository<Author> storage;

    public AuthorService(AppHelper<Author> authorAppHelper, FileRepository<Author> storageAuthor) {
        this.authorAppHelper = authorAppHelper;
        this.storage = storageAuthor;
    }

    @Override
    public boolean add() {
        try {
            Author author = authorAppHelper.create();
            if(author == null) {return false;}
            storage.save(author,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit() {
        try {
            List<Author> modifedAuthors = authorAppHelper.update(list());
            if(modifedAuthors == null && modifedAuthors.isEmpty()){
                return false;
            }
            storage.saveAll(modifedAuthors,fileName);
            return true;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean remove(Author entity) {
        return false;
    }

    @Override
    public boolean print() {
        return authorAppHelper.printList(this.list());
    }

    @Override
    public List<Author> list() {
        return storage.load(fileName);
    }
}
