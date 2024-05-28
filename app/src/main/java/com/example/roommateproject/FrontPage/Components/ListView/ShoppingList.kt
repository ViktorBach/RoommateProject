package com.example.roommateproject.FrontPage.Components.ListView

/*****************************************************************************/
// ShoppingList data class //

/*****************************************************************************/
//Viktor
//Data class declaring content of each shopping list item
data class ShoppingList(
    val title: String,
    val completed: Boolean = false,
    val createdAt: Long = System.nanoTime(),
) {
    val id: String = createdAt.toString()

    //Creates a copy of the list item with opposite completed state
    fun toggled(): ShoppingList = this.copy(completed = !this.completed)
}
