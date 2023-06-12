package com.shangguigu.shangyitong.hosp.controller;


import com.shangguigu.shangyitong.common.exception.ShangyitongException;
import com.shangguigu.shangyitong.common.helper.HttpRequestHelper;
import com.shangguigu.shangyitong.common.result.Result;
import com.shangguigu.shangyitong.common.result.ResultCodeEnum;
import com.shangguigu.shangyitong.common.util.MD5;
import com.shangguigu.shangyitong.hosp.service.HospitalService;
import com.shangguigu.shangyitong.hosp.service.HospitalSetService;
import com.shangguigu.shangyitong.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request){

        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //1 获取医院系统传递过来的签名,签名进行MD5加密
        String hospSign = (String)paramMap.get("sign");
        //2 根据传递过来医院编码，查询数据库，查询签名
        String hoscode = (String)paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);
        //4 判断签名是否一致
        if(!hospSign.equals(signKeyMd5)) {
            throw new ShangyitongException(ResultCodeEnum.SIGN_ERROR);
        }
        //传输过程中“+”转换为了“ ”，因此我们要转换回来
        String logoData = (String)paramMap.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        paramMap.put("logoData",logoData);

        //调用service的方法
        hospitalService.save(paramMap);
        return Result.ok();
    }

    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request) {
        //获取传递过来医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hoscode = (String)paramMap.get("hoscode");
        //1 获取医院系统传递过来的签名,签名进行MD5加密
        String hospSign = (String)paramMap.get("sign");

        //2 根据传递过来医院编码，查询数据库，查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);

        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //4 判断签名是否一致
        if(!hospSign.equals(signKeyMd5)) {
            throw new ShangyitongException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service方法实现根据医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

}
