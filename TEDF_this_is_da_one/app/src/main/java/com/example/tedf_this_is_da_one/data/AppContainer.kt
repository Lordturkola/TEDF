package com.example.tedf_this_is_da_one.data

interface IAppContainer {

    }

    /**
     * Container that contains all depeneencies needed
     */
    class AppContainer() : IAppContainer {
        /**
         * Implementation dependencies neededd here
         */
        companion object {
            enum class HomeScreenNav() {
                Home,
                Review,
                Edit,
                View,
            }
        }
    }
