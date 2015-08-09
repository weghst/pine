package com.weghst.pine;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

@ContextConfiguration(locations = {"classpath:spring-pine-core-test.xml"})
public abstract class SpringTestSupport extends AbstractTransactionalTestNGSpringContextTests {
}
