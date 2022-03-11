package com.youlishu.service;


import com.youlishu.bean.BeamTransformInfo;
import com.youlishu.dao.BeamDeleteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class BeamDeleteService {

    @Autowired
    private BeamDeleteMapper beamDeleteMapper;

    @Value("${Windows.wallinpicpath}")
    private String wallinpicpath;

    @Value("${Windows.beaminpicpath}")
    private String beaminpicpath;

    @Value("${Windows.beamintxtpath}")
    private String beamintxtpath;

    @Value("${Windows.beamoutpath}")
    private String beamoutpath;

    public boolean deleteBeam(Integer id) {
        //查询该数据
        BeamTransformInfo beamTransformInfo = beamDeleteMapper.findOneBeam(id);
        //判断是否转换
        //未转换情况下
        if (beamTransformInfo.getBeamOutPngUrl()==null && beamTransformInfo.getBeamOutTxtUrl()==null){
            //删除服务器上的照片、TXT文件
            boolean delete_flag = false;
            //删除照片
            File file1 = new File(beaminpicpath+beamTransformInfo.getPngFileName());
            File file2 = new File(wallinpicpath+beamTransformInfo.getPngFileName());
            File file3 = new File(beamintxtpath+beamTransformInfo.getTxtFileName());
            if (file1.exists() && file1.isFile() && file2.exists() && file2.isFile() && file3.exists() && file3.isFile()){
                file1.delete();
                file2.delete();
                file3.delete();
                delete_flag = true;
            }else
                delete_flag = false;
            //删除数据库信息
            if (delete_flag == true){
                int a = beamDeleteMapper.deleteBeam(id);
                if (a == 1){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
            //已经转换
        }else {
            //删除服务器上的照片、TXT文件
            boolean delete_flag = false;
            //删除照片
            File file1 = new File(beaminpicpath+beamTransformInfo.getPngFileName());
            File file2 = new File(wallinpicpath+beamTransformInfo.getPngFileName());
            File file3 = new File(beamintxtpath+beamTransformInfo.getTxtFileName());
            File file4 = new File(beamoutpath+beamTransformInfo.getPngFileName());
            File file5 = new File(beamoutpath+beamTransformInfo.getTxtFileName());
            if (file1.exists() && file1.isFile() && file2.exists() && file2.isFile() && file3.exists() && file3.isFile()
                    && file4.exists() && file4.isFile() && file5.exists() && file5.isFile()){
                file1.delete();
                file2.delete();
                file3.delete();
                file4.delete();
                file5.delete();
                delete_flag = true;
            }else
                delete_flag = false;
            //删除数据库信息
            if (delete_flag == true){
                int a = beamDeleteMapper.deleteBeam(id);
                if (a == 1){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
