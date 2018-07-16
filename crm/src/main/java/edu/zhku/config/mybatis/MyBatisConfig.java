/**
 * 
 */
package edu.zhku.config.mybatis;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <p>
 * Title: MyBatisConfig.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年7月5日
 */
@Configuration
public class MyBatisConfig {

	@Autowired
	private DataSource dataSource; // 当然也可以使用注入的方式来创建DataSource对象

	@Bean
	@ConditionalOnMissingBean // 当容器里没有指定的Bean的情况下创建该对象
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

		// 设置数据源
		sqlSessionFactoryBean.setDataSource(dataSource);

		// 设置mybatis的主配置文件
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		// 加载配置文件
		Resource mybatisConfigXml = resolver.getResource("classpath:mybatis/mybatis-config.xml");
		sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);

		// 设置别名包
		sqlSessionFactoryBean.setTypeAliasesPackage("edu.zhku.pojo");

		/**
		 * 相当于配置文件中的这部分配置
		 * 
		 * <typeAliases> <!--对单个pojo起别名--> <!--
		 * <typeAlias type="com.xc.pojo.User" alias="user"/> -->
		 * 
		 * <!-- 批量定义别名（推荐） --> <!--扫描指定包下所有的实体，默认别名是类名，首字母大小写都可以-->
		 * <package name="edu.zhku.pojo"/> </typeAliases>
		 */
		return sqlSessionFactoryBean;
	}
}
