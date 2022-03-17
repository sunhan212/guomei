package com.youlishu.service;





import com.youlishu.bean.Bmjg1PO;

import java.util.List;

/**
 * 孙涵
 * 2022/3/14
 */
public interface CompanyService {

    List<Bmjg1PO> queryByCompany(Integer num,String name);

    boolean insertNext(Integer num,String name,String nextName);

    boolean update(Integer num,String name,String newName);

    boolean deleteByName(Integer num, String name);
}
