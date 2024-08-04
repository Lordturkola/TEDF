package com.example.tedf_this_is_da_one.data

import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.tedf_this_is_da_one.R
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

interface IAppContainer {
    val TedfCollection: CollectionReference
    val TedfStorage: StorageReference
    val TedfFontProvider: GoogleFont.Provider
}

/**
 * Container that contains all depeneencies needed
 */
class AppContainer() : IAppContainer {
    /**
     * Implementation dependencies neededd here
     */
    override val TedfCollection: CollectionReference by lazy {
        FirebaseFirestore.getInstance().collection("energydrinks")
    }
    override val TedfStorage: StorageReference by lazy {
        FirebaseStorage.getInstance().getReference()
    }
    override val TedfFontProvider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    companion object {
        enum class HomeScreenNav() {
            Home,
            Review,
            Edit,
            View,
        }
    }
}
