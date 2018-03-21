package com.tourism.service.impl;

import com.tourism.dao.DesktopDao;
import com.tourism.entity.Desktop;
import com.tourism.service.DesktopService;
import com.tourism.utils.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service("desktopService")
public class DesktopServiceImpl implements DesktopService {

    @Value("#{configProperties['icb.uploadPath']}")
    private String uploadPath;

    @Value("#{configProperties['icb.tempPath']}")
    private String tempPath;

    @Value("#{configProperties['icb.uploadFilsPath']}")
    private String uploadFilsPath;

    @Autowired
    DesktopDao desktopDao;

    public List<Desktop> getAll() {
        return desktopDao.findAll();
    }

    public String[] save(Desktop desktop) {
        String [] array = new String[2];
        System.out.println("aaaaaaaa:"+desktop.getId());
        if( desktop.getId() == null
                || desktop.getId().equals("")){
            String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
            uuid = uuid.replace("-", "");
            desktop.setId(uuid);
            array[1] = uuid;
            Desktop desktopResult = desktopDao.save(desktop);
            if(desktopResult == null ){
                array[0] = "false";
            }else{
                array[0] = "true";
            }
        }else{
            int  result = desktopDao.updateDesktop(desktop.getEmail(), desktop.getIphone(), desktop.getId());
            if(result < 0) {
                array[0] = "false";
            }else{
                array[0] = "true";
            }
            array[1] = desktop.getId();
        }

        return array;
    }

    public void delete(Desktop desktop) {
        desktopDao.delete(desktop);
    }

    public int upload(HttpServletRequest request, HttpServletResponse response, String registID) {
        String fileUrl = "";
        String fileUrls = "";
        String id = "";
        String temp = "";
        /*String path = request.getSession().getServletContext().getRealPath("/");
        String path2 = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");*/
        try{
            List<Desktop> desktopList = desktopDao.findAll();
            if(desktopList!=null||desktopList.size()!=0){
                temp =desktopList.get(0).getPic();
                desktopDao.delete(desktopList.get(0));
            }
            Desktop cf = new Desktop();
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
            String uploadPathTemp = uploadFilsPath;
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

                    if("email".equals(name)){
                        cf.setEmail(value);
                    }else if("iphone".equals(name)) {
                        cf.setIphone(value);
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
                        cf.setPic(fileName);
                    }else{
                        cf.setPic(temp);
                    }
                }
            }
            //@Query("update RecordFile set status=0,fileUrl=?1,createDate=?2,operateDate=?3,creuser=?4 where id = ?5")
            //return  contentFlowDao.updateFileURL(cf,new Date(),new Date());
            String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
            uuid = uuid.replace("-", "");
            cf.setId(uuid);
            Desktop dtResult = desktopDao.save(cf);
            if(dtResult == null ){
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
