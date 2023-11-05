package sopt.uni.data.entity.shortgame

sealed class MissionToolState {
    object MEMO : MissionToolState()
    object TIMER : MissionToolState()
    object NONE : MissionToolState()

    companion object {
        fun getMissionTool(tool: String): MissionToolState =
            when (tool) {
                "MEMO" -> MEMO
                "TIMER" -> TIMER
                else -> NONE
            }
    }
}
