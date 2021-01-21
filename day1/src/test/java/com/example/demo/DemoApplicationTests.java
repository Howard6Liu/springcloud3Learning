package com.example.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserMapper usermapper;

	@Test
	public void contextLoads() {
	}


	@Test
	public void testQuery(){
		List<User> users =usermapper.selectList(null);
		users.forEach(System.out::println);

	}

//	@Test
//	public void testSelectPage() {
//		Page<User> page=new Page<>(1,4);
//		usermapper.selectPage(page,null);
//
//		page.getRecords().forEach(System.out::println);
//		System.out.println("目前是第几页 "+page.getCurrent());
//		System.out.println("共有多少页 "+page.getPages());
//		System.out.println(page.getSize());
//		System.out.println("共有多少条 "+page.getTotal());
//		System.out.println("有下一页嘛 "+page.hasNext());
//		System.out.println(page.hasPrevious());
//
//	}

	@Test
	public void testSelectMapsPage() {
		Page<User> page = new Page<>(1, 5);
		IPage<Map<String, Object>> mapIPage = usermapper.selectMapsPage(page, null);
		mapIPage.getRecords().forEach(System.out::println);
		System.out.println(page.getCurrent());
	}



//	@Test
//	public void testAutomaticInsert(){
//		User user=new User();
//		user.setName("Jessica");
//		user.setAge(20);
//		user.setEmail("jessica@11.com");
//		int rows = usermapper.insert(user);
//	}

//	@Test
//	public void testOptimistic(){
//		User user=new User();
//		user.setName("Roy");
//		user.setAge(90);
//		user.setEmail("Roy@11.com");
//		int rows = usermapper.insert(user);
//	}

//	@Test
//	public void testUpdateUser() {
//		//update user set name=? where id=?
//
//		User user=usermapper.selectById(1348182406984736770L);
//		user.setAge(90);
//		int rows = usermapper.updateById(user);
//		System.out.println(rows);
//	}

//	@Test
//	public void testUpdate(){
//		User user=usermapper.selectById(1348176237075488769L);
//		user.setAge(30);
//		int rows=usermapper.updateById(user);
//	}

}
