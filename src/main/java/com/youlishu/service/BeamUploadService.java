package com.youlishu.service;


import com.youlishu.bean.BeamTransformInfo;
import com.youlishu.bean.UserLog;
import com.youlishu.dao.BeamUploadMapper;
import com.youlishu.dao.UserLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BeamUploadService {

    @Autowired
    private BeamUploadMapper beamUploadMapper;

    @Autowired
    private UserLogMapper userLogMapper;

//    @Value("${Windows.beaminpicpath}")
//    private String beaminpicpath;
//
//    @Value("${Windows.wallinpicpath}")
//    private String wallinpicpath;
//
//    @Value("${Windows.beamoutpicpath}")
//    private String beamoutpicpath;
//
//    @Value("${Windows.beamouttxtpath}")
//    private String beamouttxtpath;

    @Value("${Linux.beaminpicpath}")
    private String beaminpicpath;
    @Value("${Linux.beaminpicpathurl}")
    private String beaminpicpathurl;

    @Value("${Linux.beamintxtpath}")
    private String beamintxtpath;
    @Value("${Linux.beamintxtpathurl}")
    private String beamintxtpathurl;

    @Value("${Linux.wallinpicpath}")
    private String wallinpicpath;
    @Value("${Linux.wallinpicpathurl}")
    private String wallinpicpathurl;

    @Value("${Linux.beamoutpath}")
    private String beamoutpath;
    @Value("${Linux.beamoutpathurl}")
    private String beamoutpathurl;


    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public int transformBeam(MultipartFile file1,MultipartFile file2,String prjName, String username, String beamDesignType, String beamLong, String beamUp, String beamLow) {
        //建筑空间照片文件名称
        String fileName1 = file1.getOriginalFilename();
        //剪力墙照片文件名称
        String fileName2 = file2.getOriginalFilename();

        fileName1 = getFileName(fileName1);
        fileName2 = getFileName(fileName2);
        try {
            if (!file1.isEmpty() && !file2.isEmpty()) {

                try (BufferedOutputStream out1 = new BufferedOutputStream(
                        new FileOutputStream((beaminpicpath + File.separator + fileName1)));
                     BufferedOutputStream out2 = new BufferedOutputStream(
                        new FileOutputStream((wallinpicpath + File.separator + fileName2))))
                {
                    out1.write(file1.getBytes());
                    out2.write(file2.getBytes());
                    out1.flush();
                    out2.flush();
                    out1.close();
                    out2.close();
                    //生成txt参数文件
                    //TODO
                    String txt = beamDesignType+", "+beamLong+", "+beamUp+", "+beamLow;
                    String txtFileName = fileName1.replace(".png",".png.txt");
                    File txtfile = new File(beamintxtpath + File.separator + txtFileName);
                    FileWriter fw = new FileWriter(txtfile);
                    fw.write(txt);
                    fw.close();
                    //数据库添加信息
                    String beamInTxtPath = beamintxtpathurl + fileName1.replace(".png",".txt");
                    String outBeamPngPath = beaminpicpathurl + File.separator + fileName1;
                    String outWallPngPath = wallinpicpathurl + File.separator + fileName2;
                    BeamTransformInfo beamTransformInfo = new BeamTransformInfo();
                    beamTransformInfo.setBeamUploadTime(date);
                    beamTransformInfo.setPrjName(prjName);
                    beamTransformInfo.setWallUploadTime(date);
                    beamTransformInfo.setWallOutPngUrl(outWallPngPath);
                    beamTransformInfo.setBeamInPngUrl(outBeamPngPath);
                    beamTransformInfo.setBeamInTxtUrl(beamInTxtPath);
                    beamTransformInfo.setTxtFileName(txtFileName);
                    beamTransformInfo.setPngFileName(fileName1);
                    beamTransformInfo.setUserName(username);
                    beamUploadMapper.insertBeamAndWall(beamTransformInfo);
                    //日志
                    UserLog userLog = new UserLog();
                    userLog.setUsername(username);
                    userLog.setTime(date);
                    userLog.setContent("用户："+username+"在"+dateFormat.format(date)+"进行梁-板构建数据上传");
                    userLogMapper.insert(userLog);

                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
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
     * 文件名后缀前添加一个时间戳
     */
    private String getFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyymmddHHmmss");  //设置时间格式
        String nowTimeStr = sDateFormate.format(new Date()); // 当前时间
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
        return fileName;
    }

    /**
     * 上传剪力墙照片
     */
    public int uploadWall(MultipartFile file1, String username) {
        //剪力墙照片文件名称
        String fileName1 = file1.getOriginalFilename();
        fileName1 = getFileName(fileName1);
        try {
            if (!file1.isEmpty()) {
                //上传图片 我也不知道这个try为啥这样写 但是能跑别改
                try (BufferedOutputStream out1 = new BufferedOutputStream(
                        new FileOutputStream((wallinpicpathurl + File.separator + fileName1))))
                {
                    out1.write(file1.getBytes());
                    out1.flush();
                    out1.close();
                    //生成txt参数文件
                    //txt文件内坐标如何来的？？？
                    //TODO
//                    String txt = beamDesignType+", "+beamLong+", "+beamUp+","+beamLow;
//                    String txtFileName = fileName1.replace(".png",".txt");
//                    File txtfile = new File(beamouttxtpath + File.separator + txtFileName);
//                    FileWriter fw = new FileWriter(txtfile);
//                    fw.write(txt);
//                    fw.close();
                    //数据库添加信息
                    String outWallPngPath = wallinpicpathurl + File.separator + fileName1;
                    BeamTransformInfo beamTransformInfo = new BeamTransformInfo();
//                    beamTransformInfo.setBeamUploadTime(date);
                    beamTransformInfo.setWallUploadTime(date);
                    beamTransformInfo.setWallOutPngUrl(outWallPngPath);
//                    beamTransformInfo.setPngFileName(fileName1);
                    //beamTransformInfo.setWallOutTxtUrl(outTxtPath);
                    beamUploadMapper.insertWall(beamTransformInfo);
                    //日志
                    UserLog userLog = new UserLog();
                    userLog.setUsername(username);
                    userLog.setTime(date);
                    userLog.setContent("用户："+username+"在"+dateFormat.format(date)+"进行梁-板构建数据上传");
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
}
