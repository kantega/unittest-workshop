package no.kantega.unittesting.utilities;

import org.apache.commons.dbcp2.BasicDataSource;

public class TestUtilities {

    public static BasicDataSource createTestDateSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUsername("demo");
        dataSource.setPassword("demo");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setInitialSize(1);
        return dataSource;
    }

}
