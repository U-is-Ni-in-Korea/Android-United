package sopt.uni.presentation.shortgame.createshortgame

sealed class CreateShortGameState {
    object ShortGameState : CreateShortGameState()
    object MissionDetailState : CreateShortGameState()
}
