package trong.test.githubtest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals




@RunWith(MockitoJUnitRunner::class)
open class MockitoUnitTest {

    @Before
    fun init() {

    }

    @Test
    fun `when not use mock annotation then correct`() {
        val mockList: ArrayList<String> = Mockito.mock(ArrayList::class.java) as ArrayList<String>

        mockList.add("one")
        Mockito.verify(mockList).add("one")
        assertEquals(0, mockList.size)

        Mockito.`when`(mockList.size).thenReturn(100)
       assertEquals(100, mockList.size)
    }

    @Mock
    lateinit var mockList : ArrayList<String>

    @Test
    fun `when use mock annotation then mock is inject`() {

        mockList.add("two")
        Mockito.verify(mockList).add("two")
        assertEquals(0, mockList.size)

        Mockito.`when`(mockList.size).thenReturn(100)
        assertEquals(100, mockList.size)
    }


    @Test
    fun `when not use spy annotation then correct`() {
        val list = Mockito.spy(ArrayList<String>())

        list.add("one")
        list.add("two")
        Mockito.verify(list).add("one")
        Mockito.verify(list).add("two")
        assertEquals(2, list.size)

        //Mockito.doReturn(100).`when`(list).size
        Mockito.`when`(list.size).thenReturn(100)
        assertEquals(100, list.size)
    }

    @Spy
    val spyList = ArrayList<String>()

    @Test
    fun `when use spy annotation then spy is inject correct`() {
        spyList.add("one")
        spyList.add("two")

        Mockito.verify(spyList).add("one")
        Mockito.verify(spyList).add("two")
        assertEquals(2, spyList.size)

        Mockito.`when`(spyList.size).thenReturn(100)
        assertEquals(100, spyList.size)
    }

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<List<String>>

    @Test
    fun  `when use capture annotation then correct`() {
        mockList.addAll(arrayListOf("one", "two"))
        Mockito.verify(mockList).addAll(argumentCaptor.capture())
        assertEquals(arrayListOf("one", "two"), argumentCaptor.value)
    }


    @InjectMocks
    val dic = MyDictionary()

    @Mock
    lateinit var wordMap1 : HashMap<String, String>

    /*@Mock
    lateinit var wordMap2 : HashMap<String, String>*/


    @Test
    fun `when use injectmock annotation then correct`() {
        dic.add("a", "mot")
        dic.add("an", "mot")
        Mockito.`when`(dic.wordMap.size).thenReturn(100)
        assertEquals(100, dic.wordMap.size)

        Mockito.`when`(wordMap1.get("word")).thenReturn("meaning")
        assertEquals("meaning", dic.getMeaning("word"))
    }

    var spyDic : MyDictionary = MyDictionary()

    @Test
    fun `when use inject mock into spy object then correct`() {
        spyDic.wordMap = wordMap1
        Mockito.`when`(wordMap1.get("word")).thenReturn("meaning")
        assertEquals("meaning", spyDic.getMeaning("word"))
    }


    inner class MyDictionary {
        var wordMap: MutableMap<String, String> = HashMap()

        fun add(word: String, meaning: String) {
            wordMap[word] = meaning
        }

        fun getMeaning(word: String): String {
            return wordMap[word]!!
        }
    }

}