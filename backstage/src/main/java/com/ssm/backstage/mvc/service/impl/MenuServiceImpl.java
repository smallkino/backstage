package com.ssm.backstage.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.backstage.model.Menu;
import com.ssm.backstage.model.Page;
import com.ssm.backstage.mvc.dao.MenuDao;
import com.ssm.backstage.mvc.service.MenuService;

/**
 * 
 * @ClassName: MenuServiceImpl
 * @Description: 菜单业务实现
 * @author xiaoxiaofeng
 * @date 2016年9月26日 下午3:10:48
 *
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(MenuServiceImpl.class);
	
	/**
	 * 菜单dao
	 */
	@Autowired
	private MenuDao menuDao;
	
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Author：xiaoxiaofeng
	 * @Description: 新增及保存
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void saveOrUpdate(Menu menu) {
		if(menu.getId() != null){
			menuDao.update(menu);
		}else{
			menuDao.save(menu);
		}
	}

	/**
	 * 
	 * @Title: getMenuByParentId
	 * @Author：xiaoxiaofeng
	 * @Description: 根据父级菜单id查询子菜单
	 * @param @param parentId
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Menu> getMenuByParentId(String parentId) {
		
		return menuDao.getMenuByParentId(parentId);
	}

	/**
	 * 
	 * @Title: getAllMenu
	 * @Author：xiaoxiaofeng
	 * @Description: 首页获取所有菜单
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Menu> getAllMenu() {
		List<Menu> menuList = menuDao.getAllMenu();
		List<Menu> result = new ArrayList<Menu>();
		//封装一级菜单
		for(Menu menu : menuList){
			if("-1".equals(menu.getParentId())){
				result.add(menu);
			}
		}
		//封装子菜单
		for(Menu pmenu : result){
			for(Menu smenu : menuList){
				if(pmenu.getId().equals(smenu.getParentId())){
					pmenu.getMenuList().add(smenu);
				}
			}
			
		}
		return result;
	}

	/**
	 * 
	 * @Title: getAllMenu
	 * @Author：xiaoxiaofeng
	 * @Description: 分页获取所有菜单
	 * @param @param page
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Menu> getAllMenu(Page page) {
		List<Menu> menuList = menuDao.getAllMenu(page);
		for(Menu menu : menuList){
			if("-1".equals(menu.getParentId())){
				menu.setParentName("无");
			}else{
				for(Menu pmenu : menuDao.getMenuByParentId("-1")){
					if(pmenu.getId().equals(menu.getParentId())){
						menu.setParentName(pmenu.getName());
						break;
					}
				}
			}
		}
		return menuList;
	}

	/**
	 * 
	 * @Title: getMenuList
	 * @Author：xiaoxiaofeng
	 * @Description: 查询菜单列表
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Menu> getMenuList() {
		return menuDao.getAllMenu();
	}

	/**
	 * 
	 * @Title: getById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id查询
	 * @param @param id
	 * @param @return    设定文件
	 * @return Menu    返回类型
	 * @throws
	 */
	@Override
	public Menu getById(String id) {
		return menuDao.getById(id);
	}

	/**
	 * 
	 * @Title: deleteById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id删除
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void deleteById(String id) {
		menuDao.deleteById(id);
	}
	
}
