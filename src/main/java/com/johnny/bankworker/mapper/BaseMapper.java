package com.johnny.bankworker.mapper;

import java.util.List;

public interface BaseMapper<T> {
    int searchTotalCount();

    List<T> searchList(int startIndex, int pageSize);

    T searchByID(int id);

    T searchByName(String name);

    int insert(T entity);

    int update(T entity);

    int delete(T entity);

    int recovery(T entity);
}
