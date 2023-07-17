package sopt.uni.data.entity.shortgame

sealed class MissionResultState(val order: Int) {
    object WIN : MissionResultState(0)
    object LOSE : MissionResultState(1)
    object DRAW : MissionResultState(2)
    object UNDECIDED : MissionResultState(3)

    companion object {
        fun getMissionResultType(type: String): MissionResultState =
            when (type) {
                "WIN" -> WIN
                "LOSE" -> LOSE
                "DRAW" -> DRAW
                // "UNDECIDED
                else -> UNDECIDED
            }
    }
}
