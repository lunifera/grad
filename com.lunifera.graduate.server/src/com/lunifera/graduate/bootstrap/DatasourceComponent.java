/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.bootstrap;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.jdbc.DataSourceFactory;
import org.osgi.service.jndi.JNDIConstants;

@Component(immediate = true)
public class DatasourceComponent {

	private static final String DERBY_JDBC_EMBEDDED_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DBNAME_GRADUATE = "graduate";
	private static final String GRADUATE_DS = "GraduateDs";

	private DataSourceFactory dsf;
	private DataSource ds;
	private DataSource jtaDs;
	private XADataSource xaDs;
	private ServiceRegistration<DataSource> dsRegistry;
	private ServiceRegistration<DataSource> dsJtaRegistry;
	private ServiceRegistration<XADataSource> xaDsRegistry;

	@Activate
	protected void activate(ComponentContext context) {
		// try {
		// ds = dsf.createDataSource(null);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		//
		// registry =
		// context.getBundleContext().registerService(DataSource.class,
		// ds, null);

		try {
			Properties dsProps = new Properties();
			dsProps.put(DataSourceFactory.JDBC_DATABASE_NAME,
					DBNAME_GRADUATE);
			dsProps.put(DataSourceFactory.JDBC_URL,
					"jdbc:derby:graduate;create=true");
			dsProps.put(DataSourceFactory.OSGI_JDBC_DRIVER_CLASS,
					DERBY_JDBC_EMBEDDED_DRIVER);
			dsProps.put(DataSourceFactory.JDBC_USER, "app");
			dsProps.put(DataSourceFactory.JDBC_PASSWORD, "app");
			ds = dsf.createDataSource(dsProps);

			dsProps = new Properties();
			dsProps.put(DataSourceFactory.JDBC_DATABASE_NAME,
					DBNAME_GRADUATE);
			dsProps.put(DataSourceFactory.JDBC_URL,
					"jdbc:derby:graduate;create=true");
			dsProps.put(DataSourceFactory.OSGI_JDBC_DRIVER_CLASS,
					DERBY_JDBC_EMBEDDED_DRIVER);
			dsProps.put(DataSourceFactory.JDBC_USER, "app");
			dsProps.put(DataSourceFactory.JDBC_PASSWORD, "app");
			jtaDs = dsf.createDataSource(dsProps);

			xaDs = dsf.createXADataSource(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(JNDIConstants.JNDI_SERVICENAME, GRADUATE_DS);
		dsRegistry = context.getBundleContext().registerService(
				DataSource.class, ds, props);

	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind="unsetDatasourceFactory")
	protected void setDatasourceFactory(DataSourceFactory dsf) {
		this.dsf = dsf;
	}
	
	protected void unsetDatasourceFactory(DataSourceFactory dsf) {
		this.dsf = null;
	}

	@Deactivate
	protected void deactivate() {
		if (dsRegistry != null) {
			dsRegistry.unregister();
			dsRegistry = null;
		}

		if (dsJtaRegistry != null) {
			dsJtaRegistry.unregister();
			dsJtaRegistry = null;
		}

		if (xaDsRegistry != null) {
			xaDsRegistry.unregister();
			xaDsRegistry = null;
		}
	}

}
