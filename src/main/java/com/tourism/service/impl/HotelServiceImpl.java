package com.tourism.service.impl;

import com.tourism.dao.HotelDao;
import com.tourism.entity.Hotel;
import com.tourism.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("hotelService")
public class HotelServiceImpl implements HotelService {

    @Value("#{configProperties['icb.uploadPath']}")
    private String uploadPath;

    @Value("#{configProperties['icb.tempPath']}")
    private String tempPath;

    @Value("#{configProperties['icb.uploadFilsPath']}")
    private String uploadFilsPath;

    @Autowired
    HotelDao hotelDao;

    public Hotel findById(String id) {
        return hotelDao.findById(id);
    }

    public Page<Hotel> findAll(Pageable pageable) {
        return hotelDao.findAll(pageable);
    }

    public String[] save(Hotel h) {
        return new String[0];
    }

    public void delete(Hotel h) {
        hotelDao.delete(h);
    }

}
