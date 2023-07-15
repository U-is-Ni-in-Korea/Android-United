package sopt.uni.data.entity.shortgame

data class MissionDetail(
    val id: Int,
    val title: String,
    val description: String,
    val rule : String,
    val tip: String,
    val image: String,
    val missionContentList: List<MissionContent>
)
