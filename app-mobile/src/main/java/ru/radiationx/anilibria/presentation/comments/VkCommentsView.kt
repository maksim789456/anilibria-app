package ru.radiationx.anilibria.presentation.comments

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.radiationx.anilibria.presentation.common.IBaseView
import ru.radiationx.data.entity.app.page.VkComments

@StateStrategyType(AddToEndSingleStrategy::class)
interface VkCommentsView : IBaseView {

    fun showBody(comments: VkComments)
}