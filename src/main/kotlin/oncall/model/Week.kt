package oncall.model

enum class Week(val title: String) {
    SUNDAY("일"),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토");

    companion object {

        fun isValidWeek(title: String): Boolean {
            return Week.entries.any {
                it.title == title
            }
        }
    }
}