package com.rma.expensetracker.presentation.navigation.bottom_navigation

data class BottomNavBarState(
    val isVisible: Boolean = false,
    val items: List<BottomNavItem> = emptyList()
)