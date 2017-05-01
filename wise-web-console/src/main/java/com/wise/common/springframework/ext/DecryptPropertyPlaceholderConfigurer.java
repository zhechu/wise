package com.wise.common.springframework.ext;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.wise.common.utils.DESUtils;

/**
 * 扩展 spring 属性配置文件处理程序
 * @author lingyuwang
 *
 */
public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		String password = props.getProperty("jdbc.password"); 
		if (password != null) { 
			//解密 jdbc.password 属性值，并重新设置 
			props.setProperty("jdbc.password", DESUtils.decode(password)); 
		}
		super.processProperties(beanFactoryToProcess, props);
	}

}
