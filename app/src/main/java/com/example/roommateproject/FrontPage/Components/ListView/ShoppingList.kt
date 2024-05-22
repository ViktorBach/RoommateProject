package com.example.roommateproject.FrontPage.Components.ListView

data class ShoppingList(
    val title: String,
    val completed: Boolean = false,
    val createdAt: Long = System.nanoTime()
) {
    val id: String = createdAt.toString()

    /**
     * Creates a copy of this task with the opposite completed state
     */
    fun toggled(): ShoppingList = this.copy(completed = !this.completed)
}