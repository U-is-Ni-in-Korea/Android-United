package sopt.uni.data.repository.shortgame

import sopt.uni.data.entity.shortgame.MissionDetail

interface ShortGameRepository {
    suspend fun getMissionCategory(): Result<List<MissionDetail>>
}
