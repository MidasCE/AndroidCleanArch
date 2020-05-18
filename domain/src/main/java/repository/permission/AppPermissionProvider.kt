package repository.permission

interface AppPermissionProvider {

    fun isPermissionGranted() : Boolean
}
