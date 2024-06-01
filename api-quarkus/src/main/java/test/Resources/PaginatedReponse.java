package test.Resources;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public record PaginatedReponse<E>(List<E> data, int page, int totalPages){

    public PaginatedReponse(PanacheQuery<E> query){
        this(query.list(), query.page().index +1, query.pageCount());
    }
}

