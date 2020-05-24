package repository.permission

interface AppPermissionProvider {

    fun isRecordAudioPermissionGranted() : Boolean
}
