package stickearn.movie.stickearnmovieapps.view.movieDetails

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import stickearn.movie.stickearnmovieapps.BuildConfig
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.utils.PaginationStatus
import stickearn.movie.stickearnmovieapps.view.movieDetails.reviews.ReviewsMovieAdapter


class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel {
        parametersOf(intent.getParcelableExtra(MOVIE_DATA))
    }

    private var reviewsMovieAdapter = ReviewsMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        initView()
        initObserver()
        initEventListener()
    }

    private fun initView() {

        rvMovieReviews.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        rvMovieReviews.adapter = reviewsMovieAdapter
    }

    private fun initObserver() {

        detailMovieViewModel.showMovieDataEvent.observe(this, {

            cToolbarDetailMovie.title = it.title
            tvMovieReleaseDate.text = it.releaseDate

            Picasso
                .get()
                .load(
                    String.format(
                        "%s/t/p/w500/%s",
                        BuildConfig.BASE_TMDB_IMAGE_URL,
                        it.backdropPath
                    )
                )
                .fit()
                .into(ivMovieToolbar)

            tvMovieName.text = it.title

            tvMovieDescription.text = it.overview
        })

        detailMovieViewModel.initializeReviewsMovieLiveData().observe(this, {
            reviewsMovieAdapter.submitList(it)
        })

        detailMovieViewModel.reviewsMovieDataSourceFactory?.paginationStatus?.observe(this, {

            when (it) {
                is PaginationStatus.Empty -> tvNoReviewsMovie.isVisible = true
            }
        })

        detailMovieViewModel.changeFavoriteIconColorEvent.observe(this, {

            if (it) ivFavoriteMovie.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_twotone_favorite_24
                )
            ) else {
                ivFavoriteMovie.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_twotone_unfavorite_24
                    )
                )
            }
        })

        detailMovieViewModel.saveFavoriteMovieEvent.observe(this, Observer {

            if (it) {
                Snackbar
                    .make(
                        coordinator_layout,
                        getString(R.string.marked_favorite_movies),
                        Snackbar.LENGTH_SHORT
                    ).setAnchorView(clMenuDetailMovie)
                    .show()

            } else {
                Snackbar
                    .make(
                        coordinator_layout,
                        getString(R.string.unmarked_favorite_movies),
                        Snackbar.LENGTH_SHORT
                    ).setAnchorView(clMenuDetailMovie)
                    .show()
            }
        })

        detailMovieViewModel.shareLinkEvent.observe(this, {

            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, it)

            startActivity(Intent.createChooser(intent, "Share"))
        })
    }

    private fun initEventListener() {

        toolbar.setNavigationOnClickListener {
            finish()
        }

        ivFavoriteMovie.setOnClickListener {
            detailMovieViewModel.favoriteIconClicked()
        }

        ivShareMovie.setOnClickListener {
            detailMovieViewModel.shareIconClicked()
        }
    }

    companion object {
        const val MOVIE_DATA = "MovieData"
    }
}