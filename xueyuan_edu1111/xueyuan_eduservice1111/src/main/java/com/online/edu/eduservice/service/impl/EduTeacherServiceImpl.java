package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduTeacherMapper;
import com.online.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-04-10
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    //条件查询带分页
    @Override
    public void pageListCondition(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher) {
//        try {
//            int i = 9/0;
//        }catch(Exception e) {
//            //抛出自定义异常
//            throw new EduException(20001,"执行自定义异常");
//        }

        //关键：queryTeacher有传递过来的条件值，判断，如果有条件值，拼接条件
        if(queryTeacher == null) {
            //直接查询分页，不进行条件操作
            baseMapper.selectPage(pageTeacher,null);
            return;
        }
        //如果queryTeacher不为空
        //从queryTeacher对象里面获取出条件值
        String name = queryTeacher.getName();
        String level = queryTeacher.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();
        //判断条件值是否有，如果有拼接条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            //拼接条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        //条件查询带分页
        baseMapper.selectPage(pageTeacher,wrapper);
    }
}
