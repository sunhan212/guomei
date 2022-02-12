package com.youlishu.service;


import com.youlishu.bean.BeamTransformInfo;
import com.youlishu.dao.BeamTransformMapper;
import com.youlishu.dao.UserLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BeamTransformService {

    @Autowired
    private BeamTransformMapper beamTransformMapper;
    @Autowired
    private UserLogMapper userLogMapper;

//    @Value("${Windows.beaminpicpath}")
//    private String beaminpicpath;
//
//    @Value("${Windows.beamoutpicpath}")
//    private String beamoutpicpath;
//
//    @Value("${Windows.walloutpicpath}")
//    private String walloutpicpath;
//
//    @Value("${Windows.beamouttxtpath}")
//    private String beamouttxtpath;
//
//    @Value("${Windows.beampypath}")
//    private String beampypath;

    @Value("${Linux.beaminpicpath}")
    private String beaminpicpath;
    @Value("${Linux.beaminpicpathurl}")
    private String beaminpicpathurl;

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


    public int uploadBeamTransformInfo(String prjname, String username) {
        try {
            //先找到文件名
            BeamTransformInfo info = beamTransformMapper.getinfo(username,prjname);
            //生成查看输出图片的地址
            String outpngurl = beamoutpathurl+ File.separator+info.getPngFileName();
            //生成查看输出文本的地址
            String outtxturl = beamoutpathurl+File.separator+info.getTxtFileName();
            //String outtxturl = deouttxturl.replace(".txt",".png.txt");
            BeamTransformInfo transinfo = new BeamTransformInfo();
            transinfo.setBeamOutPngUrl(outpngurl);
            transinfo.setBeamOutTxtUrl(outtxturl);
            transinfo.setTransformTime(date);
            transinfo.setUserName(username);
            transinfo.setPrjName(prjname);
            beamTransformMapper.update(transinfo);

        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
