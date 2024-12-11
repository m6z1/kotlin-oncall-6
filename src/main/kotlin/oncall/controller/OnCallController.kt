package oncall.controller

import oncall.model.AssignmentMonth
import oncall.model.Worker
import oncall.view.InputView

class OnCallController(
    private val inputView: InputView,
) {

    fun start() {
        val month = getOnCallMonth()
        val (weekdaysWorkers, holidayWorkers) = getWorkers()

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