package oncall.model

class Worker(private val nickName: String) {

    init {
        require(nickName.length in 1..5) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(nickName.isBlank().not()) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
    }

    fun getWorkerNickName(): String {
        return nickName
    }

    companion object {

        fun from(nickName: String): Worker {
            return Worker(nickName)
        }
    }
}