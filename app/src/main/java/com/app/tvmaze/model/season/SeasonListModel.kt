package com.app.tvmaze.model.season

import com.app.tvmaze.model.episode.EpisodeModel

class SeasonListModel (

    var numberSeason: Int = -1,

    var expandable: Boolean = false,

    var listEpisodes: List<EpisodeModel> = listOf()
)