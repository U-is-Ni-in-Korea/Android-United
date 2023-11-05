package sopt.uni.data.datasource.local

import javax.inject.Inject

class LocalPreferenceDataSourceImpl @Inject constructor(
    private val sparkleStorage: SparkleStorage,
) : LocalPreferenceDataSource {
    override fun setMemo(memo: String) {
        sparkleStorage.memoText = memo
    }

    override fun getMemo() = sparkleStorage.memoText ?: ""
}
