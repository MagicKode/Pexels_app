package ru.myapp.pexels_app.utils

import ru.myapp.pexels_app.db.repository.PicsRepository
import ru.myapp.pexels_app.main.MainActivity

object Constant {
    lateinit var APP: MainActivity
    lateinit var REPOSITORY: PicsRepository

    const val API_KEY = "nladKPeijNpyXBwATyNKT74eiCbzwVVWEJMW1vhsCYVlmBYrpOanSSca"
    const val BASE_URL = "https://api.pexels.com/"


}