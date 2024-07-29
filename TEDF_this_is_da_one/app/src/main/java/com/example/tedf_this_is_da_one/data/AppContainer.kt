package com.example.tedf_this_is_da_one.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

interface IAppContainer {
    val TedfCollection: CollectionReference
}

/**
 * Container that contains all depeneencies needed
 */
class AppContainer() : IAppContainer {
    /**
     * Implementation dependencies neededd here
     */
    override val TedfCollection: CollectionReference by lazy {
        FirebaseFirestore.getInstance().collection("TEDF")
    }

    companion object {
        enum class HomeScreenNav() {
            Home,
            Review,
            Edit,
            View,
        }
    }
}
