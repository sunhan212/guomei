package com.youlishu.service;


import com.youlishu.bean.BeamTransformInfo;
import com.youlishu.bean.UserLog;
import com.youlishu.bean.WallTransformInfo;
import com.youlishu.dao.BeamUpdateMapper;
import com.youlishu.dao.UserLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface BeamUpdateService {


    BeamTransformInfo findOneBeam(Integer id, String username);

    boolean updateBeam(MultipartFile file1, MultipartFile file2, String prjName, Integer id,
                       String beamDesignType, String beamLong, String beamUp, String beamLow, String username);
}
