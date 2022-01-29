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

    @Value("${Linux.inphotopathurl}")
    private String inphotopathurl;

    @Value("${Linux.intxtpathurl}")
    private String intxtpathurl;

    @Value("${Linux.outpath}")
    private String outpath;



    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 上传需要转换的图片及参数
     * @param
     */
    public String transformwall(MultipartFile file, String username,String seismic,String structure, String scale,String prjname) {
        String fileName = file.getOriginalFilename();
        fileName = getFileName(fileName);
//        StringBuffer sb = new StringBuffer();
        try {
            if (!file.isEmpty()) {
                //上传图片 我也不知道这个try为啥这样写 但是能跑别改
                try (BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream((inphotopath + File.separator + fileName))))
                {
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
//                    sb.append(1);
                    //生成txt参数
                    //拼接文件txt文件里面的内容，规则就是加起来用，隔开
                    String txt = seismic+", "+structure+", "+scale;
                    //给文本改一下png的后缀
                    String txtFileName = fileName.replace(".png",".txt");
                    //生成一个txt文件
                    File txtfile = new File(intxtpath + File.separator + txtFileName);
                    //写入拼接好的文本内容
                    FileWriter fw = new FileWriter(txtfile);
                    fw.write(txt);
                    fw.close();
//                    sb.append(2);
                    //数据库添加信息
                    String inPngPath = inphotopathurl + fileName;
//                    sb.append(3);
                    String inTxtPath = intxtpathurl + fileName.replace(".png",".txt");
//                    sb.append(4);
                    WallTransformInfo wallTransformInfo = new WallTransformInfo();
                    wallTransformInfo.setUploadTime(date);
                    wallTransformInfo.setUserName(username);
                    wallTransformInfo.setInPngUrl(inPngPath);
                    wallTransformInfo.setInTxtUrl(inTxtPath);
                    wallTransformInfo.setTxtFileName(fileName+".txt");
                    wallTransformInfo.setPngFileName(fileName);
                    wallTransformInfo.setPrjName(prjname);
//                    sb.append(5);
                    wallTransformInfoMapper.insertSelective(wallTransformInfo);
//                    sb.append(6);
                    //日志
                    UserLog userLog = new UserLog();
                    userLog.setUsername(username);
                    userLog.setTime(date);
                    userLog.setContent("用户："+username+"在"+dateFormat.format(date)+"进行剪力墙数据上传");
                    userLogMapper.insert(userLog);
//                    sb.append(6);

                    return "1";
                } catch (FileNotFoundException e) {
                    return e.getMessage();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "1";

    }

    /**
     * 上传转换后的信息
     * @param
     */
    public int uploadTransformInfo(String prjname,String username) {
        try {
            //先找到这个文件名
            WallTransformInfo info = wallTransformInfoMapper.getinfo(username,prjname);
            //生成查看输出图片的地址
            String outpngurl = outpath+info.getPngFileName();
            //生成查看输出文本的地址
            String outtxturl = outpath+info.getTxtFileName();
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
