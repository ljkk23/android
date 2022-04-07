package com.example.todolist;

data class Todo(
    val content: String,
    val createTime: String,
    val done:String,
) {
    companion object {
        val TABLE = "Todo"
        val COL_ID = "id"
        val COL_CONTENT = "content"
        val COL_TIME = "createTime"
        val COL_DONE="done"
    }

    var id: Int? = null

}
