package com.youlishu.controller;



import com.youlishu.bean.Bmjg1PO;
import com.youlishu.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 孙涵
 * 2022/3/14
 */
@CrossOrigin
@RestController
@RequestMapping("/company")
@Api("组织架构")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     *
     * @param num 级别
     * @param name 名称
     * @return
     */
    @RequestMapping(value = "/query" ,method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "按照级别查询", notes = "通过级别和名称查询下级")
    public List<Bmjg1PO> queryCompany(Integer num,String name){
        List<Bmjg1PO> bmjg1POList = companyService.queryByCompany(num,name);
        System.out.println(bmjg1POList.size());
        return bmjg1POList;
    }

    /**
     *
     * @param num 级别
     * @param name 名称
     * @param nextName 下级名称
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "添加", notes = "通过级别和名称添加下级名称")
    public boolean insertNext(@RequestParam("num") Integer num,
                              @RequestParam("name") String name,
                              @RequestParam("nextName") String nextName){
        boolean flag = companyService.insertNext(num,name,nextName);
        return flag;
    }

    /**
     *
     * @param num 级别
     * @param name 名称
     * @param newName 新名称
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "修改", notes = "通过级别和名称修改当前级别名称")
    public boolean update(@RequestParam("num") Integer num,
                              @RequestParam("name") String name,
                              @RequestParam("newName") String newName){
        boolean flag = companyService.update(num,name,newName);
        return flag;
    }

    /**
     *
     * @param num 级别
     * @param name 名称
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "删除", notes = "通过级别和名称删除")
    public boolean delete(@RequestParam("num") Integer num,
                          @RequestParam("name") String name){
        boolean flag = companyService.deleteByName(num,name);
        return flag;
    }
}
