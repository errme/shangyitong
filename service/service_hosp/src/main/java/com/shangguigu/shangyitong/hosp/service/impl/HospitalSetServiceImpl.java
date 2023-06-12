package com.shangguigu.shangyitong.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangguigu.shangyitong.hosp.mapper.HospitalSetMapper;
import com.shangguigu.shangyitong.hosp.service.HospitalService;
import com.shangguigu.shangyitong.hosp.service.HospitalSetService;
import com.shangguigu.shangyitong.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {


    @Autowired
    private HospitalService hospitalService;

    //2 根据传递过来医院编码，查询数据库，查询签名
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);

        return hospitalSet.getSignKey();
    }
}
