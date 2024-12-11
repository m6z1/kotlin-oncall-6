package oncall.model

data class WorkSchedule(
    val worker: Worker,
    val monthInfo: MonthInfo,
)