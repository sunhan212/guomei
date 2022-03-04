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



//    @Value("${Linux.beaminpicpath}")
//    private String beaminpicpath;
//    @Value("${Linux.beaminpicpathurl}")
//    private String beaminpicpathurl;
//
//    @Value("${Linux.wallinpicpath}")
//    private String wallinpicpath;
//    @Value("${Linux.wallinpicpathurl}")
//    private String wallinpicpathurl;
//
//    @Value("${Linux.beamoutpath}")
//    private String beamoutpath;
//    @Value("${Linux.beamoutpathurl}")
//    private String beamoutpathurl;
    @Value("${Windows.beaminpicpath}")
    private String beaminpicpath;

    @Value("${Windows.wallinpicpath}")
    private String wallinpicpath;

    @Value("${Windows.beamoutpath}")
    private String beamoutpath;



    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public int uploadBeamTransformInfo(String prjName, String username) {
        try {
            //先找到文件名
            BeamTransformInfo info = beamTransformMapper.getinfo(username,prjName);
            //生成查看输出图片的地址
            String outpngurl = beamoutpath + info.getPngFileName();
            System.out.println(outpngurl);
            //生成查看输出文本的地址
            String outtxturl = beamoutpath + info.getTxtFileName();
            System.out.println(outtxturl);
            //String outtxturl = deouttxturl.replace(".txt",".png.txt");
            BeamTransformInfo transinfo = new BeamTransformInfo();
            transinfo.setBeamOutPngUrl(outpngurl);
            transinfo.setBeamOutTxtUrl(outtxturl);
            transinfo.setTransformTime(date);
            transinfo.setUserName(username);
            transinfo.setPrjName(prjName);
            beamTransformMapper.update(transinfo);

        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
