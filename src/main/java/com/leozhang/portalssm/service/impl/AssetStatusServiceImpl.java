package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.AssetStatus;
import com.leozhang.portalssm.entity.AssetStatusExample;
import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.entity.SexExample;
import com.leozhang.portalssm.mapper.AssetStatusMapper;
import com.leozhang.portalssm.service.AssetStatusService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetStatusServiceImpl implements AssetStatusService {

    @Autowired
    private AssetStatusMapper assetStatusMapper;

    @Override
    public Result getListForPage(int pno, int psize, String assetStatusName, String sortField, String sortType) {
        //开启分页查询模式
        Page<AssetStatus> page = PageHelper.startPage(pno,psize);
        //创建查询的对象
        AssetStatusExample example = new AssetStatusExample();
        AssetStatusExample.Criteria criteria = example.createCriteria();
        if (assetStatusName.trim().length()>0){
            criteria.andAssetStatusNameLike("%"+assetStatusName+"%");
        }
        //添加排序条件
        if (sortField.trim().length()>0){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<AssetStatus> list = assetStatusMapper.selectByExample(example);

        return Result.end(200,list,"查询成功",page.getTotal());
    }
}
