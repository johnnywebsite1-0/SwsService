package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.BankBranchStaffEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankBranchStaffMapper {
    int searchTotalCount(int bankID, int branchID);

    List<BankBranchStaffEntity> searchList(int bankID, int branchID, int startIndex, int pageSize);

    BankBranchStaffEntity searchByID(int bankID, int branchID, int id);

    BankBranchStaffEntity searchByName(int bankID, int branchID, String staffName);

    int insert(BankBranchStaffEntity entity);

    int update(BankBranchStaffEntity entity);

    int delete(BankBranchStaffEntity entity);

    int recovery(BankBranchStaffEntity entity);
}
