package oncall.controller

import oncall.model.*
import oncall.view.InputView

class OnCallController(
    private val inputView: InputView,
) {

    fun start() {
        val assignmentMonth = getOnCallMonth()
        val monthGenerator = MonthGenerator(assignmentMonth)
        val month = monthGenerator.generateMonth()
        val (weekdaysWorkers, holidayWorkers) = getWorkers()
        val schedule = mutableListOf<WorkSchedule>()
        month.forEach { day ->
            when (day.dayType) {
                DayType.WEEKDAYS -> {
                    weekdaysWorkers.forEach { worker ->
                        schedule.add(WorkSchedule(worker, day))
                    }
                }

                DayType.HOLIDAYS -> {
                    holidayWorkers.forEach { worker ->
                        schedule.add(WorkSchedule(worker, day))
                    }
                }
            }
        }
        println(schedule)
    }

    private fun getOnCallMonth(): AssignmentMonth {
        while (true) {
            try {
                val assignmentMonth = inputView.readOnCallMonth()
                return assignmentMonth
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    private fun getWorkers(): Pair<List<Worker>, List<Worker>> {
        while (true) {
            try {
                val weekdaysWorkers = getWorkersOfWeekdays()
                val holidayWorkers = getWorkersOfHolidays()

                var count = 0
                holidayWorkers.forEach { holidaysWorker ->
                    weekdaysWorkers.forEach {
                        if (it.getWorkerNickName() == holidaysWorker.getWorkerNickName()) count++
                    }
                }
                if (count == holidayWorkers.size && holidayWorkers.size == weekdaysWorkers.size)
                    return (weekdaysWorkers to holidayWorkers)
            } catch (e: Exception) {
                println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
                return getWorkers()
            }
        }
    }

    private fun getWorkersOfWeekdays(): List<Worker> {
        val workersOfWeekdays = inputView.readOnCallWeekdays()
        return workersOfWeekdays
    }

    private fun getWorkersOfHolidays(): List<Worker> {
        val workersOfHolidays = inputView.readOnCallHolidays()
        return workersOfHolidays
    }
}