package com.youlishu.service.impl;




import com.youlishu.bean.Bmjg1PO;
import com.youlishu.dao.Bmjg1POMapper;
import com.youlishu.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 孙涵
 * 2022/3/14
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private Bmjg1POMapper bmjg1POMapper;
    @Override
    public List<Bmjg1PO> queryByCompany(Integer num,String name) {
        if (num == 1){
            return bmjg1POMapper.selectCompany(name);
        }else if (num == 2){
            return bmjg1POMapper.selectCompanyByTwo(name);
        }else if (num == 3){
            return bmjg1POMapper.selectCompanyByThree(name);
        }else if (num == 4){
            return bmjg1POMapper.selectCompanyByFour(name);
        }else if (num == 5){
            return bmjg1POMapper.selectCompanyByFive(name);
        }else if (num == 6){
            return bmjg1POMapper.selectCompanyBySix(name);
        }
        return null;
    }

    @Override
    public boolean insertNext(Integer num,String name,String nextName) {
        if (num == 1){
            return bmjg1POMapper.insertNext(name,nextName);
        }else if (num == 2){
            return bmjg1POMapper.insertNextByTwo(name,nextName);
        }else if (num == 3){
            return bmjg1POMapper.insertNextByThree(name,nextName);
        }else if (num == 4){
            return bmjg1POMapper.insertNextByFour(name,nextName);
        }else if (num == 5){
            return bmjg1POMapper.insertNextByFive(name, nextName);
        }else if (num == 6){
            return bmjg1POMapper.insertNextBySix(name, nextName);
        }
        return false;
    }

    @Override
    public boolean update(Integer num,String name,String newName) {
        if (num == 1){
            return bmjg1POMapper.update(name,newName);
        }else if (num == 2){
            return bmjg1POMapper.updateByTwo(name, newName);
        }else if (num == 3){
            return bmjg1POMapper.updateByThree(name, newName);
        }else if (num == 4){
            return bmjg1POMapper.updateByFour(name, newName);
        }else if (num == 5){
            return bmjg1POMapper.updateByFive(name, newName);
        }else if (num == 6){
            return bmjg1POMapper.updateBySix(name, newName);
        }else if (num == 7){
            return bmjg1POMapper.updateByServen(name, newName);
        }
        return false;
    }

    @Override
    public boolean deleteByName(Integer num, String name) {
        if (num == 1){
            return bmjg1POMapper.deleteByName(name);
        }else if (num == 2){
            return bmjg1POMapper.deleteByNameByTwo(name);
        }else if (num == 3){
            return bmjg1POMapper.deleteByNameByThree(name);
        }else if (num == 4){
            return bmjg1POMapper.deleteByNameByFour(name);
        }else if (num == 5){
            return bmjg1POMapper.deleteByNameByFive(name);
        }else if (num == 6){
            return bmjg1POMapper.deleteByNameBySix(name);
        }else if (num == 7){
            return bmjg1POMapper.deleteByNameByServen(name);
        }
        return false;
    }
}
