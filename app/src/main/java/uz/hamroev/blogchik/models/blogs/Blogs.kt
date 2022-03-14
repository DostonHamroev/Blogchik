package uz.hamroev.blogchik.models.blogs

data class Blogs(
    val error: Boolean,
    val items: List<Item>,
    val message: String,
    val ok: Boolean
)