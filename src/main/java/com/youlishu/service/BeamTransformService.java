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
//    @Value("${Windows.beamintxtpath}")
//    private String beamintxtpath;
//
//    @Value("${Windows.beamoutpath}")
//    private String beamoutpath;

    @Value("${Linux.beaminpicpath}")
    private String beaminpicpath;

    @Value("${Linux.beamintxtpath}")
    private String beamintxtpath;

    @Value("${Linux.beamoutpath}")
    private String beamoutpath;



    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public int uploadBeamTransformInfo(String prjname, String username) {
        try {

            BeamTransformInfo info = beamTransformMapper.getinfo(username,prjname);

            String outpngurl = beamoutpath+ File.separator+info.getPngFileName();

            String deouttxturl = beamoutpath+File.separator+info.getTxtFileName();
            String outtxturl = deouttxturl.replace(".txt",".png.txt");
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
