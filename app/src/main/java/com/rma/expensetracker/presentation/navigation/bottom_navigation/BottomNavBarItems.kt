package com.rma.expensetracker.presentation.navigation.bottom_navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rma.expensetracker.R

object BottomNavBarItems {
    val BOTTOM_NAV_BAR_ITEMS = listOf(
        BottomNavItem(
            direction = BottomNavItem.BottomNavDirection.RECORDS,
            icon = R.drawable.outline_receipt_long_24,
            label = R.string.records_tab_label,
            contentDescription = R.string.records_tab_description,
        ),
        BottomNavItem(
            direction = BottomNavItem.BottomNavDirection.SAVINGS,
            icon = R.drawable.outline_savings_24,
            label = R.string.savings_tab_label,
            contentDescription = R.string.next,
        ),
        BottomNavItem(
            direction = BottomNavItem.BottomNavDirection.ADD,
            icon = R.drawable.outline_add_circle_24,
            contentDescription = R.string.add_tab_description,
        ),
        BottomNavItem(
                direction = BottomNavItem.BottomNavDirection.GROUPS,
            icon = R.drawable.outline_groups_24,
            label = R.string.groups_tab_label,
            contentDescription = R.string.groups_tab_description,
        ),
        BottomNavItem(
            direction = BottomNavItem.BottomNavDirection.BUDGET,
            icon = R.drawable.outline_account_balance_wallet_24,
            label = R.string.budget_tab_label,
            contentDescription = R.string.budget_tab_description,
        ),
    )
}

data class BottomNavItem(
    val direction: BottomNavDirection,
    @DrawableRes val icon: Int,
    @StringRes val label: Int? = null,
    @StringRes val contentDescription: Int? = null,
) {
    enum class BottomNavDirection {
        RECORDS,
        SAVINGS,
        ADD,
        GROUPS,
        BUDGET
    }
}
