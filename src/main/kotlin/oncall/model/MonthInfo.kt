package oncall.model

data class MonthInfo(
    val date: Int,
    val week: Week,
    val dayType: DayType,
)
