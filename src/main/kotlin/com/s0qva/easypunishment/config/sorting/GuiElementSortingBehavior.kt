package com.s0qva.easypunishment.config.sorting

import com.s0qva.easypunishment.client.util.basic.StringUtil
import gg.essential.vigilance.data.Category
import gg.essential.vigilance.data.PropertyData
import gg.essential.vigilance.data.SortingBehavior

class GuiElementSortingBehavior : SortingBehavior() {

    override fun getCategoryComparator(): Comparator<in Category> = compareBy {
        Integer.parseInt(StringUtil.split(it.name, StringUtil.DOT)[0])
    }

    override fun getSubcategoryComparator(): Comparator<in Map.Entry<String, List<PropertyData>>> = compareBy {
        Integer.parseInt(StringUtil.split(it.key, StringUtil.DOT)[0])
    }

    override fun getPropertyComparator(): Comparator<in PropertyData> = compareBy {
        Integer.parseInt(StringUtil.split(it.attributesExt.name, StringUtil.DOT)[0])
    }
}
