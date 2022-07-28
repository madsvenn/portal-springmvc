package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.entity.SexExample;
import com.leozhang.portalssm.mapper.SexMapper;
import com.leozhang.portalssm.service.SexService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexServiceImpl implements SexService {

    @Autowired
    private SexMapper sexMapper;

    @Override
    public Result getListForPage(int pno, int psize, String sexName, String sortField, String sortType) {

        //开启分页查询模式
        Page<Sex> page = PageHelper.startPage(pno,psize);
        //创建查询的对象
        SexExample example = new SexExample();
        SexExample.Criteria criteria = example.createCriteria();
        if (sexName.trim().length()>0){
            criteria.andSexNameLike("%"+sexName+"%");
        }
        //添加排序条件
        if (sortField.trim().length()>0){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<Sex> list = sexMapper.selectByExample(example);

        return Result.end(200,list,"查询成功",page.getTotal());
    }
}
