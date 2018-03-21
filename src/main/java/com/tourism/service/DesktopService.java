package com.tourism.service;

import com.tourism.entity.Desktop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DesktopService {

    int upload (HttpServletRequest request, HttpServletResponse response, String registID);

    List<Desktop> getAll();

    String[] save(Desktop desktop);

    void delete(Desktop desktop);

}
