package com.tourism.service;

import com.tourism.entity.ContentFlow;
import com.tourism.entity.TopFlow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TopFlowService {


    List<TopFlow> getAll();

    String[] save(TopFlow label);

    void delete(TopFlow label);

    Page<TopFlow> findByLabelName(String labelName, Pageable pageable);

    Page<TopFlow> findAll(Pageable pageable);

    TopFlow findById(String id);

}
