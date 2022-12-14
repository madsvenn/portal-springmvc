package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.LogMap;
import com.leozhang.portalssm.entity.LogMapExample;
import com.leozhang.portalssm.mapper.LogMapMapper;
import com.leozhang.portalssm.service.LogMapService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogMapServiceImpl implements LogMapService {

    @Autowired
    private LogMapMapper logMapMapper;

    @Override
    public Result getListForPage(int pno, int psize, String url, String sortField, String sortType) {
        Page<LogMap> p = PageHelper.startPage(pno, psize);
        LogMapExample lme = new LogMapExample();
        LogMapExample.Criteria criteria = lme.createCriteria();
        if(url!=null&&url.trim().length()>0){
            criteria.andUrlLike("%"+url+"%");
        }
        if(sortField.trim().length()>0){
            lme.setOrderByClause(sortField+" "+sortType);
        }
        logMapMapper.selectByExample(lme);
        return Result.end(200,p.getResult(),"查询成功",p.getTotal());
    }

    @Override
    public LogMap selectLogMapById(Long id) {
        return logMapMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertLogMap(LogMap logMap) {
        logMapMapper.insert(logMap);
    }

    @Override
    public void updateLogMap(LogMap logMap) {
        logMapMapper.updateByPrimaryKeySelective(logMap);
    }

    @Override
    public void deleteLogMapById(Long id) {
        logMapMapper.deleteByPrimaryKey(id);
    }
}
