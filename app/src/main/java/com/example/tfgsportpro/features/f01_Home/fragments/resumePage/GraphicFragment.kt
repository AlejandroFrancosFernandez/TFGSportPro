package com.example.tfgsportpro.features.f01_Home.fragments.resumePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentGraphicBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class GraphicFragment : Fragment() {

    private lateinit var binding: FragmentGraphicBinding
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraphicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupChartAppearance()
        loadChartData()

    }

    private fun setupChartAppearance() {
        val lineChart: LineChart = binding.lineChart
        lineChart.axisRight.isEnabled = false
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false

        lineChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
            isGranularityEnabled = true
            axisLineColor = resources.getColor(R.color.colorLetra)
            textColor = resources.getColor(R.color.colorLetra)
            textSize = 12f
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String =
                    (value.toInt() + 1).toString() //+1 para que no empiece en 0
            }
        }
        lineChart.axisLeft.apply {
            setDrawGridLines(false)
            granularity = 1f
            isGranularityEnabled = true
            axisLineColor = resources.getColor(R.color.colorLetra)
            textColor = resources.getColor(R.color.colorLetra)
            textSize = 12f
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String =
                    value.toInt().toString()
            }
        }
        lineChart.animateX(700)
    }

    private fun loadChartData() {
        val lineChart: LineChart = binding.lineChart
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        firestore.collection("User")
            .document(currentUserUid)
            .collection("routines")
            .get()
            .addOnSuccessListener { result ->
                // 1) Conteo por fecha
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
                    timeZone = TimeZone.getTimeZone("Europe/Madrid")
                }
                val countByDate = mutableMapOf<String, Int>()
                result.documents.forEach { doc ->
                    val ts = doc.getLong("timestamp") ?: return@forEach
                    val key = sdf.format(Date(ts))
                    countByDate[key] = (countByDate[key] ?: 0) + 1
                }

                // 2) Preparar fechas y datos
                val allDates: List<Date>
                val entries: List<Entry>

                if (countByDate.isEmpty()) {
                    // No hay datos -> poner la fecha actual sin datos
                    allDates = listOf(Date())
                    entries = listOf()
                } else {
                    val dates = countByDate.keys.map { sdf.parse(it)!! }
                    val minDate = dates.minOrNull()!!
                    val maxDate = dates.maxOrNull()!!
                    val datesList = mutableListOf<Date>()
                    Calendar.getInstance().apply { time = minDate }.also { cal ->
                        while (!cal.time.after(maxDate)) {
                            datesList.add(cal.time)
                            cal.add(Calendar.DATE, 1)
                        }
                    }
                    allDates = datesList

                    entries = allDates.mapIndexed { idx, date ->
                        val cnt = countByDate[sdf.format(date)] ?: 0
                        Entry(idx.toFloat(), cnt.toFloat())
                    }
                }

                // 3) Crear DataSet
                val dataSet = LineDataSet(entries, "").apply {
                    mode = LineDataSet.Mode.LINEAR
                    setDrawCircles(true)
                    circleRadius = 4f
                    setDrawCircleHole(false)
                    lineWidth = 2f
                    color = getColor(R.color.colorLetra)
                    setDrawValues(false)
                }

                // 4) Asignar datos al Chart
                binding.lineChart.apply {
                    xAxis.labelCount = allDates.size
                    data = LineData(dataSet)
                    invalidate()
                }
            }
    }
}
