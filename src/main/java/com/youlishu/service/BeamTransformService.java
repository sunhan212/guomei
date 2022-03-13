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

    //本机地址
    @Value("${Windows.beaminpicpath}")
    private String beaminpicpath;

    @Value("${Windows.wallinpicpath}")
    private String wallinpicpath;

    @Value("${Windows.beamoutpath}")
    private String beamoutpath;

    //公网地址
    @Value("${Windows.beaminpicpathurl}")
    private String beaminpicpathurl;

    @Value("${Windows.wallinpicpathurl}")
    private String wallinpicpathurl;

    @Value("${Windows.beamoutpathurl}")
    private String beamoutpathurl;



    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public int uploadBeamTransformInfo(Integer id, String username) {
        try {
            //先找到文件名
            BeamTransformInfo info = beamTransformMapper.getinfo(username,id);
            //生成查看输出图片的地址
            String outpngurl = beamoutpathurl + info.getPngFileName();
            System.out.println(outpngurl);
            //生成查看输出文本的地址
            String outtxturl = beamoutpathurl + info.getTxtFileName();
            System.out.println(outtxturl);
            //String outtxturl = deouttxturl.replace(".txt",".png.txt");
            BeamTransformInfo transinfo = new BeamTransformInfo();
            transinfo.setBeamOutPngUrl(outpngurl);
            transinfo.setBeamOutTxtUrl(outtxturl);
            transinfo.setTransformTime(new Date());
            transinfo.setUserName(username);
            transinfo.setId(id);
            //transinfo.setPrjName(prjName);
            beamTransformMapper.update(transinfo);

        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /*public String findPngFileName(String prjName, String username) {

        return beamTransformMapper.findPngFileName(prjName,username);
    }*/
}
