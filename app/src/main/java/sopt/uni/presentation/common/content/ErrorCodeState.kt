package sopt.uni.presentation.common.content

sealed class ErrorCodeState {
    object NoPartner : ErrorCodeState()
    object NoError : ErrorCodeState()
    object NoToken : ErrorCodeState()
    object DuplicateGame : ErrorCodeState()
    object ServerError : ErrorCodeState()
}
