package com.styleru.vitaly.hseday2018.quest

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.styleru.vitaly.hseday2018.R
import me.zhanghai.android.materialprogressbar.MaterialProgressBar
import java.util.*


class QuestFragment : Fragment() {
    private lateinit var textQuest: TextView
    private lateinit var progressBar: MaterialProgressBar
    private lateinit var prefs: SharedPreferences
    private lateinit var currentProgress:TextView
    private val changeDate: GregorianCalendar = GregorianCalendar(2018, Calendar.SEPTEMBER, 13)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quest, container, false)
        textQuest = view.findViewById(R.id.quest_introduction)
        progressBar = view.findViewById(R.id.quest_progress_bar)
        currentProgress = view.findViewById(R.id.your_progress)

        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        progressBar.progress = prefs.getInt("passed", 0)

        val date = Calendar.getInstance().time

        if (date.before(changeDate.time)) {
            textQuest.setText(R.string.quest_text_before)
        } else if (date.after(changeDate.time)) {
            textQuest.setText(R.string.quest_text_after)
        }
        return view
    }


    fun refreshProgress() {
        currentProgress.text = String.format(resources.getString(R.string.quests_passed_short), prefs.getInt("passed", 0))
//        if (progressBar != null)
            progressBar.progress = prefs.getInt("passed", 0)
    }
}
