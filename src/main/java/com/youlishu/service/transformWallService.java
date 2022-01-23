package com.youlishu.service;

import com.youlishu.bean.UserLog;
import com.youlishu.bean.WallTransformInfo;
import com.youlishu.dao.UserLogMapper;
import com.youlishu.dao.WallTransformInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author yujin
 * @Date 2022/1/19 18:58
 * @Version 1.0
 */
@Service
public class transformWallService {

    @Autowired
    private UserLogMapper userLogMapper;
    @Autowired
    private WallTransformInfoMapper wallTransformInfoMapper;

//    @Value("${Windows.inphotopath}")
//    private String inphotopath;
//
//    @Value("${Windows.intxtpath}")
//    private String intxtpath;
//
//    @Value("${Windows.outpath}")
//    private String outpath;

    @Value("${Linux.inphotopath}")
    private String inphotopath;

    @Value("${Linux.intxtpath}")
    private String intxtpath;

    @Value("${Linux.outpath}")
    private String outpath;



    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 上传需要转换的图片及参数
     * @param
     */
    public int transformwall(MultipartFile file, String username,String seismic,String structure, String scale,String prjname) {
        String fileName = file.getOriginalFilename();
        fileName = getFileName(fileName);
        try {
            if (!file.isEmpty()) {
                //上传图片 我也不知道这个try为啥这样写 但是能跑别改
                try (BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream((inphotopath + File.separator + fileName))))
                {
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    //生成txt参数文件
                    String txt = seismic+", "+structure+", "+scale;
                    String txtFileName = fileName.replace(".png",".txt");
                    File txtfile = new File(intxtpath + File.separator + txtFileName);
                    FileWriter fw = new FileWriter(txtfile);
                    fw.write(txt);
                    fw.close();
                    //数据库添加信息
                    String inPngPath = inphotopath + File.separator + fileName;
                    String inTxtPath = intxtpath + File.separator + txtFileName;
                    WallTransformInfo wallTransformInfo = new WallTransformInfo();
                    wallTransformInfo.setUploadTime(date);
                    wallTransformInfo.setUserName(username);
                    wallTransformInfo.setInPngUrl(inPngPath);
                    wallTransformInfo.setInTxtUrl(inTxtPath);
                    wallTransformInfo.setTxtFileName(txtFileName);
                    wallTransformInfo.setPngFileName(fileName);
                    wallTransformInfo.setPrjName(prjname);
                    wallTransformInfoMapper.insertSelective(wallTransformInfo);
                    //日志
                    UserLog userLog = new UserLog();
                    userLog.setUsername(username);
                    userLog.setTime(date);
                    userLog.setContent("用户："+username+"在"+dateFormat.format(date)+"进行剪力墙数据上传");
                    userLogMapper.insert(userLog);

                    return 1;
                } catch (FileNotFoundException e) {
                    return 0;
                } catch (IOException e) {
                    return 0;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;

    }

    /**
     * 上传转换后的信息
     * @param
     */
    public int uploadTransformInfo(String prjname,String username) {
        try {

            WallTransformInfo info = wallTransformInfoMapper.getinfo(username,prjname);

            String outpngurl = outpath+File.separator+info.getPngFileName();

            String deouttxturl = outpath+File.separator+info.getTxtFileName();
            String outtxturl = deouttxturl.replace(".txt",".png.txt");
            WallTransformInfo transinfo = new WallTransformInfo();
            transinfo.setOutPngUrl(outpngurl);
            transinfo.setOutTxtUrl(outtxturl);
            transinfo.setTransformTime(date);
            transinfo.setUserName(username);
            transinfo.setPrjName(prjname);
            wallTransformInfoMapper.update(transinfo);

        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
     return 1;
    }

    /**
     * 文件名后缀前添加一个时间戳
     */
    private String getFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyymmddHHmmss");  //设置时间格式
        String nowTimeStr = sDateFormate.format(new Date()); // 当前时间
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
        return fileName;
    }
}
