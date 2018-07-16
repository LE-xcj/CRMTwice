/**
 * 
 */
package edu.zhku.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * <p>
 * Title: MyApplication.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年7月4日
 */
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "edu.zhku")		//扫描包
@PropertySource(value = { "classpath:db.properties"} )		//加载配置文件
@MapperScan("edu.zhku.mapper")		//扫描mapper文件
public class MyApplication extends SpringBootServletInitializer{
	
	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.driver}")
	private String jdbcDriverClassName;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	/**
	 * 配置数据源
	 * @return
	 */
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(jdbcDriverClassName);	//驱动
		dataSource.setUrl(jdbcUrl);		//url
		dataSource.setUsername(jdbcUsername);// 用户名
		dataSource.setPassword(jdbcPassword);// 密码
		return dataSource;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(MyApplication.class);
	}
	
	
}
