package oncall.controller

import oncall.model.*
import oncall.view.InputView
import oncall.view.OutputView

class OnCallController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    fun start() {
        val assignmentMonth = getOnCallMonth()
        val monthGenerator = MonthGenerator(assignmentMonth)
        val month = monthGenerator.generateMonth()
        val (weekdaysWorkers, holidayWorkers) = getWorkers()
        val schedule = mutableListOf<WorkSchedule>()
        var weekdayIndex = 0
        var holidayIndex = 0
        month.forEach { day ->
            when (day.dayType) {
                DayType.WEEKDAYS -> {
                    schedule.add(
                        WorkSchedule(
                            weekdaysWorkers[weekdayIndex % weekdaysWorkers.size],
                            day
                        )
                    )
                    weekdayIndex++
                }

                DayType.WEEKENDS -> {
                    schedule.add(
                        WorkSchedule(
                            holidayWorkers[holidayIndex % holidayWorkers.size],
                            day
                        )
                    )
                    holidayIndex++
                }

                DayType.HOLIDAYS -> {
                    schedule.add(
                        WorkSchedule(
                            holidayWorkers[holidayIndex % holidayWorkers.size],
                            day
                        )
                    )
                    holidayIndex++
                }
            }
        }
        val finalSchedule = nextLogic(schedule.toList())
        outputView.printSchedule(finalSchedule, assignmentMonth.month)
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
                else throw IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
            } catch (e: Exception) {
                println(e.message)
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

    private fun nextLogic(schedule: List<WorkSchedule>): List<WorkSchedule> {
        val updateSchedule = schedule.toMutableList()

        schedule.forEachIndexed { index, workSchedule ->
            if (index == schedule.size - 1) {
                return@forEachIndexed
            }
            if (schedule[index].worker.getWorkerNickName() == schedule[index + 1].worker.getWorkerNickName()) {
                val dayType = schedule[index + 1].monthInfo.dayType
                when (dayType) {
                    DayType.WEEKDAYS -> {
                        val nextWeekdaysWorker: Worker
                        for (i in index + 2..<schedule.size) {
                            if (schedule[i].monthInfo.dayType == DayType.WEEKDAYS) {
                                nextWeekdaysWorker = schedule[i].worker
                                updateSchedule[index + 1] =
                                    WorkSchedule(nextWeekdaysWorker, schedule[index + 1].monthInfo)
                                updateSchedule[i] = WorkSchedule(schedule[index + 1].worker, schedule[i].monthInfo)
                                return@forEachIndexed
                            }
                        }
                    }

                    DayType.WEEKENDS -> {
                        val nextHolidaysWorker: Worker
                        for (i in index + 2..<schedule.size) {
                            if (schedule[i].monthInfo.dayType == DayType.WEEKENDS) {
                                nextHolidaysWorker = schedule[i].worker
                                updateSchedule[index + 1] =
                                    WorkSchedule(nextHolidaysWorker, schedule[index + 1].monthInfo)
                                updateSchedule[i] = WorkSchedule(schedule[index + 1].worker, schedule[i].monthInfo)
                                return@forEachIndexed
                            }
                        }
                    }

                    DayType.HOLIDAYS -> {
                        val nextHolidaysWorker: Worker
                        for (i in index + 2..<schedule.size) {
                            if (schedule[i].monthInfo.dayType == DayType.WEEKENDS) {
                                nextHolidaysWorker = schedule[i].worker
                                updateSchedule[index + 1] =
                                    WorkSchedule(nextHolidaysWorker, schedule[index + 1].monthInfo)
                                updateSchedule[i] = WorkSchedule(schedule[index + 1].worker, schedule[i].monthInfo)
                                return@forEachIndexed
                            }
                        }
                    }
                }
            }
        }
        return updateSchedule.toList()
    }
}