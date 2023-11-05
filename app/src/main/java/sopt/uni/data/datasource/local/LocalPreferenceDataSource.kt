package sopt.uni.data.datasource.local

interface LocalPreferenceDataSource {
    fun getMemo(): String
    fun setMemo(memo: String)
}
