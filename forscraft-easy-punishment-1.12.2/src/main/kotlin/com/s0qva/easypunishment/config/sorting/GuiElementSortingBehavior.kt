package com.s0qva.easypunishment.config.sorting

import gg.essential.vigilance.data.PropertyData
import gg.essential.vigilance.data.SortingBehavior

class GuiElementSortingBehavior : SortingBehavior() {

    override fun getPropertyComparator(): Comparator<in PropertyData> = compareBy {
        it.attributesExt.name
    }
}
