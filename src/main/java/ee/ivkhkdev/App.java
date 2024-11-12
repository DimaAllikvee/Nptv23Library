package ee.ivkhkdev;


import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.CardService;

public class App implements Input{

    private final Service<Book> bookService;
    private final Service<Author> authorService;
    private final Service<User> userService;
    private final Service<Card> cardService;

    public App(Service<Book> bookService, Service<Author> authorService, Service<User>userService, Service<Card> cardService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.userService = userService;
        this.cardService = cardService;
    }

    public void run() {
        System.out.println("------ Библиотека группы NPTV23 ------");
        System.out.println("--------------------------------------");
        boolean repeat=true;
        do{
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Список книг");
            System.out.println("3. Добавить автора");
            System.out.println("4. Изменить автора");
            System.out.println("5. Добавить читателя");
            System.out.println("6. Выдать книгу");
            System.out.println("7. Вернуть книгу");

            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(getString());
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");

                    if (bookService.add()) {
                        System.out.println("Книга добавлена");
                    } else {
                        System.out.println("Книгу добавить не удалось");
                    }
                    break;
                case 2:
                    System.out.println("----- Список книг -----");
                    bookService.print();
                    break;
                case 3:
                    System.out.println("----- Добавление автора -----");
                    if (authorService.add()) {
                        System.out.println("Автор добавлен");
                    } else {
                        System.out.println("Автора добавить не удалось");
                    }
                    break;
                case 4:
                    System.out.println("----- Измнение автора -----");
                if (authorService.edit()) {
                    System.out.println("Автор добавлен");
                } else {
                    System.out.println("Автора добавить не удалось");
                }
                break;
                case 5:
                    System.out.println("----- Добавление читателя -----");
                    if (userService.add()) {
                        System.out.println("Читатель добавлен");
                    } else {
                        System.out.println("Читателя добавить не удалось");
                    }
                    break;

                case 6:
                        System.out.println("-----------Выдача книгу---------");
                        if (cardService.add()) {
                            System.out.println("Книга выдана");
                        } else {
                            System.out.println("Книгу выдать не удалось");
                        }

                        break;

                case 7:
                    System.out.println("-----------Возрат книги---------");
                    if (((CardService)cardService).returnBook()) {
                        System.out.println("Книга возвращена");
                    } else {
                        System.out.println("Книгу возвратить не удалось");
                    }
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
            System.out.println("--------------------------------------");
        }while(repeat);
        System.out.println("До свидания :)");
    }
}
