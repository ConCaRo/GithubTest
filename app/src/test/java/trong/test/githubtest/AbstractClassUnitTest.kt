package trong.test.githubtest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class AbstractClassUnitTest {

    @Before
    fun init() {

    }

    lateinit var abstractIndependentTest: AbstractIndependent

    @Test
    fun `test default abstract class using concrete class`() {
        abstractIndependentTest = AbstractIndependentTest()
        assertEquals(1, abstractIndependentTest.abstractmethod())
    }

    @Mock
    lateinit var abstractIndependent: AbstractIndependent

    @Test
    fun `test abstract class using mock annotation`() {
        Mockito.`when`(abstractIndependent.abstractmethod()).thenReturn(1)
        assertEquals(1, abstractIndependent.abstractmethod())
    }


    @Mock
    lateinit var abstractDependentMock: AbstractDependent

    @Test
    fun `test default class using mockito`() {
        val expectedResult = 1
        Mockito.doNothing().`when`(abstractDependentMock).preExecute()
        Mockito.`when`(abstractDependentMock.postExecute(Mockito.anyInt())).thenReturn(expectedResult)

        val actualResult = abstractDependentMock.callApi("alo1.com")
        assertEquals(expectedResult, actualResult)

        val inOrder = Mockito.inOrder(abstractDependentMock)
        inOrder.verify(abstractDependentMock).preExecute()
        inOrder.verify(abstractDependentMock).execute(Mockito.anyString())
        inOrder.verify(abstractDependentMock).postExecute(Mockito.anyInt())
    }
}

// Create concrete class
open class AbstractIndependentTest : AbstractIndependent() {

    override fun abstractmethod(): Int {
        return 1
    }

}


open abstract class AbstractIndependent {

    abstract fun abstractmethod(): Int

    fun defaultImpl(): String = "Hello"
}

open abstract class AbstractDependent {
    fun callApi(url: String) : Int {
        preExecute()
        val result = execute(url)
        return postExecute(result)
    }

    abstract fun preExecute()
    fun execute(url: String): Int = if(url == "alo.com") 1 else 0
    abstract fun postExecute(result: Int) : Int
}