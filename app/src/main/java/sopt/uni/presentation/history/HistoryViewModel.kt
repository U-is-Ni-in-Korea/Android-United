package sopt.uni.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sopt.uni.data.entity.history.History
import sopt.uni.data.entity.history.Mission

/** TODO 준희 : @HiltViewModel 붙이기*/
class HistoryViewModel : ViewModel() {
    private val _historyData = MutableLiveData<List<History>>(emptyList())
    val historyData: LiveData<List<History>> = _historyData

    init {
        getHistoryList()
    }

    /** TODO 준희 : 서버통신 로직 구현하기 */
    private fun getHistoryList() {
        _historyData.value = listOf(
            History(
                date = "23.07.13",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
                winner = "철수",
                myMission = Mission(
                    content = "텀블러",
                    result = "WIN",
                    time = "17:40",
                ),
                partnerMission = Mission(
                    content = "젤리",
                    result = "LOSE",
                    time = "17:40",
                ),
            ),
            History(
                date = "23.07.30",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기1",
                result = "WIN",
                winner = "철수",
            ),
            History(
                date = "23.07.21",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기2",
                result = "LOSE",
                winner = "철수",
            ),
            History(
                date = "23.07.18",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기3",
                result = "WIN",
                winner = "철수",
            ),
            History(
                date = "23.07.20",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기4",
                result = "DRAW",
                winner = "철수",
            ),
            History(
                date = "23.07.12",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
            ),
            History(
                date = "23.07.13",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
            ),
            History(
                date = "23.07.13",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
            ),
            History(
                date = "23.07.13",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
            ),
            History(
                date = "23.07.13",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
            ),
            History(
                date = "23.07.13",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
            ),
            History(
                date = "23.07.13",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
            ),
            History(
                date = "23.07.13",
                image = "http://image.yes24.com/goods/90365124/XL",
                title = "끝말잇기",
                result = "WIN",
            ),
        )
    }
}
