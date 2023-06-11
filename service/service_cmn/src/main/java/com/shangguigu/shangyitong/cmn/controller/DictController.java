package com.shangguigu.shangyitong.cmn.controller;


import com.shangguigu.shangyitong.cmn.service.DictService;
import com.shangguigu.shangyitong.common.result.Result;
import com.shangguigu.shangyitong.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;


    //根据dictCode获取下级节点
    @ApiOperation(value = "根据dictCode获取下级节点")
    @GetMapping("findChildData/{dictCode}")
    public Result findByDictCode(@PathVariable Long dictCode) {
        List<Dict> list = dictService.findChlidData(dictCode);
        return Result.ok(list);
    }

    //导出数据字典接口
    @GetMapping("exportDictData")
    public void exportDict(HttpServletResponse response) {
        dictService.exportDictData(response);
    }

    //导入数据字典
    @PostMapping("importDictData")
    public Result importDict(MultipartFile file) {
        dictService.importDictData(file);
        return Result.ok();
    }
}
