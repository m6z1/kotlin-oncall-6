package oncall.view

import oncall.model.DayType
import oncall.model.WorkSchedule

class OutputView {

    fun printSchedule(schedules: List<WorkSchedule>, month: Int) {
        schedules.forEach { schedule ->
            if (schedule.monthInfo.dayType == DayType.HOLIDAYS) {
                println("${month}월 ${schedule.monthInfo.date}일 ${schedule.monthInfo.week.title}(${schedule.monthInfo.dayType.title}) ${schedule.worker.getWorkerNickName()}")
                return@forEach
            }
            println("${month}월 ${schedule.monthInfo.date}일 ${schedule.monthInfo.week.title} ${schedule.worker.getWorkerNickName()}")
        }
    }
}