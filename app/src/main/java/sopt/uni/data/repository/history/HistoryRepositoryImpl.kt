package sopt.uni.data.repository.history

import sopt.uni.data.entity.history.History
import sopt.uni.data.entity.history.Mission
import sopt.uni.data.service.HistoryService
import sopt.uni.data.source.remote.response.HistoryResponseDto
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyService: HistoryService,
) : HistoryRepository {
    override suspend fun getHistoryList(): Result<List<History>> {
        return kotlin.runCatching {
            historyService.getHistoryList()
        }.fold(
            onSuccess = { response ->
                val historyList = convertToHistoryList(response)
                Result.success(historyList)
            },
            onFailure = { exception ->
                Result.failure(exception)
            },
        )
    }

    private fun convertToHistoryList(historyResponseDto: List<HistoryResponseDto>): List<History> {
        val historyList = historyResponseDto.map { historyResponse ->
            History(
                date = historyResponse.date,
                image = historyResponse.image,
                title = historyResponse.title,
                result = historyResponse.result,
                winner = historyResponse.winner,
                myMission = Mission(
                    content = historyResponse.myMission.content,
                    result = historyResponse.myMission.result,
                    time = historyResponse.myMission.time,
                ),
                partnerMission = Mission(
                    content = historyResponse.partnerMission.content,
                    result = historyResponse.partnerMission.result,
                    time = historyResponse.partnerMission.time,
                ),
            )
        }
        return historyList
    }
}
