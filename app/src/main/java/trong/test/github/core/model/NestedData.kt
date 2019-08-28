package trong.test.github.core.model

class Parent(
    val id: String = "",
    val title: String = "",
    val data: List<Children> = arrayListOf(Children(), Children(), Children(), Children(), Children())
)

class Children(
    val id: String = "",
    val name: String = "123",
    val image: String = "https://shopily-sg.s3.amazonaws.com/uploads/deals/56522/56522_logo_95d1e4ad.png"
)

