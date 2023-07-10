package sopt.uni.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import sopt.uni.R

class HistoryViewModel : ViewModel() {
    data class Repo(
        val date: String,
        @DrawableRes val result_img: Int,
        val title: String,
        val result: String,
        @DrawableRes val next: Int,
    )

    private val mockRepoList = listOf<Repo>(
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
        Repo(
            date = "23.06.20",
            result_img = R.drawable.rectangle,
            title = "대답 유도하기",
            result = "숭리",
            next = R.drawable.right,
        ),
    )

    fun getMockRepoList(): List<Repo> {
        return mockRepoList
    }
}
