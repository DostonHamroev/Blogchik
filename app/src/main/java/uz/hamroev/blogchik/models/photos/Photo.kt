package uz.hamroev.blogchik.models.photos

data class Photo(
    val error: Boolean,
    val items: List<Item>,
    val message: String,
    val ok: Boolean
)