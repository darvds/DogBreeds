package com.darvds.dogbreeds.mvp

class PresenterStorage {
    companion object{

        /**
         * List of active presenters in the app
         */
        var presenters = HashMap<String, BasePresenter>()

        /**
         * Get a presenter from the storage based on the id.
         *
         * Returns null if requesting wrong type or if the presenter
         * does not exist for that id.
         */
        inline fun <reified P : BasePresenter> getPresenter(id: String) : P? {
            return presenters[id] as? P?
        }

        /**
         * Add a new presenter to the storage to retain access to it
         * across the app lifecycle.
         *
         * This will add the listener to remove the presenter from the storage
         * when it is no longer in use.
         */
        fun  <P : BasePresenter> putPresenter(id: String, presenter: P){
            presenter.setOnDestroyListener(object: BasePresenter.OnDestroyListener {
                override fun onDestroy() {
                    presenters.remove(id)
                }
            })
            presenters[id] = presenter
        }
    }
}