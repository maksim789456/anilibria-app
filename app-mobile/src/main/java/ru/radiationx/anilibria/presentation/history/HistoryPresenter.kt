package ru.radiationx.anilibria.presentation.history

import moxy.InjectViewState
import ru.radiationx.anilibria.navigation.Screens
import ru.radiationx.anilibria.presentation.common.BasePresenter
import ru.radiationx.data.entity.app.release.ReleaseItem
import ru.radiationx.data.repository.HistoryRepository
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by radiationx on 18.02.18.
 */
@InjectViewState
class HistoryPresenter @Inject constructor(
        private val router: Router,
        private val historyRepository: HistoryRepository
) : BasePresenter<HistoryView>(router) {

    private val currentReleases = mutableListOf<ReleaseItem>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        observeReleases()
    }

    private fun observeReleases() {
        historyRepository
                .observeReleases()
                .subscribe {
                    currentReleases.clear()
                    currentReleases.addAll(it)
                    viewState.showReleases(it)
                }
                .addToDisposable()
    }

    fun localSearch(query: String) {
        if (!query.isEmpty()) {
            val searchRes = currentReleases.filter {
                it.title.orEmpty().contains(query, true) || it.titleEng.orEmpty().contains(query, true)
            }
            viewState.showReleases(searchRes)
        } else {
            viewState.showReleases(currentReleases)
        }
    }

    fun onItemClick(item: ReleaseItem) {
        router.navigateTo(Screens.ReleaseDetails(item.id, item.code, item))
    }

    fun onDeleteClick(item: ReleaseItem) {
        historyRepository.removeRelease(item.id)
    }

    fun onItemLongClick(item: ReleaseItem): Boolean {
        return false
    }
}