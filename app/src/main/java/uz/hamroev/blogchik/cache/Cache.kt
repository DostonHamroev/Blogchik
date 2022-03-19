package uz.hamroev.blogchik.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "blogchik"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var language: String?
        get() = sharedPreferences.getString("language", "ru")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("language", value)
            }
        }



    var zoomUrl: String?
        get() = sharedPreferences.getString("zoomurl", "0")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("zoomurl", value)
            }
        }

    var imageTitle: String?
        get() = sharedPreferences.getString("imagetitle", "0")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("imagetitle", value)
            }
        }

    var imageDate: String?
        get() = sharedPreferences.getString("imagedate", "0")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("imagedate", value)
            }
        }


    var blogHTML: String?
        get() = sharedPreferences.getString("bloghtml", "0")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("bloghtml", value)
            }
        }

    var blogID: String?
        get() = sharedPreferences.getString("blogid", "0")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("blogid", value)
            }
        }




}
