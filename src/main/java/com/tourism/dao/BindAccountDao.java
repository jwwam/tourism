package com.tourism.dao;

import com.tourism.entity.BindAccount;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ppm on 2017/7/21.
 */
@Transactional
public interface BindAccountDao extends PagingAndSortingRepository<BindAccount, Long>{
    BindAccount findByOpenId(String openId);

}
