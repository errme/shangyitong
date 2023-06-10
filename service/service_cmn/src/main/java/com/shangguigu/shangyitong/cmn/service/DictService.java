package com.shangguigu.shangyitong.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shangguigu.shangyitong.model.cmn.Dict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartFile;

//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {


    //根据数据id查询子数据列表
    List<Dict> findChlidData(Long dictCode);

    //导出数据字典接口
    void exportDictData(HttpServletResponse response);
    //导入数据字典
    void importDictData(MultipartFile file);
//
//    //根据dictcode和value查询
//    String getDictName(String dictCode, String value);
//
//    //根据dictCode获取下级节点
//    List<Dict> findByDictCode(String dictCode);
}
