package com.styleru.vitaly.hseday2018.map

import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.styleru.vitaly.hseday2018.R
import com.styleru.vitaly.hseday2018.dataclasses.*
import com.styleru.vitaly.hseday2018.main.MainActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.styleru.vitaly.hseday2018.dataclasses.*
import se.simbio.encryption.Encryption




class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private var mapView: MapView? = null
    private lateinit var mResources: Resources
    private lateinit var questBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var sportBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var lectureBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var questBottomSheetPeek: ConstraintLayout
    private lateinit var questBottomSheetPeekIcon: ImageView
    private lateinit var lectureBottomSheetPeek: ConstraintLayout
    private lateinit var lectureBottomSheetPeekIcon: ImageView
    private lateinit var lectureText: TextView
    private lateinit var lectureTitle: TextView
    private lateinit var mapBitmap: Bitmap
    private lateinit var questTitle: TextView
    private lateinit var questText: TextView
    private lateinit var questButtonCheck: Button
    private lateinit var questEditText: EditText
    private lateinit var questsPassed: TextView
    private lateinit var sportTitle: TextView

    private lateinit var prefs: SharedPreferences

    private val questMarkerList = ArrayList<Marker>()
    private lateinit var questsList: ArrayList<Quest>
    private lateinit var sportsList: ArrayList<Sport>
    private lateinit var musicList: ArrayList<Music>
    private lateinit var lectureList: ArrayList<Lecture>
    private lateinit var tentList: ArrayList<Tent>
    private lateinit var otherList: ArrayList<Tent>
    private lateinit var otherWithDescList: ArrayList<OtherWithDescription>
    private var currentQuest = 1

    private val key = "AnbjkDABSDHJgvhjAVSDUYAHSdbvJAKBSdy123"
    private val salt = "YourSalt"
    private val iv = ByteArray(16)
    private val encryption = Encryption.getDefault(key, salt, iv)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById(R.id.map)

        val a :String= " "



        mResources = context!!.resources
        mapBitmap = BitmapFactory.decodeResource(mResources, R.drawable.small_map1)

        mapView!!.onCreate(savedInstanceState)
        mapView!!.onResume()
        MapsInitializer.initialize(activity!!.applicationContext)
        mapView!!.getMapAsync(this)

        questBottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.quest_bottom_sheet))
        questBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        questBottomSheetPeek = view.findViewById(R.id.quest_bottom_sheet_peek)
        questBottomSheetPeekIcon = view.findViewById(R.id.quest_peek_icon)

        sportBottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.sport_bottom_sheet))
        sportBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN


        lectureBottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.lecture_bottom_sheet))
        lectureBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        lectureBottomSheetPeek = view.findViewById(R.id.lecture_bottom_sheet_peek)
        lectureBottomSheetPeekIcon = view.findViewById(R.id.lecture_peek_icon)
        lectureText = view.findViewById(R.id.lecture_text)
        lectureTitle = view.findViewById(R.id.lecture_dialog_title)

        lectureBottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState.equals(BottomSheetBehavior.STATE_COLLAPSED)) {
                    lectureBottomSheetPeekIcon.animate().rotation(0.0f).start()
                }
                if (newState.equals(BottomSheetBehavior.STATE_EXPANDED)) {
                    lectureBottomSheetPeekIcon.animate().rotation(-180.0f).start()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
        lectureBottomSheetPeek.setOnClickListener {
            if (lectureBottomSheetBehavior.state.equals(BottomSheetBehavior.STATE_COLLAPSED)) {
                lectureBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                lectureBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        prefs = PreferenceManager.getDefaultSharedPreferences(context)


        // Отслеживание изменения состояния нижнего элемента
        questBottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState.equals(BottomSheetBehavior.STATE_COLLAPSED)) {
                    questBottomSheetPeekIcon.animate().rotation(0.0f).start()
                }
                if (newState.equals(BottomSheetBehavior.STATE_EXPANDED)) {
                    questBottomSheetPeekIcon.animate().rotation(-180.0f).start()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })

        questBottomSheetPeek.setOnClickListener {
            if (questBottomSheetBehavior.state.equals(BottomSheetBehavior.STATE_COLLAPSED)) {
                questBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                questBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        questButtonCheck = view.findViewById(R.id.check_button)
        questEditText = view.findViewById(R.id.quest_dialog_edit_code)
        questsPassed = view.findViewById(R.id.quests_passed)

        questButtonCheck.setOnClickListener {
            checkQuestCode()
        }
        questTitle = view.findViewById(R.id.quest_dialog_title)
        questText = view.findViewById(R.id.quest_dialog_text)

        sportTitle = view.findViewById(R.id.sport_dialog_title)
        getAllDataFromJsonFiles()

        return view
    }

    private fun passQuest(number: Int) {
        questMarkerList[number].setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_quest_passed)))
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        // Делаем карту пустой
        map.mapType = GoogleMap.MAP_TYPE_NONE

        // Вставляем картинку в карту
