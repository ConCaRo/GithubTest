package trong.test.githubtest

import org.junit.Test


class ServiceLocatorTest {


    class Service1 {
        val value = 1
    }

    class Service2 {
        val value = 2
        fun involke(service1: Service1) {}
    }

    class Service3(val service1: Service1) {
        val value = 3
        fun doSth() {
            print("get value service1 ${service1.value}")
        }
    }

    class ServiceLocator() {
        val service1: Service1 by lazy { Service1() }
        val service2: Service2 by lazy { Service2() }
        val service3: Service3 by lazy {
            Service3(
                service1
            )
        }
    }


    @Test
    fun `test`() {
        val locator = ServiceLocator()
        locator.service3.doSth()
    }
}