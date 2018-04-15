package redis.util.config;

import com.alibaba.druid.pool.DruidDataSource;  
import com.alibaba.druid.support.http.StatViewServlet;  
import com.alibaba.druid.support.http.WebStatFilter;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;  
import org.springframework.boot.web.servlet.FilterRegistrationBean;  
import org.springframework.boot.web.servlet.ServletRegistrationBean;  
import org.springframework.context.EnvironmentAware;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.core.env.Environment;  
  
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.SQLException;  
import java.util.HashMap;  
import java.util.Map;
import java.util.Properties;  

@Configuration
public class DruidConfiguration implements EnvironmentAware {  
  
//    private RelaxedPropertyResolver propertyResolver;  
  
	private Properties prop = null;
	
	public DruidConfiguration() {
		prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void setEnvironment(Environment env) {  
//        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");  
    }  
  
    @Bean  
    public DataSource dataSource() {  
        DruidDataSource datasource = new DruidDataSource();  
        datasource.setUrl(prop.getProperty("url"));  
        datasource.setDriverClassName(prop.getProperty("driver-class-name"));  
        datasource.setUsername(prop.getProperty("username"));  
        datasource.setPassword(prop.getProperty("password"));  
        datasource.setInitialSize(Integer.valueOf(prop.getProperty("initialSize")));  
        datasource.setMinIdle(Integer.valueOf(prop.getProperty("minIdle")));
        datasource.setValidationQuery(prop.getProperty("validationQuery"));
        datasource.setMaxWait(Long.valueOf(prop.getProperty("maxWait")));  
        datasource.setMaxActive(Integer.valueOf(prop.getProperty("maxActive")));  
        datasource.setMinEvictableIdleTimeMillis(  
        Long.valueOf(prop.getProperty("minEvictableIdleTimeMillis")));  
        try {  
            datasource.setFilters("stat");  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return datasource;  
    }  
  
    @Bean  
    public ServletRegistrationBean druidServlet() {  
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();  
        servletRegistrationBean.setServlet(new StatViewServlet());  
        servletRegistrationBean.addUrlMappings("/druid/*");  
        Map<String, String> initParameters = new HashMap<String, String>();  
        // initParameters.put("loginUsername", "druid");// 用户名  
        // initParameters.put("loginPassword", "druid");// 密码  
//        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能  
//        initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
        // initParameters.put("deny", "192.168.20.38");// IP黑名单  
        // (存在共同时，deny优先于allow)  
//        servletRegistrationBean.setInitParameters(initParameters);  
        return servletRegistrationBean;  
    }  
  
    @Bean  
    public FilterRegistrationBean filterRegistrationBean() {  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new WebStatFilter());  
        filterRegistrationBean.addUrlPatterns("/*");  
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");  
        return filterRegistrationBean;  
    }  
  
    @Bean  
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {  
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();  
        beanNameAutoProxyCreator.setProxyTargetClass(true);  
        // 设置要监控的bean的id  
        //beanNameAutoProxyCreator.setBeanNames("sysRoleMapper","loginController");  
        beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");  
        return beanNameAutoProxyCreator;  
    }  
}