package com.youlishu.service;

import com.youlishu.bean.WallTransformInfo;
import com.youlishu.dao.WallTransformInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yujin
 * @Date 2022/1/20 18:51
 * @Version 1.0
 */
@Service
public class FindService {
    @Autowired
    WallTransformInfoMapper wallTransformInfoMapper;

    /**
     * 查看剪力墙数据
     * @return
     */
    public List findWallInfo(String userName){
        List<WallTransformInfo>list = wallTransformInfoMapper.findWallInfo(userName);
        return list;
    }
}
