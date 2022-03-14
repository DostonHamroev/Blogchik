package uz.hamroev.blogchik.models.projects

data class Projects(
    val error: Boolean,
    val items: List<Item>,
    val message: String,
    val ok: Boolean
)