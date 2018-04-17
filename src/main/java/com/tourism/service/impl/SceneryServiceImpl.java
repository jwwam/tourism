package com.tourism.service.impl;

import com.tourism.dao.SceneryDao;
import com.tourism.entity.Scenery;
import com.tourism.service.SceneryService;
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

@Service("sceneryService")
public class SceneryServiceImpl implements SceneryService{

    @Value("#{configProperties['icb.uploadPath']}")
    private String uploadPath;

    @Value("#{configProperties['icb.tempPath']}")
    private String tempPath;

    @Value("#{configProperties['icb.uploadFilsPath']}")
    private String uploadFilsPath;

    @Autowired
    SceneryDao sceneryDao;

    public Scenery findById(String id) {
        return sceneryDao.findById(id);
    }

    public Page<Scenery> findAll(Pageable pageable) {
        return sceneryDao.findAll(pageable);
    }

    public String[] save(Scenery s) {
        return new String[0];
    }

    public void delete(Scenery s) {
        sceneryDao.delete(s);
    }

    public int upload(HttpServletRequest request, HttpServletResponse response) {
        String fileUrl = "";
        String fileUrls = "";
        String id = "";
        String path = System.getProperty("catalina.home")+"\\webapps\\images\\";
        try{
            Scenery cf = new Scenery();
            System.out.println("upload");
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();
            // threshold 极限、临界值，即硬盘缓存 1M
            diskFactory.setSizeThreshold(8 * 1024);
            // repository 贮藏室，即临时文件目录
            File tempFile =  new File(tempPath);
            if (!tempFile.exists() && !tempFile.isDirectory()) {
                tempFile.mkdirs();
            }
            diskFactory.setRepository(tempFile);

            ServletFileUpload upload = new ServletFileUpload(diskFactory);
            // 设置允许上传的最大文件大小 4M
            upload.setSizeMax(15 * 1024 * 1024);
            // 解析HTTP请求消息头
            List<FileItem> itemList=upload.parseRequest(request);
            System.out.println(itemList.size());
            //String uploadPathTemp = uploadPath +"\\"+ DateUtils.yyyyMMdd(new Date());
            String uploadPathTemp = path;
            File file1 =  new File(uploadPathTemp);
            if( !file1 .isDirectory()){
                file1.mkdirs();
            }
            for(FileItem item:itemList){
                //判断输入的类型是 普通输入项 还是文件
                if(item.isFormField()){
                    //普通输入项 ,得到input中的name属性的值
                    String name=item.getFieldName();
                    //得到输入项中的值
                    String value=item.getString("UTF-8");
                    id = value;
                    System.out.println("name="+name+"  value="+value);

                    if("title".equals(name)){
                        cf.setTitle(value);
                    }else if("detail".equals(name)){
                        cf.setDetail(value);
                    }else if("price".equals(name)){
                        cf.setPrice(value);
                    }else if("day".equals(name)){
                        cf.setDay(value);
                    }else if("address".equals(name)){
                        cf.setAddress(value);
                    }else if("star".equals(name)){
                        cf.setStar(value);
                    }else{
                        System.out.println("多余提交选项!");
                    }
                }else{
                    String strName=item.getName();
                    if(StringUtils.isNotEmpty(strName)){
                        String fileName = UUID.randomUUID()+strName.substring(strName.lastIndexOf("."));
                        InputStream is=item.getInputStream();
                        File file2 =  new File(file1,fileName);
                        fileUrl = uploadPathTemp+"\\"+fileName;
                        if(StringUtils.isNotEmpty(fileUrl)){
                            fileUrls+=fileUrl+",";
                        }
                        FileOutputStream fos=new FileOutputStream(file2);
                        byte[] buff = new byte[1024];
                        int len = 0;
                        while((len = is.read(buff))>0){
                            fos.write(buff);
                        }
                        is.close();
                        fos.close();
                        //cf.setImg(fileUrl);
                        cf.setImg(fileName);
                    }
                }
            }
            //@Query("update RecordFile set status=0,fileUrl=?1,createDate=?2,operateDate=?3,creuser=?4 where id = ?5")
            //return  contentFlowDao.updateFileURL(cf,new Date(),new Date());
            String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
            uuid = uuid.replace("-", "");
            cf.setId(uuid);
            Scenery cfResult = sceneryDao.save(cf);
            if(cfResult == null ){
                return -1;
            }else{
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
