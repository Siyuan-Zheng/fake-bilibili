package me.shigure.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zhengsiyuan
 * @date 2019-03-05 14:05
 */
public class BeanUtil {
	/**
	 * 将resultSet转为bean对象
	 * @param clazz 需要封装的类
	 * @param resultSet    需要进行封装的resultSet
	 * @return 一个封装好的object
	 */
	public Object autoBean(Class<?> clazz, ResultSet resultSet) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//用来存取set方法的集合
		ArrayList<Method> setMethods = new ArrayList<>();
		// 实例化对象
		Object obj = clazz.getDeclaredConstructor().newInstance();
		try {
			//对象中的所有方法
			Method[] method = clazz.getMethods();
			for (Method m : method) {
				//截取前面3个英文字符，看是否为set，如果是暂定为set方法
				if (m.getName().substring(0, 3).contains("set")) {
					//将set方法加入集合
					setMethods.add(m);
				}
			}
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			//获取结果中列的个数
			int columnCount = resultSetMetaData.getColumnCount();
			//循环，目的为将每一列的值用正确的set方法
			for (int i = 1; i <= columnCount; i++) {
				//获取列名
				String columnName = resultSetMetaData.getColumnLabel(i);
				String field = columnName.replaceFirst(columnName.substring(0, 1),
						//因为set方法都是这种形式，setXxxx，因此将列名第一个字母大写
						columnName.substring(0, 1).toUpperCase());
				//遍历set方法集合
				for (Method m1 : setMethods) {
					//判断，如果通过判断既可知道该用对象的什么set方法
					if (m1.getName().equals("set" + field)) {
						//获取方法参数类型，判断是否为为setBoolean
						Class<?>[] para = m1.getParameterTypes();
						if ("boolean".equals(para[0].getName())) {
							//因为mysql的boolean是以0,1存在，所以做以下判断
							if (resultSet.getObject(columnName).equals(0)) {
								m1.invoke(obj, false);
							} else {
								m1.invoke(obj, true);
							}
						} else {
							//如果不是setBoolean这种，则执行一下
							//通过反射调用方法
							m1.invoke(obj, resultSet.getObject(columnName));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//返回对象
		return obj;
	}
}