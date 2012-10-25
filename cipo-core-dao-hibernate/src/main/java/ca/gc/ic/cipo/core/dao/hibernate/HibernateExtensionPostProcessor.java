package ca.gc.ic.cipo.core.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Utility class that adds Hibernate persistent class definitions to an existing 
 * Spring Session Factory or Entity Manager Factory bean, possibly defined 
 * within a separate Spring configuration file in a separate jar file. By using 
 * this extension factory developers can add persistent classes to their Web 
 * application without modifying any of the existing Spring configuration or 
 * jar distribution files.
 * 
 * @author DenisJ1
 */
@SuppressWarnings("rawtypes")
public class HibernateExtensionPostProcessor implements BeanFactoryPostProcessor {
	
	/** Name of the reference to the sessionFactory bean. */
	private String sessionFactoryBeanName = "sessionFactory";

	/** Mapping resources to add to the session factory. */
	private List mappingResources;

	/** Annotated classes to add to the session factory. */
	private List annotatedClasses;

	/** Hibernate properties to add to the session factory. */
	private Properties hibernateProperties;

	/** Location of configuration. */
	private List configLocations;

	/**
	 * Adds the annotated classes and the mapping resources to the existing
	 * Session Factory configuration.
	 * 
	 * @param beanFactory The bean factory
	 */
	@SuppressWarnings("unchecked")
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		if (beanFactory
				.containsBean(sessionFactoryBeanName)) {
			BeanDefinition sessionFactoryBeanDefinition = beanFactory
				.getBeanDefinition(sessionFactoryBeanName);
			MutablePropertyValues propertyValues = sessionFactoryBeanDefinition
				.getPropertyValues();

			if (mappingResources != null) {
				// Do we have existing resources?
				PropertyValue propertyValue = propertyValues
					.getPropertyValue("mappingResources");

				if (propertyValue == null) {
					propertyValue = new PropertyValue("mappingResources",
							new ArrayList());
					propertyValues.addPropertyValue(propertyValue);
				}

				// Value is expected to be a list.
				List existingMappingResources = (List) propertyValue.getValue();
				existingMappingResources.addAll(mappingResources);
			}

			if (annotatedClasses != null) {
				// Do we have existing resources?
				PropertyValue propertyValue = propertyValues
					.getPropertyValue("annotatedClasses");

				if (propertyValue == null) {
					propertyValue = new PropertyValue("annotatedClasses",
							new ArrayList());
					propertyValues.addPropertyValue(propertyValue);
				}

				// Value is expected to be a list.
				List existingMappingResources = (List) propertyValue.getValue();
				existingMappingResources.addAll(annotatedClasses);
			}

			if (configLocations != null) {
				PropertyValue propertyValue = propertyValues
					.getPropertyValue("configLocations");
				if (propertyValue == null) {
					propertyValue = new PropertyValue("configLocations",
							new ArrayList());
					propertyValues.addPropertyValue(propertyValue);
				}
				List existingConfigLocations = (List) propertyValue.getValue();
				existingConfigLocations.addAll(configLocations);
			}

			if (hibernateProperties != null) {
				PropertyValue propertyValue = propertyValues
					.getPropertyValue("hibernateProperties");
				if (propertyValue == null) {
					propertyValue = new PropertyValue("hibernateProperties",
							new Properties());
					propertyValues.addPropertyValue(propertyValue);
				}
				Properties existingHibernateProperties = 
					(Properties) propertyValue.getValue();
				existingHibernateProperties.putAll(hibernateProperties);
			}
		} else {
			throw new NoSuchBeanDefinitionException(
					"No bean named ["
					+ sessionFactoryBeanName
					+ "] exists within the bean factory. "
					+ "Cannot post process session factory to "
					+ "add Hibernate resource definitions.");
		}
	}

	/**
	 * Set the name of the SessionFactory bean. By default this post processor
	 * looks for a bean of name &quot;sessionFactory&quot;
	 * 
	 * @param sessionFactoryBeanName The name of the session factory bean.
	 */
	public void setSessionFactoryBeanName(String sessionFactoryBeanName) {
		this.sessionFactoryBeanName = sessionFactoryBeanName;
	}

	/**
	 * Set the list of mapping resources (.hbm.xml files) to be added to the
	 * session factory.
	 * 
	 * @param mappingResources The list of mapping resources.
	 */
	public void setMappingResources(List mappingResources) {
		this.mappingResources = mappingResources;
	}

	/**
	 * The list of annotated classes to add to the session factory.
	 * 
	 * @param annotatedClasses The list of annotated classes that need to be added.
	 */
	public void setAnnotatedClasses(List annotatedClasses) {
		this.annotatedClasses = annotatedClasses;
	}

	/**
	 * The list of configuration locations (i.e. classpath:hibernate.cfg.xml) to
	 * add to the session factory
	 * 
	 * @param configLocations The list of configuration locations that need to be added.
	 */
	public void setConfigLocations(List configLocations) {
		this.configLocations = configLocations;
	}

	/**
	 * Hibernate properties to add to the session factory.
	 * 
	 * @param hibernateProperties The list of additional properties.
	 */
	public void setHibernateProperties(Properties hibernateProperties) {
		this.hibernateProperties = hibernateProperties;
	}
}
