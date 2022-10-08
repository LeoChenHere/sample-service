package org.sample.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RawDataRepository extends CrudRepository<RawData, Integer> {
    @Query("select s from RawData s where s.app=?1 and s.dataType=?2")
    List<RawData> findByDataType(String app, String dataType);

}
