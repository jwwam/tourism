package com.tourism.dao;

import com.tourism.entity.ContentFlow;
import com.tourism.entity.TopFlow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface TopFlowDao extends PagingAndSortingRepository<TopFlow, Long>,JpaSpecificationExecutor<TopFlow>,JpaRepository<TopFlow,Long> {

    @Query("from TopFlow cf where cf.title = ?1")
    Page<TopFlow> findByLabelName(String labelName, Pageable pageable);

    @Query("from TopFlow cf where cf.id = ?1")
    TopFlow findById(String id);

    @Modifying
    @Query("UPDATE TopFlow SET title=?1,detail=?2 WHERE id = ?3")
    int updateTF(String tfName, String tfDes, String id);

/*    @Modifying
    @Query("update RecordFile set status=0,fileUrl=?1,createDate=?2,operateDate=?3,creuser=?4 where id = ?5")
    int updateFileURL( ,Date createDate, Date operateDate);*/

}
