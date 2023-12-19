package com.shangguigu.shangyitong.hosp.controller;

import com.shangguigu.shangyitong.common.result.Result;
import com.shangguigu.shangyitong.hosp.service.DepartmentService;
import com.shangguigu.shangyitong.vo.hosp.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "医院部门数据")
//医院部门数据(MongoDB数据库
@RestController
@RequestMapping("/admin/hosp/department")
//@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "根据hoscode获取所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDepartmentList(@PathVariable String hoscode) {
        List<DepartmentVo> departmentList = departmentService.findDeptTree(hoscode);
        return Result.ok(departmentList);
    }
}
