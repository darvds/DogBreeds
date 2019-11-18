package com.darvds.dogbreeds.mvp

abstract class BasePresenter {

    private var onDestroyListener: OnDestroyListener? = null


    /**
     * Set the on destroy listener that will be fired when the activity
     * or fragment is destroyed
     */
    fun setOnDestroyListener(listener: OnDestroyListener) {
        onDestroyListener = listener
    }

    /**
     * Call this when the activity or fragment is destroyed
     */
    fun destroy() {
        onDestroyListener?.onDestroy()
    }


    abstract fun detachView()


    interface OnDestroyListener {
        fun onDestroy()
    }

}