package oncall.controller

import oncall.view.InputView

class OnCallController(
    private val inputView: InputView,
) {

    fun start() {
        val assignmentMonth = inputView.readOnCallMonth()
        val workersOfWeekdays = inputView.readOnCallWeekdays()
        val workersOfHolidays = inputView.readOnCallHolidays()

        var count = 0
        workersOfHolidays.forEach { holidaysWorker ->
            workersOfWeekdays.forEach {
                if (it.getWorkerNickName() == holidaysWorker.getWorkerNickName()) count++
            }
        }
        if (count == workersOfHolidays.size && workersOfHolidays.size == workersOfWeekdays.size) {

        }
    }
}