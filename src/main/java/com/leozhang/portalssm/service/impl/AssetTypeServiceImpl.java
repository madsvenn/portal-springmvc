package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.AssetStatus;
import com.leozhang.portalssm.entity.AssetStatusExample;
import com.leozhang.portalssm.entity.AssetType;
import com.leozhang.portalssm.entity.AssetTypeExample;
import com.leozhang.portalssm.mapper.AssetTypeMapper;
import com.leozhang.portalssm.service.AssetTypeService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetTypeServiceImpl implements AssetTypeService {

    @Autowired
    private AssetTypeMapper typeMapper;

    @Override
    public Result assetTypeService(int pno, int psize, String assetTypeName, String sortField, String sortType) {

        Page<AssetType> page = PageHelper.startPage(pno,psize);
        AssetTypeExample example = new AssetTypeExample();
        AssetTypeExample.Criteria criteria = example.createCriteria();
        if (assetTypeName.trim().length()>0){
            criteria.andAssetTypeNameLike("%"+assetTypeName+"%");
        }
        if (sortField.trim().length()>0){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<AssetType> list = typeMapper.selectByExample(example);

        return Result.end(200,list,"查询成功",page.getTotal());
    }

    @Override
    public AssetType selectById(Long id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertData(AssetType assetType) {
        typeMapper.insert(assetType);
    }

    @Override
    public void updatedata(AssetType assetType) {
        typeMapper.updateByPrimaryKeySelective(assetType);
    }

    @Override
    public void deleteData(Long id) {
        typeMapper.deleteByPrimaryKey(id);
    }
}
