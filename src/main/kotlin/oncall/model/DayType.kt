package oncall.model

enum class DayType(val title: String) {
    WEEKDAYS("평일"),
    WEEKENDS("주말"),
    HOLIDAYS("휴일");

    companion object {

        fun from(week: Week): DayType {
            return when (week) {
                Week.SUNDAY -> WEEKENDS
                Week.MONDAY -> WEEKDAYS
                Week.TUESDAY -> WEEKDAYS
                Week.WEDNESDAY -> WEEKDAYS
                Week.THURSDAY -> WEEKDAYS
                Week.FRIDAY -> WEEKDAYS
                Week.SATURDAY -> WEEKENDS
            }
        }
    }
}