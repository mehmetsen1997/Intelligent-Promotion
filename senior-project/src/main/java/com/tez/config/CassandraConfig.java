package com.tez.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {
 
//    @Override
//    protected String getKeyspaceName() {
//        return "tez_keyspace";
//    }
// 
//    @Override
//    public SchemaAction getSchemaAction() {
//        return SchemaAction.CREATE;
//    }
//   
//    
    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = 
          new CassandraClusterFactoryBean();
        cluster.setContactPoints("127.0.0.1");
        cluster.setPort(9042);
        cluster.setJmxReportingEnabled(false);
        return cluster;
    }
//    
//    @Override
//    public String[] getEntityBasePackages() {
//        return new String[]{"com.tez.model"};
//    }
// 
////    @Bean
////    public CassandraOperations cassandraOperations() throws Exception {
////        return new CassandraTemplate(session().getObject());
////    }
//    
    @Bean
    public CassandraMappingContext cassandraMapping() 
      throws ClassNotFoundException {
        return new CassandraMappingContext();
    }
	
	@Override
    public String getKeyspaceName() {
        return "tez_keyspace";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Bean
    public CassandraOperations cassandraOperations() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
    
    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.tez.model.cassandra"};
    }
    
    
}
