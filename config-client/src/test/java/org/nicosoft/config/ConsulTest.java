package org.nicosoft.config;

import junit.framework.TestCase;
import org.junit.Test;
import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.consul.impl.ConsulServiceImpl;

public class ConsulTest extends TestCase {

    @Test
    public void testRegister() {
        ConsulService consulService = new ConsulServiceImpl();
        consulService.register();
        consulService.deregister();
    }
}
