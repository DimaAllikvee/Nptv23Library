package org.example.model;

import java.util.UUID;

public class Author {
    private UUID id;
    private String authorname;
    private String authorSurname;

    public Author() {
        this.id = UUID.randomUUID();
    }

    public Author(String authorSurname, String authorname) {
        this.id = UUID.randomUUID();
        this.authorSurname = authorSurname;
        this.authorname = authorname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;
        return id.equals(author.id) && authorname.equals(author.authorname) && authorSurname.equals(author.authorSurname);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + authorname.hashCode();
        result = 31 * result + authorSurname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorname='" + authorname + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                '}';
    }
}
