package com.darvds.dogbreeds.mvp

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity() {

    var presenter: T? = null


    override fun onPause() {
        super.onPause()
        presenter?.detachView()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            presenter?.destroy()
        }
    }
}