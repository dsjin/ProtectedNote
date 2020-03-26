package tk.dsjin.protectednote.usecase.dataholder

import tk.dsjin.protectednote.usecase.NoteUseCase

data class Result<T> (
    val result : NoteUseCase.ResultStatus,
    val message : String?,
    val data : T?
){
    companion object{
        fun <T> success(message: String?, data: T?) = Result(NoteUseCase.ResultStatus.Success, message, data)
        fun <T> failure(message: String?, data: T?) = Result(NoteUseCase.ResultStatus.Failure, message, data)
        fun <T> notFound(message: String?, data: T?) = Result(NoteUseCase.ResultStatus.NotFound, message, data)
    }
}