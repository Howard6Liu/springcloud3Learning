package com.online.edu.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-04-10
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;

    //7 根据id修改的方法
    @PostMapping("updateTeacher/{id}")
    public R updateTeacher(@PathVariable String id,
                           @RequestBody EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //6 根据id查询讲师
    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher",eduTeacher);
    }

    //5 添加讲师的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //4 多条件组合查询带分页
    @PostMapping("moreCondtionPageList/{page}/{limit}")
    public R getMoreCondtionPageList(@PathVariable Long page, @PathVariable Long limit,
                                     @RequestBody(required = false) QueryTeacher queryTeacher) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        //调用service的方法实现条件查询带分页
        eduTeacherService.pageListCondition(pageTeacher,queryTeacher);
        //从pageTeacher对象里面获取分页数据
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("items",records);
    }

    //3 分页查询讲师列表的方法
    @GetMapping("pageList/{page}/{limit}")
    public R getPageTeacherList(@PathVariable Long page,
                                @PathVariable Long limit) {
        //创建page对象，传递两个参数
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        //调用方法分页查询
        eduTeacherService.page(pageTeacher,null);
        //从pageTeacher对象里面获取分页数据
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("items",records);
    }

    //1 查询所有的讲师功能
    @GetMapping
    public R getAllTeacherList() {
        //调用service的方法
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    //2 逻辑删除讲师
    @DeleteMapping("{id}")
    public R deleteTeacherById(@PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

