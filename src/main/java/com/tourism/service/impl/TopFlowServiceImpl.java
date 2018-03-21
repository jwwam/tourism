package com.tourism.service.impl;

import com.tourism.dao.ContentFlowDao;
import com.tourism.dao.TopFlowDao;
import com.tourism.entity.TopFlow;
import com.tourism.service.ContentFlowService;
import com.tourism.service.TopFlowService;
import com.tourism.utils.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service("topFlowService")
public class TopFlowServiceImpl implements TopFlowService {

    @Autowired
    TopFlowDao topFlowDao;


    public List<TopFlow> getAll() {
        return topFlowDao.findAll();
    }

    public String[] save(TopFlow tf) {
        String [] array = new String[2];
        System.out.println("aaaaaaaa:"+tf.getId());
        if( tf.getId() == null
                || tf.getId().equals("")){
            String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
            uuid = uuid.replace("-", "");
            tf.setId(uuid);
            array[1] = uuid;
            TopFlow userResult = topFlowDao.save(tf);
            if(userResult == null ){
                array[0] = "false";
            }else{
                array[0] = "true";
            }
        }else{
            int  result = topFlowDao.updateTF(tf.getContent(), tf.getTitle(), tf.getId());
            if(result < 0) {
                array[0] = "false";
            }else{
                array[0] = "true";
            }
            array[1] = tf.getId();
        }

        return array;
    }

    public void delete(TopFlow tf) {
        topFlowDao.delete(tf);
    }

    public Page<TopFlow> findByLabelName(String labelName, Pageable pageable) {
        return topFlowDao.findByLabelName(labelName, pageable);
    }

    public Page<TopFlow> findAll(Pageable pageable) {
        return topFlowDao.findAll(pageable);
    }

    public TopFlow findById(String id) {
        return topFlowDao.findById(id);
    }

}
