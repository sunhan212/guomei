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
//    @Value("${Windows.beamintxtpath}")
//    private String beamintxtpath;
//
//    @Value("${Windows.beamoutpath}")
//    private String beamoutpath;

//    @Value("${Windows.beamoutpicpath}")
//    private String beamoutpicpath;
//
//    @Value("${Windows.beamouttxtpath}")
//    private String beamouttxtpath;

    @Value("${Linux.beaminpicpath}")
    private String beaminpicpath;

    @Value("${Linux.beamintxtpath}")
    private String beamintxtpath;

    @Value("${Linux.beamoutpath}")
    private String beamoutpath;

    @Value("${Linux.beamoutpicpath}")
    private String beamoutpicpath;

    @Value("${Linux.beamouttxtpath}")
    private String beamouttxtpath;

    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public int transformBeam(MultipartFile file, String username, String beamDesignType, String beamLong, String beamUp, String beamLow, String prjname) {
        String fileName = file.getOriginalFilename();
        fileName = getFileName(fileName);
        try {
            if (!file.isEmpty()) {
                //上传图片 我也不知道这个try为啥这样写 但是能跑别改
                try (BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream((beamoutpicpath + File.separator + fileName))))
                {
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    //生成txt参数文件
                    //txt文件内坐标如何来的？？？
                    //TODO
                    String txt = beamDesignType+", "+beamLong+", "+beamUp+","+beamLow;
                    String txtFileName = fileName.replace(".png",".txt");
                    File txtfile = new File(beamouttxtpath + File.separator + txtFileName);
                    FileWriter fw = new FileWriter(txtfile);
                    fw.write(txt);
                    fw.close();
                    //数据库添加信息
                    String outPngPath = beamoutpicpath + File.separator + fileName;
                    String outTxtPath = beamouttxtpath + File.separator + txtFileName;
                    BeamTransformInfo beamTransformInfo = new BeamTransformInfo();
                    beamTransformInfo.setWallUploadTime(date);
                    beamTransformInfo.setWallOutPngUrl(outPngPath);
                    beamTransformInfo.setWallOutTxtUrl(outTxtPath);
                    beamUploadMapper.insertBeamOfWall(beamTransformInfo);
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

    public int uploadWall(MultipartFile file, String username) {
        String fileName = file.getOriginalFilename();
        fileName = getFileName(fileName);
        try {
            if (!file.isEmpty()) {
                //上传图片 我也不知道这个try为啥这样写 但是能跑别改
                try (BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream((beamoutpath + File.separator + fileName))))
                {
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    //生成txt参数文件
                    //txt文件内坐标如何来的？？？
                    //TODO
                    //String txt = beamDesignType+", "+beamLong+", "+beamUp+","+beamLow;
                    String txtFileName = fileName.replace(".png",".txt");
                    /*File txtfile = new File(beamintxtpath + File.separator + txtFileName);
                    FileWriter fw = new FileWriter(txtfile);
                    fw.write(txt);
                    fw.close();*/
                    //数据库添加信息
                    String outPngPath = beamoutpicpath + File.separator + fileName;
                    String outTxtPath = beamouttxtpath + File.separator + txtFileName;
                    BeamTransformInfo beamTransformInfo = new BeamTransformInfo();
                    beamTransformInfo.setWallUploadTime(date);
                    beamTransformInfo.setWallOutPngUrl(outPngPath);
                    beamTransformInfo.setWallOutTxtUrl(outTxtPath);
                    beamUploadMapper.insertSelective(beamTransformInfo);
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
