package uz.hamroev.blogchik.models.social

data class Social(
    val error: Boolean,
    val items: List<Item>,
    val message: String,
    val ok: Boolean
)