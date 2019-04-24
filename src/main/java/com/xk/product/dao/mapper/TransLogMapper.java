package com.xk.product.dao.mapper;

import com.xk.product.dao.model.TransLog;
import com.xk.product.dao.model.TransLogExample;
import com.xk.product.dao.model.TransLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransLogMapper {
    int countByExample(TransLogExample example);

    int deleteByExample(TransLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransLogWithBLOBs record);

    int insertSelective(TransLogWithBLOBs record);

    List<TransLogWithBLOBs> selectByExampleWithBLOBs(TransLogExample example);

    List<TransLog> selectByExample(TransLogExample example);

    TransLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransLogWithBLOBs record, @Param("example") TransLogExample example);

    int updateByExampleWithBLOBs(@Param("record") TransLogWithBLOBs record, @Param("example") TransLogExample example);

    int updateByExample(@Param("record") TransLog record, @Param("example") TransLogExample example);

    int updateByPrimaryKeySelective(TransLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TransLogWithBLOBs record);

    int updateByPrimaryKey(TransLog record);
}