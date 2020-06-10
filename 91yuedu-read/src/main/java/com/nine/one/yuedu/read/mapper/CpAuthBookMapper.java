package com.nine.one.yuedu.read.mapper;

import com.nine.one.yuedu.read.entity.CpAuthBook;
import com.nine.one.yuedu.read.entity.vo.CpAuthBookVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.MyMapper;

import java.util.List;

public interface CpAuthBookMapper extends MyMapper<CpAuthBook> {


    List<Integer> getBookIdListByCpAuthId(@Param("cpAuthId") Integer cpAuthId);


    List<CpAuthBookVO> selectBookListByCpAuthId(@Param("cpAuthId") Integer cpAuthId);
}
