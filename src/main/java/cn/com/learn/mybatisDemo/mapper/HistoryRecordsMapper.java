package cn.com.learn.mybatisDemo.mapper;

import cn.com.learn.mybatisDemo.vo.HistoryRecords;
import java.util.List;

public interface HistoryRecordsMapper {
    int insert(HistoryRecords record);

    List<HistoryRecords> selectAll();
}