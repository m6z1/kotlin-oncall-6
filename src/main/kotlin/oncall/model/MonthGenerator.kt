package oncall.model

class MonthGenerator(private val assignmentMonth: AssignmentMonth) {

    fun generateMonth(): List<MonthInfo> {
        val daysCount = Month.from(assignmentMonth.month)
        val holiday: PublicHoliday? = PublicHoliday.from(assignmentMonth.month)
        val month = mutableListOf<MonthInfo>()
        val startWeek = Week.entries.find {
            it.title == assignmentMonth.startWeekOfMonth
        }
        var index = Week.entries.indexOf(startWeek)

        if (holiday == null) {
            for (days in 1..daysCount) {
                month.add(
                    MonthInfo(
                        date = days,
                        week = Week.entries[index],
                        dayType = DayType.from(Week.entries[index]),
                    )
                )
                index = (index + 1) % Week.entries.size
            }
            return month
        }

        for (days in 1..daysCount) {
            if (daysCount == holiday.dayOfMonth) {
                index = (index + 1) % Week.entries.size
                month.add(
                    MonthInfo(
                        date = days,
                        week = Week.entries[index],
                        dayType = DayType.HOLIDAYS,
                    )
                )
            } else {
                index = (index + 1) % Week.entries.size
                month.add(
                    MonthInfo(
                        date = days,
                        week = Week.entries[index],
                        dayType = DayType.from(Week.entries[index]),
                    )
                )
            }
        }
        return month
    }
}