package hr.santolin.jetpackflowerpower.utils

object Constants {

    fun routeType (): ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Dugi alpinistički")
        list.add("Dugi sportski")
        list.add("Kratki sportski")
        return list
    }
    const val LEADING_TYPE = "LeadingType"
    fun leadingType (): ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Cug-cug")
        list.add("Sve kao prvi")
        list.add("Sve kao drugi")
        return list
    }
}