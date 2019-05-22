package com.tourism.dao;

import com.tourism.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
@Transactional
public interface FoodDao extends PagingAndSortingRepository<Food, Long>,JpaSpecificationExecutor<Food>,JpaRepository<Food,Long> {
    @Query("from Food f where f.id = ?1")
    Food findById(String id);
    @Modifying
    @Query("UPDATE Food SET address=?1, detail=?4, img=?5, price=?6, title=?7 WHERE id = ?9")
    int updateHotel(String address, String detail, String img, String price, String title, String id);

}
