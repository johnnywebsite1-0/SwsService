package com.johnny.bankworker.service;

import com.johnny.bankworker.vo.UnifiedResponse;

public interface BaseService<T, V, E> {
    UnifiedResponse findList(int pageNumber, int pageSize);

    UnifiedResponse findList(int bankID, int branchID, int pageNumber, int pageSize);

    UnifiedResponse find(int id);

    UnifiedResponse find(int bankID, int branchID, int id);

    UnifiedResponse existCheck(String name);

    UnifiedResponse existCheck(int bankID, int branchID, String name);

    UnifiedResponse add(T dto);

    UnifiedResponse change(T dto);

    UnifiedResponse delete(T dto);

    UnifiedResponse recovery(T dto);

    V convertEntityToVo(E entity);

    E convertDtoToEntity(T dto);
}
