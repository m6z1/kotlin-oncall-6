package oncall.model

enum class Month(val title: Int, val daysCount: Int) {
    JAN(1, 31),
    FEB(2, 28),
    MAR(3, 31),
    APL(4, 30),
    MAY(5, 31),
    JUN(6, 30),
    JUL(7, 31),
    AUG(8, 31),
    SEP(9, 30),
    OCT(10, 31),
    NOV(11, 30),
    DEC(12, 31);

    companion object {

        fun from(title: Int): Int {
            return Month.entries.find {
                it.title == title
            }?.daysCount ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
        }
    }
}