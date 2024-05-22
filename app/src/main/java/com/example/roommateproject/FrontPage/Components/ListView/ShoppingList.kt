package com.example.roommateproject.FrontPage.Components.ListView

data class ShoppingList(
    val title: String,
    val completed: Boolean = false,
    val createdAt: Long = System.nanoTime()
) {
    val id: String = createdAt.toString()

    fun toggled(): ShoppingList = this.copy(completed = !this.completed)
}