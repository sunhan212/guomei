package com.youlishu.service.impl;

import com.youlishu.bean.BeamTransformInfo;
import com.youlishu.bean.UserLog;
import com.youlishu.dao.BeamUpdateMapper;
import com.youlishu.dao.UserLogMapper;
import com.youlishu.service.BeamUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BeamUpdateServiceImpl implements BeamUpdateService {
    @Autowired
    private UserLogMapper userLogMapper;
    @Autowired
    private BeamUpdateMapper beamUpdateMapper;

    //本机地址
    @Value("${Windows.beaminpicpath}")
    private String beaminpicpath;

    @Value("${Windows.beamintxtpath}")
    private String beamintxtpath;

    @Value("${Windows.wallinpicpath}")
    private String wallinpicpath;

    @Value("${Windows.beamoutpath}")
    private String beamoutpath;
    //公网地址
    @Value("${Windows.beaminpicpathurl}")
    private String beaminpicpathurl;

    @Value("${Windows.beamintxtpathurl}")
    private String beamintxtpathurl;

    @Value("${Windows.wallinpicpathurl}")
    private String wallinpicpathurl;

    @Value("${Windows.beamoutpathurl}")
    private String beamoutpathurl;

    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
@Override
    public BeamTransformInfo findOneBeam(Integer id, String username) {
        return beamUpdateMapper.findOneBeam(id,username);
    }
@Override
    public boolean updateBeam(MultipartFile file1, MultipartFile file2, String prjName, Integer id,
                              String beamDesignType, String beamLong, String beamUp, String beamLow, String username) {
        //根据用户名和id先去查询数据
        BeamTransformInfo beamTransformInfo = beamUpdateMapper.findOneBeam(id, username);
        //删除服务器上的剪力墙上传地址和参数TXT文件地址
        boolean delete_flag = false;
        try {
            File files = new File(beamTransformInfo.getBeamInPngUrl(), beamTransformInfo.getPngFileName());
            if (!files.exists() && !files.isFile() && !files.delete()) {
                delete_flag = true;
            } else {
                delete_flag = false;
            }
            if (delete_flag == true) {
                //修改数据库信息
                //建筑空间照片文件名称
                String fileName1 = file1.getOriginalFilename();
                //剪力墙照片文件名称
                String fileName2 = file2.getOriginalFilename();

                fileName1 = getFileName(fileName1);
                fileName2 = getFileName(fileName2);
                if (!file1.isEmpty() && !file2.isEmpty()) {

                    try (BufferedOutputStream out1 = new BufferedOutputStream(
                            new FileOutputStream((beaminpicpathurl + File.separator + fileName1)));
                         BufferedOutputStream out2 = new BufferedOutputStream(
                                 new FileOutputStream((wallinpicpathurl + File.separator + fileName2)))) {
                        out1.write(file1.getBytes());
                        out2.write(file2.getBytes());
                        out1.flush();
                        out2.flush();
                        out1.close();
                        out2.close();
                        //生成txt参数文件
                        //TODO
                        String txt = beamDesignType + "," + beamLong + "," + beamUp + "," + beamLow;
                        String txtFileName = fileName1.replace(".png", ".txt");
                        File txtfile = new File(beamintxtpathurl + File.separator + txtFileName);
                        FileWriter fw = new FileWriter(txtfile);
                        fw.write(txt);
                        fw.close();
                        //数据库添加信息
                        //输出上传参数TXT文件地址
                        String beamInTxtPath = beamintxtpathurl + fileName1.replace(".png", ".txt");
                        //输出上传建筑空间照片地址
                        String outBeamPngPath = beaminpicpathurl + File.separator + fileName1;
                        //输出上传剪力墙照片地址
                        String outWallPngPath = wallinpicpathurl + File.separator + fileName2;
                        BeamTransformInfo beamTransformInfo1 = new BeamTransformInfo();
                        beamTransformInfo1.setBeamUploadTime(date);
                        beamTransformInfo1.setId(id);
                        beamTransformInfo1.setPrjName(prjName);
                        beamTransformInfo1.setWallUploadTime(date);
                        beamTransformInfo1.setWallOutPngUrl(outWallPngPath);
                        beamTransformInfo1.setBeamInPngUrl(outBeamPngPath);
                        beamTransformInfo1.setBeamInTxtUrl(beamInTxtPath);
                        beamTransformInfo1.setTxtFileName(txtFileName);
                        beamTransformInfo1.setPngFileName(fileName1);
                        beamTransformInfo1.setUserName(username);
                        beamTransformInfo1.setBeamDesignType(beamDesignType);
                        beamTransformInfo1.setBeamLong(beamLong);
                        beamTransformInfo1.setBeamLow(beamLow);
                        beamTransformInfo1.setBeamUp(beamUp);
                        beamUpdateMapper.updateBeam(beamTransformInfo1);
                        //日志
                        UserLog userLog = new UserLog();
                        userLog.setUsername(username);
                        userLog.setTime(date);
                        userLog.setContent("用户：" + username + "在" + dateFormat.format(date) + "进行梁-板构建数据上传");
                        userLogMapper.insert(userLog);
                        return true;
                    }catch (Exception e){
                        e.printStackTrace();
                        return false;
                    }
                }
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
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