//        val mResources = context!!.resources
//        mapBitmap = BitmapFactory.decodeResource(mResources, R.drawable.small_map1)

        val newarkMap = GroundOverlayOptions()
                .position(LatLng(0.0, 0.0), 27000000f, 12735849f)
                .image(BitmapDescriptorFactory.fromBitmap(mapBitmap))
        map.addGroundOverlay(newarkMap)

        // Задаем стартовую позицию для карты
        val startPos = LatLng(0.0, 0.0)
        val cameraPos = CameraPosition.Builder().target(startPos).zoom(3f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos))

        // Устанавливаем минимальный и максимальный зум для карты
        map.setMinZoomPreference(3f)
        map.setMaxZoomPreference(3.5f)

        // Ограничения движения карты
        val cameraBorders = LatLngBounds(LatLng(-24.0, -89.0), LatLng(24.0, 89.0))
        map.setLatLngBoundsForCameraTarget(cameraBorders)

        // Выключаем кнопки, которые вылезут при нажатии на метку
        map.uiSettings.isMapToolbarEnabled = false

        // Выключаем возможность поворота камеры
        map.uiSettings.isRotateGesturesEnabled = false

        map.setOnMarkerClickListener(this)
        map.setOnCameraMoveStartedListener(this)

        var i = 0
        questsList.forEach {
            val marker = map.addMarker(MarkerOptions().position(LatLng(it.x, it.y)).title("quest").snippet(it.number.toString()))
            if (prefs.getBoolean((i).toString(), false))
                marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_quest_passed)))
            else
                marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_quest)))
            questMarkerList.add(marker)
            i++
        }

        sportsList.forEach {
            val marker = map.addMarker(MarkerOptions().position(LatLng(it.x, it.y)).title("sport").snippet(it.name))
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_ball)))
        }

        musicList.forEach {
            val marker = map.addMarker(MarkerOptions().position(LatLng(it.x, it.y)).title("sport").snippet(it.name))
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_microphone)))
        }

        lectureList.forEach {
            val marker = map.addMarker(MarkerOptions().position(LatLng(it.x, it.y)).title("sport").snippet(it.name))
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_list)))
        }

        tentList.forEach {
            val marker = map.addMarker(MarkerOptions().position(LatLng(it.x, it.y)).title("sport").snippet(it.name))
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_tent)))
        }

        otherList.forEach {
            val marker = map.addMarker(MarkerOptions().position(LatLng(it.x, it.y)).title("sport").snippet(it.name))
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_tent)))
        }

        otherWithDescList.forEach {
            val marker = map.addMarker(MarkerOptions().position(LatLng(it.x, it.y)).title("lecture").snippet(it.name))
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mResources, R.drawable.map_tent)))
        }
    }

    override fun onCameraMoveStarted(p0: Int) {
        // Закрываем открытые нижние вкладки при движении карты
        if (questBottomSheetBehavior.state.equals(BottomSheetBehavior.STATE_EXPANDED))
            questBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        if (sportBottomSheetBehavior.state.equals(BottomSheetBehavior.STATE_EXPANDED))
            sportBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        if (lectureBottomSheetBehavior.state.equals(BottomSheetBehavior.STATE_EXPANDED))
            lectureBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onMarkerClick(clickedMarker: Marker?): Boolean {
        // Обрабатываем клик на метку
        // В зависимости от метки открываем соответствующую нижнюю панель

        if (clickedMarker!!.title.equals("quest")) {
            val questNumber = clickedMarker.snippet.toInt() - 1
            currentQuest = questNumber
            questTitle.text = questsList[questNumber].name
            questText.text = questsList[questNumber].description
            questsPassed.text = String.format(resources.getString(R.string.quests_passed), prefs.getInt("passed", 0))

            questEditText.text.clear()
            val questStatus = prefs.getBoolean(questNumber.toString(), false)
            if (questStatus) {
                questEditText.isEnabled = false
                questButtonCheck.isEnabled = false
                questEditText.hint = resources.getString(R.string.passed)
            } else {
                questButtonCheck.isEnabled = true
                questEditText.isEnabled = true
                questEditText.hint = resources.getString(R.string.enter_the_code)
            }

            sportBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            lectureBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            questBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            map.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(clickedMarker.position).zoom(3.25f).build()))
            return true
        }

        if (clickedMarker.title.equals("sport")) {
            sportTitle.text = clickedMarker.snippet
            questBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            lectureBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            sportBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            map.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(clickedMarker.position).zoom(3.25f).build()))
            return true
        }

        if (clickedMarker.title.equals("lecture")) {
            lectureTitle.text = clickedMarker.snippet
            lectureText.text = otherWithDescList.first { it.name.equals(clickedMarker.snippet) }.description
            questBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            sportBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            lectureBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            map.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(clickedMarker.position).zoom(3.25f).build()))
            return true
        }
        return false
    }

    fun ifPanelClosed(): Boolean = (questBottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED)

    fun closePanels() {
        if (questBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            questBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun checkQuestCode(){
        if (questEditText.text.toString().equals(encryption.decryptOrNull(questsList[currentQuest].passcode))) {
            prefs.edit().putBoolean(currentQuest.toString(), true).apply()
            prefs.edit().putInt("passed", prefs.getInt("passed", 0) + 1).apply()
            Toast.makeText(context, R.string.correct, Toast.LENGTH_LONG).show()
            questsPassed.text = String.format(resources.getString(R.string.quests_passed), prefs.getInt("passed", 0))
            questEditText.text.clear()
            questEditText.isEnabled = false
            questButtonCheck.isEnabled = false
            questEditText.hint = resources.getString(R.string.passed)
            passQuest(currentQuest)
            if(prefs.getInt("passed", 0) == 14){
                (activity as MainActivity).addBadge()
            }
        } else {
            Toast.makeText(context, R.string.incorrect, Toast.LENGTH_LONG).show()
        }
    }

    private fun getAllDataFromJsonFiles() {
        // Достаем все квесты из json
        var text = resources.openRawResource(R.raw.quest)
                .bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder().build()
        val questType = Types.newParameterizedType(List::class.java, Quest::class.java)
        val questJsonAdapter = moshi.adapter<ArrayList<Quest>>(questType)
        questsList = questJsonAdapter.fromJson(text)!!

        text = resources.openRawResource(R.raw.sport)
                .bufferedReader().use { it.readText() }
        val sportType = Types.newParameterizedType(List::class.java, Sport::class.java)
        val sportJsonAdapter = moshi.adapter<ArrayList<Sport>>(sportType)
        sportsList = sportJsonAdapter.fromJson(text)!!

        musicList = ArrayList()
        text = resources.openRawResource(R.raw.music)
                .bufferedReader().use { it.readText() }
        val musicType = Types.newParameterizedType(List::class.java, Music::class.java)
        val musicJsonAdapter = moshi.adapter<ArrayList<Music>>(musicType)
        musicList.addAll(musicJsonAdapter.fromJson(text)!!)

        text = resources.openRawResource(R.raw.meeting)
                .bufferedReader().use { it.readText() }
        val meetingType = Types.newParameterizedType(List::class.java, Music::class.java)
        val meetingJsonAdapter = moshi.adapter<ArrayList<Music>>(meetingType)
        musicList.addAll(meetingJsonAdapter.fromJson(text)!!)

        text = resources.openRawResource(R.raw.lectures)
                .bufferedReader().use { it.readText() }
        val lectureType = Types.newParameterizedType(List::class.java, Lecture::class.java)
        val lectureJsonAdapter = moshi.adapter<ArrayList<Lecture>>(lectureType)
        lectureList = lectureJsonAdapter.fromJson(text)!!

        text = resources.openRawResource(R.raw.entartainment)
                .bufferedReader().use { it.readText() }
        val tentType = Types.newParameterizedType(List::class.java, Tent::class.java)
        val tentJsonAdapter = moshi.adapter<ArrayList<Tent>>(tentType)
        tentList = tentJsonAdapter.fromJson(text)!!

        text = resources.openRawResource(R.raw.other)
                .bufferedReader().use { it.readText() }
        val otherType = Types.newParameterizedType(List::class.java, Tent::class.java)
        val otherJsonAdapter = moshi.adapter<ArrayList<Tent>>(otherType)
        otherList = otherJsonAdapter.fromJson(text)!!

        text = resources.openRawResource(R.raw.other_with_description)
                .bufferedReader().use { it.readText() }
        val otherWithDescType = Types.newParameterizedType(List::class.java, OtherWithDescription::class.java)
        val otherWithDescJsonAdapter = moshi.adapter<ArrayList<OtherWithDescription>>(otherWithDescType)
        otherWithDescList = otherWithDescJsonAdapter.fromJson(text)!!

    }

    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
//        mapBitmap.recycle()
        mapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }
}
