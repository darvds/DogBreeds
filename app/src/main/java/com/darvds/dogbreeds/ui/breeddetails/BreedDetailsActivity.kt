package com.darvds.dogbreeds.ui.breeddetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.darvds.dogbreeds.R
import com.darvds.dogbreeds.application.BaseApplication
import com.darvds.dogbreeds.models.DogBreed
import kotlinx.android.synthetic.main.activity_breed_details.*
import javax.inject.Inject

class BreedDetailsActivity : AppCompatActivity(), BreedDetailsPresenter.View {
    companion object {
        fun getActivityIntent(context: Context, dogBreed: DogBreed): Intent {
            val intent = Intent(context, BreedDetailsActivity::class.java)
            intent.putExtra("breed", dogBreed)
            return intent
        }
    }

    @Inject
    lateinit var presenter: BreedDetailsPresenter

    var adapter: BreedDetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_breed_details)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initUi()

        (application as? BaseApplication)?.appComponent?.inject(this)

        val dogBreed = intent.getParcelableExtra<DogBreed>("breed")

        presenter.attachView(this)
        presenter.start(dogBreed)
    }

    /**
     * Initialise the UI with the required settings for the recycler and hide the loading
     * indicator
     */
    private fun initUi() {
        breed_details_recyclerview.layoutManager = GridLayoutManager(this, 3)
        setIsLoading(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Set the list of breeds to the view. Create or update an existing adapter
     */
    override fun setImages(images: List<String>?) {
        if (adapter == null) {
            adapter = BreedDetailsAdapter(this)
        }
        adapter!!.setImages(images)

        breed_details_recyclerview.adapter = adapter
    }


    /**
     * Show or hide the loading indicator
     */
    override fun setIsLoading(isLoading: Boolean) {
        breed_details_progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showLoadingError() {
        Toast.makeText(this, R.string.loading_error, Toast.LENGTH_SHORT).show()
    }

}